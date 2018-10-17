package com.feng.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Student {

	private String id;
	
	private String name;
	
	private int age;
	
	private String[] hobby;
	
	private String birthplace;
	
	private String grade;
	
	private String teacher;
	
}
