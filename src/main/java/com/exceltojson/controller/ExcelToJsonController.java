package com.exceltojson.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.exceltojson.entity.ExcelToJson;
import com.exceltojson.excelHelper.ExcelHelper;
import com.exceltojson.message.ResponseMessage;
import com.exceltojson.service.ExcelToJsonService;
import com.google.gson.Gson;

@RestController
@RequestMapping("/api")
@CrossOrigin("http://localhost:3000")
public class ExcelToJsonController {
	
	@Autowired
	private ExcelToJsonService service;
	
	
	
	@PostMapping("/upload")
	public ResponseEntity<ResponseMessage> uploadFile(MultipartFile file){
		String message = "";
		
		if(ExcelHelper.hasExcelFormat(file)) {
			try {
				service.save(file);
				message = "Uploaded the file successfully " + file.getOriginalFilename();
				return ResponseEntity.status(HttpStatus.OK).body(new ResponseMessage(message));
			}catch (Exception e) {
				message = "Could not upload the file " + file.getOriginalFilename()+ "!";
				return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(new ResponseMessage(message));
			}
		}
		message = "Please upload an Excel file ";
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ResponseMessage(message));
	}
	
//	@GetMapping("/view")
//	public ResponseEntity<List<ExcelToJson>> getAllData(){
//		try {
//			List<ExcelToJson> excel = service.getAllData();
//			if(excel.isEmpty()) {
//				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
//				
//			}
//			
//			return new ResponseEntity<>(excel, HttpStatus.OK);
//				
//			}catch (Exception e) {
//				return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
//		}
//	}
	
	@GetMapping("/download")
	public ResponseEntity<String> getDataAsJson(){
		try {
			List<ExcelToJson> data = service.getAllData();
			if(data.isEmpty()) {
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
				
			}
			
			String jsonData = service.getDataAsJson();
	        return ResponseEntity.ok().body(jsonData);
			
		}catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
	}
		
		
	}

}
