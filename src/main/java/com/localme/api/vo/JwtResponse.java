package com.localme.api.vo;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

public class JwtResponse implements Serializable {
	private static final long serialVersionUID = -8091879091924046844L;
	private String jwttoken;

	public String getJwtToken() {
		return jwttoken;
	}

	public JwtResponse(String jwttoken) {
		super();
		this.jwttoken = jwttoken;
	}
	
	
}
