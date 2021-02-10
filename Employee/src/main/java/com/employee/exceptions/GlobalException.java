package com.employee.exceptions;

import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.employee.entities.ResponseBean;
import com.employee.util.HttpComponent;
import com.employee.util.SecurityHttpStatus;


@ControllerAdvice
public class GlobalException {
	
	
	static Logger log = Logger.getLogger(GlobalException.class);

	@Autowired
	HttpComponent headers;

	public GlobalException() {
		BasicConfigurator.configure();
	}
	
	@ExceptionHandler
	public ResponseEntity<ResponseBean> handleException(EmployeeNotFoundException e) {
		return loggerMessage("URL:/Employee---->> handleException(EmployeeNotFoundException)-", e.getMessage(),
				SecurityHttpStatus.NOT_FOUND);
	}

	private ResponseEntity<ResponseBean> loggerMessage(String url, String message, int status) 
	{
		ResponseBean seb = new ResponseBean();
		seb.setMessage(message);
		seb.setResponsecode(status);
		seb.setTiemstamp(System.currentTimeMillis());
		log.error(url+ seb.toString());
		return new ResponseEntity<ResponseBean>(seb, headers.getHeader(), HttpStatus.OK);
	}


}
