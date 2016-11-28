package com.lhh.base;

import java.util.ArrayList;
import java.util.List;

public abstract class Neo4jBaseServiceImpl implements Neo4jBaseService {

	protected abstract Neo4jBaseManager getBaseManager(); 
	
	public void adds(List<Neo4jBaseModel> list,String nametype) {
		/*if( list == null || list.isEmpty() )return ;
		StringBuilder sb = new StringBuilder();
		for (Neo4jBaseModel n : list) {
			if( n == null )continue;
			if( StringUtils.isBlank(n.getCode()) || StringUtils.isBlank(n.getName()))continue;
			sb.append(" CREATE ("+n.getCode()+":"+nametype+" { code: '"+n.getCode()+"', name: '"+n.getName()+"' }) \n");
			sb.append(" CREATE ("+parant+")-[:F]->("+n.getCode()+") \n");
			sb.append(" CREATE ("+parant+")-[:F]->("+n.getCode()+") \n");
		}
		getBaseManager().insert(sb.toString());*/
		int i = 0 ;
		System.out.println("--------------->total:"+list.size());
		List<Neo4jBaseModel> models = new ArrayList<Neo4jBaseModel>();
		for (Neo4jBaseModel neo4jBaseModel : list) {
			models.add(neo4jBaseModel);
			if( models.size()>1000){
				System.out.println("--------------->:"+((i++)*models.size()));
				getBaseManager().insert(models, nametype);;
				models.clear();
			}
		}
		System.out.println("--------------->:"+((i++)*models.size()));
		getBaseManager().insert(models, nametype);;
		models.clear();
		
	}
	public void addsMerge(List<Neo4jBaseModel> list,String nametype) {
		getBaseManager().insertMerge(list, nametype);;
	}
	
	public void initBaseData(List<Neo4jBaseModel> list) {
		
	}

}
