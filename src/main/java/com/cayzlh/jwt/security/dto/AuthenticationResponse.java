package com.cayzlh.jwt.security.dto;

import lombok.Data;

import java.io.Serializable;

/**
 * Description :
 *
 * <p>返回数据封装</p>
 *
 * @author Ant丶
 * @date 2018-04-27.
 */
@Data
public class AuthenticationResponse implements Serializable {

	private static final long serialVersionUID = 1L;

	private final String token;

	public AuthenticationResponse(String token) {
		this.token = token;
	}
}
