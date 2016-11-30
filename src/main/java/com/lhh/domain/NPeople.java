package com.lhh.domain;

import org.springframework.stereotype.Service;

import com.lhh.base.Neo4jBaseModel;

/**
 * 人
 * @author hwaggLee
 * @createDate 2016年10月25日
 */
@Service
public class NPeople  extends Neo4jBaseModel{

	private String sex;//性别
	private String nation;//民族
	private String nationality;//国籍
	private String type;
	
	
	
	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public String getNation() {
		return nation;
	}

	public void setNation(String nation) {
		this.nation = nation;
	}

	public String getNationality() {
		return nationality;
	}

	public void setNationality(String nationality) {
		this.nationality = nationality;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
	

}
