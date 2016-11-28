package com.test.hsjy;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.junit.Test;

import com.lhh.base.Neo4JService;
import com.lhh.base.Neo4jBaseModel;
import com.lhh.domain.NPeople;
import com.lhh.neo4j.common.utils.FormatUtils;
import com.lhh.utils.JdbcHSJYDataUtils;
import com.lhh.utils.JdbcHSJYUtils;
import com.test.BaseServiceTest;


public class TestNQPersonalInfo extends BaseServiceTest {

	@Resource  Neo4JService  neo4JService;
	

	@Test public void testInitCSRCIndustryData(){
		//System.out.println( neo4JService);
		JdbcHSJYUtils utils = new JdbcHSJYUtils();
		try {
			List<Neo4jBaseModel> list = loadData(utils);
			neo4JService.adds(list,"NPeople");
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			utils.closeConnection();
		}
	}
	

	private List<Neo4jBaseModel> loadData(JdbcHSJYUtils utils) throws Exception {
		//恒生聚源自然人维护
		String sql = "select n.ID,n.PersonalCode,n.PersonalNum,n.PersonalName,n.Gender,n.Nation,n.Nationality,n.Age,n.BirthDate from NQ_PersonalInfo n ;";
		List<Map<String, Object>> allmap = utils.findModeResult(sql, null);
		if (allmap == null || allmap.size() == 0)return null;

		List<Neo4jBaseModel> list = new ArrayList<Neo4jBaseModel>();
		Map<String,String> nationMap = JdbcHSJYDataUtils.loadNQSystemNation(utils);
		Map<String,String> nationalityMap = JdbcHSJYDataUtils.loadNQSystemNationality(utils);
		System.out.println("------------------------->");
		int i = 0;
		for (Map<String, Object> m : allmap) {
			String id = FormatUtils.formatToStringTrim(m.get("ID"));
			String personalNum = FormatUtils.formatToStringTrim(m.get("PersonalNum"));
			String personalName = FormatUtils.formatToStringTrim(m.get("PersonalName"));
			String gender = FormatUtils.formatToStringTrim(m.get("Gender"));
			if( "1".equals(gender)){
				gender ="男";
			}else if( "2".equals(gender)){
				gender ="女";
			}
			String nation = FormatUtils.formatToStringTrim(m.get("Nation"));
			String nationality = FormatUtils.formatToStringTrim(m.get("Nationality"));
			
			NPeople n = new NPeople();
			n.setK("np"+(i++));
			n.setCode(personalNum);
			n.setIdStr("T_0019"+id);
			n.setName(personalName);
			n.setNation(nationMap.get(nation));
			n.setNationality(nationalityMap.get(nationality));
			n.setSex(gender);
			list.add(n);
		}
		return list;
	}
	
}
