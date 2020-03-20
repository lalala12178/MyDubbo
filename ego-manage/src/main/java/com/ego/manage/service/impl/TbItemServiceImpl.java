package com.ego.manage.service.impl;

import com.ego.commons.utils.IDUtils;
import com.ego.dubbo.service.TbItemDescDubboService;
import com.ego.pojo.TbItemDesc;
import org.apache.dubbo.config.annotation.Reference;
import org.springframework.stereotype.Service;

import com.ego.commons.pojo.EasyUIDataGrid;
import com.ego.dubbo.service.TbItemDubboService;
import com.ego.manage.service.TbItemService;
import com.ego.pojo.TbItem;

import java.util.Date;

@Service
public class TbItemServiceImpl implements TbItemService{
	@Reference
	private TbItemDubboService tbItemDubboServiceImpl;
	@Reference
	private TbItemDescDubboService tbItemDescDubboService;

	public EasyUIDataGrid show(int page, int rows) {
		return tbItemDubboServiceImpl.show(page, rows);
	}

	public int update(String ids, byte status) {
		int index = 0 ;
		TbItem item = new TbItem();
		String[] idsStr = ids.split(",");
		for (String id : idsStr) {
			item.setId(Long.parseLong(id));
			item.setStatus(status);
			index +=tbItemDubboServiceImpl.updItemStatus(item);
		}
		if(index==idsStr.length){
			return 1;
		}
		return 0;
	}
	public int save(TbItem item, String desc) throws Exception {
		// 不考虑事务回滚
		// long id = IDUtils.genItemId();
		// item.setId(id);
		// Date date = new Date();
		// item.setCreated(date);
		// item.setUpdated(date);
		// item.setStatus((byte)1);
		// int index = tbItemDubboServiceImpl.insTbItem(item);
		// if(index>0){
		// TbItemDesc itemDesc = new TbItemDesc();
		// itemDesc.setItemDesc(desc);
		// itemDesc.setItemId(id);
		// itemDesc.setCreated(date);
		// itemDesc.setUpdated(date);
		// index+=tbItemDescDubboService.insDesc(itemDesc);
		// }
		// if(index==2){
		// return 1;
		// }
		// 调用dubbo中考虑事务回滚功能方法
		long id = IDUtils.genItemId();
		item.setId(id);
		Date date = new Date();
		item.setCreated(date);
		item.setUpdated(date);
		item.setStatus((byte) 1);

		TbItemDesc itemDesc = new TbItemDesc();
		itemDesc.setItemDesc(desc);
		itemDesc.setItemId(id);
		itemDesc.setCreated(date);
		itemDesc.setUpdated(date);

		int index = 0;
		try {
			index = tbItemDubboServiceImpl.insTbItemDesc(item, itemDesc);
		}catch (Exception e){
			e.printStackTrace();
		}

//		System.out.println("index:" + index);
		return index;
	}
}
