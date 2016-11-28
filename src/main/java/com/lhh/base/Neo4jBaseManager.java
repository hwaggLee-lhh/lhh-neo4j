package com.lhh.base;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import net.sf.json.JSONObject;

import org.apache.commons.lang.StringUtils;

import com.lhh.config.Neo4jJdbcUtils;

public abstract class Neo4jBaseManager {
	

	public List<String> findList(String sql,String key){
		List<String> list = new ArrayList<String>();
		ResultSet rs = null;
		Statement stmt = null;
		Connection connection = null;
		try {
			connection = new Neo4jJdbcUtils().getConnection();
			stmt = connection.createStatement();
			rs = stmt.executeQuery(sql);
			while (rs.next()) {
				list.add(rs.getString(key));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally{
			closeConnection(connection);
			closeStatement(stmt);
			closeResultSett(rs);
		}
		return list;
	}
	
	/**
	 * 不过滤重复，直接创建
	 * @param sql
	 */
	public void insert(String sql ){
		ResultSet rs = null;
		Statement stmt = null;
		Connection connection = null;
		try {
			connection = new Neo4jJdbcUtils().getConnection();
			stmt = connection.createStatement();
			rs = stmt.executeQuery(sql);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally{
			closeConnection(connection);
			closeResultSett(rs);
			closeStatement(stmt);
		}
	}

	/**
	 * 不过滤重复，直接创建
	 * @param list
	 * @param nametype
	 */
	public void insert(List<Neo4jBaseModel> list,String nametype) {
		if( list == null || list.isEmpty() )return ;
		StringBuilder sb = new StringBuilder();
		
		String sqlmodel =" CREATE ({_neo4j_id}:{_neo4j_type} {_neo4j_properties}) \n";
		
		for (Neo4jBaseModel n : list) {
			if( n == null )continue;
			if( StringUtils.isBlank(n.getCode()) || StringUtils.isBlank(n.getName()))continue;
			String sql = sqlmodel.replace("{_neo4j_id}", n.getK());
			sql = sql.replace("{_neo4j_type}", nametype);
			sql = sql.replace("{_neo4j_properties}", formatJson(JSONObject.fromObject(n).toString()));
			sb.append(sql);
			//test
			//sb.append(" CREATE ("+parant+")-[:F]->("+n.getCode()+") \n");
		}
		System.out.println("start insert db");
		insert(sb.toString());
		System.out.println("start insert db end");
	}
	
	/**
	 * 过滤重建
	 * @param sql
	 */
	public void insertMerge(List<String> sqlList ){
		ResultSet rs = null;
		Statement stmt = null;
		Connection connection = null;
		connection = new Neo4jJdbcUtils().getConnection();
		try {
			stmt = connection.createStatement();
			for (String sql : sqlList) {
				try {
					rs = stmt.executeQuery(sql);
				} catch (Exception e) {
					e.printStackTrace();
				} finally{
					closeResultSett(rs);
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally{
			closeConnection(connection);
			closeStatement(stmt);
		}
	}

	/**
	 * 过滤重复
	 * @param list
	 * @param nametype
	 */
	public void insertMerge(List<Neo4jBaseModel> list,String nametype) {
		if( list == null || list.isEmpty() )return ;
		List<String> sqlList = new ArrayList<String>();
		String sqlmodel =" merge ({_neo4j_id}:{_neo4j_type} {_neo4j_properties}) on CREATE set {_neo4j_idstr} \n";

		//int size = list.size();
		for (Neo4jBaseModel n : list) {
			if( n == null )continue;
			if( StringUtils.isBlank(n.getCode()) || StringUtils.isBlank(n.getName()))continue;
			String sql = sqlmodel.replace("{_neo4j_id}", n.getK());
			sql = sql.replace("{_neo4j_type}", nametype);
			sql = sql.replace("{_neo4j_idstr}", n.getK()+".idStr='"+n.getIdStr()+"'");
			sql = sql.replace("{_neo4j_properties}", formatJson(JSONObject.fromObject(n).toString()));
			sqlList.add(sql);
			//System.out.print((size--)+":"+sql);
		}
		insertMerge(sqlList);
	}
	
	protected String formatJson(String jsonObject){
		String[] sql = jsonObject.split(",");
		StringBuilder sb = new StringBuilder();
		for (String string : sql) {
			sb.append(string.replaceFirst("\"", "").replaceFirst("\"", "")).append(",");
		}
		return sb.toString().substring(0, sb.length()-1);
	}

	
	public void closeConnection(Connection conn) {
		if (conn != null) {
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	public void closeStatement(Statement stmt) {
		if (stmt != null) {
			try {
				stmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}


	public void closeResultSett(ResultSet rs) {
		if (rs != null) {
			try {
				rs.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

}
