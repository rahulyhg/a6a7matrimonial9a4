package com.techno.matrimonial.Model.common;

import java.io.Serializable;

public class ErrorMessageMain implements Serializable{
	public int code;
	public String description;
	public String message;

	public void setCode(int code){
		this.code = code;
	}

	public int getCode(){
		return code;
	}

	public void setDescription(String description){
		this.description = description;
	}

	public String getDescription(){
		return description;
	}

	public void setMessage(String message){
		this.message = message;
	}

	public String getMessage(){
		return message;
	}
}
