package com.jsp.model.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Data
//@AllArgsConstructor
//@NoArgsConstructor
//
//@Getter
//@Setter
//@ToString
//@EqualsAndHashCode
@Builder
public class Animal {
	private String name;
	private int age;
	private double height;
	private double weight;
	private char gender;
	
}
