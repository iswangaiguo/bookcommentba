package com.bookcomment.controller;

import java.io.IOException;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.bookcomment.util.TencentUpload;


@RestController
public class UploadFile {

	@PostMapping("/uploadFile")
	public String upload(MultipartFile file) throws IOException {
		if (file == null || file.getSize() == 0) {
			throw new NullPointerException("上传文件为空");
		} else {
			return TencentUpload.upload(file.getInputStream(), file.getOriginalFilename(),  file.getSize());
		}
	}
}
