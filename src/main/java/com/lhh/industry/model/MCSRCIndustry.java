package com.lhh.industry.model;

import com.lhh.base.Neo4jBaseModel;

/**
 * 证监会行业分类-管理行业
 * @author hwaggLee
 * @createDate 2016年10月19日
 */
public class MCSRCIndustry extends Neo4jBaseModel{

	private String neeqCode;//新三板使用合并code，如果设计到多级，需要合并code
	private int level;//级别
	private MCSRCIndustry parentIndustry ;//子集
	
	public String getNeeqCode() {
		return neeqCode;
	}
	public void setNeeqCode(String neeqCode) {
		this.neeqCode = neeqCode;
	}
	public int getLevel() {
		return level;
	}
	public void setLevel(int level) {
		this.level = level;
	}
	public MCSRCIndustry getParentIndustry() {
		return parentIndustry;
	}
	public void setParentIndustry(MCSRCIndustry parentIndustry) {
		this.parentIndustry = parentIndustry;
	}
	
	
}
