package com.exceltojson.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.exceltojson.entity.ExcelToJson;

@Repository
public interface ExcelToJsonRepository extends JpaRepository<ExcelToJson, Long> {

}
