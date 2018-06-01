package com.cayzlh.jwt.exception;

/**
 * Description :
 *
 * <p>
 *     鉴权异常
 * </p>
 *
 * @author Ant丶
 * @date 2018-04-27.
 */
public class AuthenticationException extends BaseException {

	public AuthenticationException(String message, Throwable cause) {
		super(message, cause);
	}
}
