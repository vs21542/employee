package com.example.employee.util;

import org.springframework.http.HttpHeaders;

public class HTTPHeaderUtil {
	public static HttpHeaders getHeaders() {
		HttpHeaders headers = new HttpHeaders();
		headers.add("Host", "localhost:8081");
		headers.add("Content-Language", "en-US");
		return headers;
	}
}
