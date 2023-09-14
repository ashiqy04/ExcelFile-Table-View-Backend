package com.exceltojson.service;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.exceltojson.entity.ExcelToJson;

public interface ExcelToJsonService {
	
	void save(MultipartFile file);
	
	List<ExcelToJson> getAllData();
	

	String getDataAsJson();
}
