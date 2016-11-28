package com.lhh.base;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

@Service
public class Neo4JServiceImpl  extends Neo4jBaseServiceImpl implements Neo4JService {
	@Resource private Neo4JManager neo4JManager;
	@Override protected Neo4jBaseManager getBaseManager() {
		return neo4JManager;
	}
	


}
