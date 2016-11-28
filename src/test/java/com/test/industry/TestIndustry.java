package com.test.industry;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.junit.Test;

import com.lhh.base.JdbcUtils;
import com.lhh.base.Neo4jBaseModel;
import com.lhh.industry.model.MCSRCIndustry;
import com.lhh.industry.service.MCSRCIndustryService;
import com.test.BaseServiceTest;

public class TestIndustry  extends BaseServiceTest{

	@Resource MCSRCIndustryService mcsrcIndustryService;
	
	/**
	 * 初始化管理行业数据
	 * 
	 */
	@Test public void testInitCSRCIndustryData(){
		JdbcUtils jdbcUtils = new JdbcUtils();
		String sql = "select * from nq_industry where type = 2";
		List<Map<String, Object>> listDB = null;
		try {
			listDB = jdbcUtils.findModeResult(sql, null);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		if( listDB == null ){
			log.info("无数据");
			return ;
		}
		
		List<Neo4jBaseModel> list = new ArrayList<Neo4jBaseModel>();
		long idstr = System.currentTimeMillis();
		for (Map<String,Object> map : listDB) {
			if( map == null || map.isEmpty() )continue;
			String oneLevelCode = map.get("oneLevelCode").toString();
			String oneLevelName = map.get("oneLevelName").toString();
			
			MCSRCIndustry one = createMCSRCIndustry(oneLevelCode, oneLevelName, (idstr++)+"", 1, oneLevelCode, null);
			list.add(one);
			
			String twoLevelCode = map.get("twoLevelCode").toString();
			String twoLevelName = map.get("twoLevelName").toString();

			MCSRCIndustry two = createMCSRCIndustry(twoLevelCode, twoLevelName, (idstr++)+"", 2, oneLevelCode+""+twoLevelCode, one);
			list.add(two);
			
			String threeLevelCode = map.get("threeLevelCode").toString();
			String threeLevelName = map.get("threeLevelName").toString();

			MCSRCIndustry three = createMCSRCIndustry(threeLevelCode, threeLevelName, (idstr++)+"", 3, twoLevelCode+""+threeLevelCode, two);
			list.add(three);

			String fourLevelCode = map.get("fourLevelCode").toString();
			String fourLevelName = map.get("fourLevelName").toString();

			MCSRCIndustry four = createMCSRCIndustry(fourLevelCode, fourLevelName, (idstr++)+"", 4, threeLevelCode+""+fourLevelCode, three);
			list.add(four);
		}
		mcsrcIndustryService.initBaseData(list);
	}
	
	private MCSRCIndustry createMCSRCIndustry(String code,String name,String idStr,int level,String neeqCode,MCSRCIndustry parentIndustry){
		MCSRCIndustry industry = new MCSRCIndustry();
		industry.setCode(code);
		industry.setName(name);
		industry.setIdStr(idStr+"");
		industry.setLevel(level);
		industry.setNeeqCode(neeqCode);
		industry.setParentIndustry(parentIndustry);
		
		return industry;
	}
}
