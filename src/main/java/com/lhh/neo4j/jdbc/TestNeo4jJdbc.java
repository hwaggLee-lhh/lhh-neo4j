package com.lhh.neo4j.jdbc;

import java.sql.SQLException;
import java.util.List;

public class TestNeo4jJdbc {
	public static void main(String[] args) throws SQLException {
		Neo4JJdbcUtils utils = new Neo4JJdbcUtils();
		List<String> list = utils.findList("MATCH (n) RETURN n","n");
		for (String string : list) {
			System.out.println(string);
		}
	}
	
}
