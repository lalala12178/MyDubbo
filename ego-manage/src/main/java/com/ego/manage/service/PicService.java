package com.ego.manage.service;

import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Map;

public interface PicService {

    Map<String,Object> upload(MultipartFile file) throws IOException;
}
