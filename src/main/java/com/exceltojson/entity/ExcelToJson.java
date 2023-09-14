package com.exceltojson.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "Employees")
public class ExcelToJson {
	
	@Id
	@Column(name = "Empl_ID")
	private long Id;
	@Column(name = "Name")
	private String employeeName;
	@Column(name = "Department")
	private String department;
	
	public ExcelToJson() {
		super();
	}

	public ExcelToJson(long id, String employeeName, String department) {
		super();
		Id = id;
		this.employeeName = employeeName;
		this.department = department;
	}

	public long getId() {
		return Id;
	}

	public void setId(long id) {
		Id = id;
	}

	public String getEmployeeName() {
		return employeeName;
	}

	public void setEmployeeName(String employeeName) {
		this.employeeName = employeeName;
	}

	public String getDepartment() {
		return department;
	}

	public void setDepartment(String department) {
		this.department = department;
	}

	@Override
	public String toString() {
		return "ExcelToJson [Id=" + Id + ", employeeName=" + employeeName + ", department=" + department + "]";
	}

	
	
	

}
