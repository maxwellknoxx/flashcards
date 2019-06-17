package com.maxwell.flashcards.response;

import java.util.ArrayList;
import java.util.List;

public class Response<T> {

	private T data;
	private List<T> listData;
	private String message;
	private Boolean status;

	public Response() {
	}

	public T getData() {
		return data;
	}

	public void setData(T data) {
		this.data = data;
	}

	public List<T> getListData() {
		if (this.listData == null) {
			this.listData = new ArrayList<T>();
		}
		return listData;
	}

	public void setListData(List<T> listData) {
		this.listData = listData;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public Boolean getStatus() {
		return status;
	}

	public void setStatus(Boolean status) {
		this.status = status;
	}

}
