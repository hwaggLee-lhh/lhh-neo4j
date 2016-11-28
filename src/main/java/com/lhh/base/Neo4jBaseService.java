package com.lhh.base;

import java.util.List;

public interface Neo4jBaseService {

	void adds(List<Neo4jBaseModel> list,String nametype);
	void addsMerge(List<Neo4jBaseModel> list,String nametype);
	void initBaseData(List<Neo4jBaseModel> list);
}
