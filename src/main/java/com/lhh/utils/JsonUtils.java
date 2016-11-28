package com.lhh.utils;

import java.util.HashMap;
import java.util.Map;

import net.sf.json.JSONObject;

public class JsonUtils {

	public static void main(String[] args) {
		Map<String,String> map = new HashMap<String, String>();
		for (int i = 0; i < 11; i++) {
			map.put("f"+i, "n"+i);
		}
		System.out.println(map.toString());
		System.out.println(JSONObject.fromObject(map).toString());
				
	}
}
