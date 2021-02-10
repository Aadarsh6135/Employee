package com.employee.util;

import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;

@Component
public class HttpComponent {

HttpHeaders headers= new HttpHeaders();
	
	public HttpHeaders getHeader()
	{
		headers.clear();
		headers.add("TOKENID", "123456789");
		return headers;
	}
}
