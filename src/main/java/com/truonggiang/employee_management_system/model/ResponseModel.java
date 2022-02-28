package com.truonggiang.employee_management_system.model;

import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Data;
import org.springframework.http.HttpStatus;

import java.io.Serializable;

@Data
@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
public class ResponseModel implements Serializable{
	private static final long serialVersionUID = -3277553494580760352L;
	private HttpStatus responseStatus;
	private String description;
	private Object data;

	public ResponseModel(HttpStatus responseStatus, String description, Object data) {
		this.responseStatus = responseStatus;
		this.description = description;
		this.data = data;
	}

	public ResponseModel() {

	}

	public HttpStatus getResponseStatus() {
		return responseStatus;
	}
	public void setResponseStatus(HttpStatus responseStatus) {
		this.responseStatus = responseStatus;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public Object getData() {
		return data;
	}
	public void setData(Object data) {
		this.data = data;
	}
}
