package com.cayzlh.jwt.exception;


import org.springframework.security.core.AuthenticationException;

/**
 * 描述 :
 * <p>
 *
 *  用户名已存在异常
 *
 * @author Ant丶
 * @date 2018-04-27.
 */
public class UsernameIsExistException extends AuthenticationException {

    public UsernameIsExistException(String msg) {
        super(msg);
    }

    public UsernameIsExistException(String msg, Throwable t) {
        super(msg, t);
    }


}
