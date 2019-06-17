package com.maxwell.flashcards.response;

import com.maxwell.flashcards.util.ApplicationLogUtils;

public class ResponseUtils {
	
	ApplicationLogUtils log = new ApplicationLogUtils();

	public <T> Response<T> setMessages(Response<T> response, String message, String className, Boolean status) {
		response.setMessage(message);
		response.setStatus(status);
		log.generateLog(message, className);
		return response;
	}

}
