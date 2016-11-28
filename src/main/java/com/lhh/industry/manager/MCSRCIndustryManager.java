package com.lhh.industry.manager;

import java.util.ArrayList;
import java.util.List;

import net.sf.json.JSONObject;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Repository;

import com.lhh.base.Neo4jBaseManager;
import com.lhh.base.Neo4jBaseModel;
import com.lhh.industry.model.MCSRCIndustry;
import com.lhh.utils.Neo4jBaseKey;

@Repository
public class MCSRCIndustryManager extends Neo4jBaseManager{
	private static final String nametype = Neo4jBaseKey.KEY_SCRC_INDUSTRY; 

	/**
	 * 插入节点（如果存在则不插入）
	 * @param list
	 */
	public void insertMergeNodes(List<Neo4jBaseModel> list) {
		if( list == null || list.isEmpty() ) return ;
		List<String> sqlList = new ArrayList<String>();
		String sqlmodel =" merge ({_neo4j_id}:{_neo4j_type} {_neo4j_properties}) on CREATE set {_neo4j_idstr} \n";
		for (Neo4jBaseModel m : list) {
			if( m == null )continue;
			if( StringUtils.isBlank(m.getCode()) || StringUtils.isBlank(m.getName()))continue;
			String sql = sqlmodel.replace("{_neo4j_id}", m.getIdStr());
			sql = sql.replace("{_neo4j_type}", nametype);
			sql = sql.replace("{_neo4j_idstr}", m.getIdStr()+".idStr="+m.getIdStr());
			sql = sql.replace("{_neo4j_properties}", formatJson(JSONObject.fromObject(m).toString()));
			sqlList.add(sql);
		}
		insertMerge(sqlList);
	}
	
	/**
	 * 插入关系
	 * @param list
	 */
	public void insertMergeRelationships(List<Neo4jBaseModel> list) {
		if( list == null || list.isEmpty() ) return ;
		
		//初始化数据
		List<String> sqlList = new ArrayList<String>();
		String sqlmodel =" merge ({_neo4j_id}:{_neo4j_type} {_neo4j_properties}) on CREATE set {_neo4j_idstr} \n";
		for (Neo4jBaseModel m : list) {
			if( m == null )continue;
			if( StringUtils.isBlank(m.getCode()) || StringUtils.isBlank(m.getName()))continue;
			String sql = sqlmodel.replace("{_neo4j_id}", m.getIdStr());
			sql = sql.replace("{_neo4j_type}", nametype);
			sql = sql.replace("{_neo4j_idstr}", m.getIdStr()+".idStr="+m.getIdStr());
			sql = sql.replace("{_neo4j_properties}", formatJson(JSONObject.fromObject(m).toString()));
			sqlList.add(sql);
			
			if( m instanceof MCSRCIndustry ){
				MCSRCIndustry mi = (MCSRCIndustry)m;
				MCSRCIndustry parentIndustry = mi.getParentIndustry();
				
			}
		}
		insertMerge(sqlList);
	}
}
