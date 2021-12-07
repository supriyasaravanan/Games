package com.localme.api.vo;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

public class JwtResponse implements Serializable {
	private static final long serialVersionUID = -8091879091924046844L;
	private String access_token;
	public String getAccess_token() {
		return access_token;
	}
	public JwtResponse(String access_token) {
		super();
		this.access_token = access_token;
	}


}
