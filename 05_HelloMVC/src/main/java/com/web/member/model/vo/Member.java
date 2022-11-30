package com.web.member.model.vo;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@Builder
@NoArgsConstructor
	public class Member {
		private String userId;
		private String password;
		private String userName;
		private char gender;
		private int age;
		private String email;
		private String phone;
		private String address;
		private String[] hobby;
		private Date enrollDate;
	}

