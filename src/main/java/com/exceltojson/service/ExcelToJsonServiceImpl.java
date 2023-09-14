package com.exceltojson.service;

import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.exceltojson.entity.ExcelToJson;
import com.exceltojson.excelHelper.ExcelHelper;
import com.exceltojson.repository.ExcelToJsonRepository;
import com.google.gson.Gson;

@Service
public class ExcelToJsonServiceImpl implements ExcelToJsonService {
	
	@Autowired
	private ExcelToJsonRepository repo;
	@Autowired
	private Gson gson;

	@Override
	public void save(MultipartFile file) {
		try {
			List<ExcelToJson> excel = ExcelHelper.excel(file.getInputStream());
			repo.saveAll(excel);
		}catch (IOException e) {
			throw new RuntimeException("Fail to store the excel data: "+ e.getMessage());
		}
	}

	@Override
	public List<ExcelToJson> getAllData() {
		return repo.findAll();
	}


	@Override
	public String getDataAsJson() {
		
		List<ExcelToJson> data = repo.findAll();
        return gson.toJson(data);
	}

}
