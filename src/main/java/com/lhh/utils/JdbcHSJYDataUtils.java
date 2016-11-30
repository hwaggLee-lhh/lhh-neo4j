package com.lhh.utils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.lhh.neo4j.common.utils.FormatUtils;

/**
 * 读取恒生聚源数据库的数据(返回对象不返回空)
 * @author hwaggLee
 * @createDate 2016年10月27日
 */
public class JdbcHSJYDataUtils {

	/**
	 * 加载民族信息
	 * @param utils
	 * @return
	 */
	public static Map<String,String> loadNQSystemNation(JdbcHSJYUtils utils)throws Exception {
		Map<String,String> map = new HashMap<String, String>();
		String sql = "select dm,ms from nq_SystemConst n where n.LB ='1887' ;";
		List<Map<String, Object>> allmap = utils.findModeResult(sql, null);
		if (allmap == null || allmap.size() == 0)
			return map;
		for (Map<String, Object> m : allmap) {
			String dm = FormatUtils.formatToStringTrim(m.get("dm"));
			String ms = FormatUtils.formatToStringTrim(m.get("ms"));
			map.put(dm, ms);
		}
		return map;
	}
	

	/**
	 * 加载国籍信息
	 * @param utils
	 * @return
	 */
	public static Map<String,String> loadNQSystemNationality(JdbcHSJYUtils utils)throws Exception {
		Map<String,String> map = new HashMap<String, String>();
		String sql = "select dm,ms from nq_SystemConst n where n.LB ='1023' ;";
		List<Map<String, Object>> allmap = utils.findModeResult(sql, null);
		if (allmap == null || allmap.size() == 0)
			return map;
		for (Map<String, Object> m : allmap) {
			String dm = FormatUtils.formatToStringTrim(m.get("dm"));
			String ms = FormatUtils.formatToStringTrim(m.get("ms"));
			map.put(dm, ms);
		}
		return map;
	}
}
