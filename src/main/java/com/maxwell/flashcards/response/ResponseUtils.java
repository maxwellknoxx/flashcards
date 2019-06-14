package com.maxwell.flashcards.response;

import org.springframework.http.ResponseEntity;

import com.maxwell.flashcards.util.ApplicationLogUtils;

public class ResponseUtils {
	
	ApplicationLogUtils log = new ApplicationLogUtils();

	public <T> Response<T> setMessages(Response<T> response, String message, Boolean status) {
		response.setMessage(message);
		response.setStatus(status);
		log.generateLog(message, response.getClass().toString());
		return response;
	}

	public <T> ResponseEntity<Response<T>> setExceptionMessage(Response<T> response, Exception e) {
		e.printStackTrace();
		response.getErrors().add(e.getCause().toString());
		response.setStatus(false);
		log.generateLog(e.getCause().toString(), response.getData().toString());
		return ResponseEntity.badRequest().body(response);
	}

}
