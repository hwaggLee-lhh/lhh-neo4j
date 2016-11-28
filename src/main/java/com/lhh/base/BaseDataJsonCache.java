package com.lhh.base;

import java.io.IOException;
import java.util.Set;

import net.sf.json.JSONObject;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.lang.StringUtils;

public abstract class BaseDataJsonCache {

	protected JSONObject getRemoteData(String url) {
		String result = execute(url);
		if(StringUtils.isNotBlank(result)) {
			try {
				return JSONObject.fromObject(result);
			} catch (Exception e) {
			}
		}
		return new JSONObject();
	}

	protected JSONObject getRemoteData(String url, JSONObject params) {
		String result = execute(url, params);
		if(StringUtils.isNotBlank(result)) {
			try {
				return JSONObject.fromObject(result);
			} catch (Exception e) {
			}
		}
		return new JSONObject();
	}

	public static String execute(String url) {
		HttpClient httpClient = new HttpClient();
		PostMethod getMethod = new PostMethod(url);
		getMethod.addRequestHeader("Connection", "close");
		try {
			httpClient.executeMethod(getMethod);
			byte[] responseBody = getMethod.getResponseBody(); 
			String responseStr = new String(responseBody,"utf-8");
			return responseStr;
		} catch (HttpException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} 
		return null;
	}

	public static String execute(String url, JSONObject params) {
		if(params==null || params.isEmpty()) return execute(url);
		HttpClient httpClient = new HttpClient();
		PostMethod postMethod = new PostMethod(url);
		postMethod.addRequestHeader("Connection", "close");
		@SuppressWarnings("unchecked")
		Set<String> set = params.keySet();
		for (String string : set) {
			String value = params.getString(string);
			if(StringUtils.isBlank(value)) continue;
			postMethod.addParameter(string, value);
		}
		postMethod.addParameter("form", "yejia");
		try {
			httpClient.executeMethod(postMethod);
			byte[] responseBody = postMethod.getResponseBody(); 
			String responseStr = new String(responseBody,"utf-8");
			return responseStr;
		} catch (HttpException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} 
		return null;
	}
	
}
