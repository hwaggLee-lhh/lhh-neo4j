package com.lhh.industry.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.lhh.base.Neo4jBaseManager;
import com.lhh.base.Neo4jBaseModel;
import com.lhh.base.Neo4jBaseServiceImpl;
import com.lhh.industry.manager.MCSRCIndustryManager;
import com.lhh.industry.service.MCSRCIndustryService;

@Service
public class MCSRCIndustryServiceImpl  extends Neo4jBaseServiceImpl implements MCSRCIndustryService {
	@Resource private MCSRCIndustryManager mcsrcIndustryManager;
	@Override protected Neo4jBaseManager getBaseManager() {
		return mcsrcIndustryManager;
	}
	
	
	
	@Override public void initBaseData(List<Neo4jBaseModel> list) {
		//mcsrcIndustryManager.insertBaseData(list);
	}


}
