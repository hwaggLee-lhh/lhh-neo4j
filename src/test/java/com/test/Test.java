package com.test;

import net.sf.json.JSONObject;

public class Test {

	public static void main(String[] args) {
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("code", "7897897");
		jsonObject.put("name", "反对撒反对");
		System.out.println(jsonObject.toString());
	}
}
