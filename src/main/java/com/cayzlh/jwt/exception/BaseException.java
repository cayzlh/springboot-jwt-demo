package com.cayzlh.jwt.exception;

/**
 * 描述 :
 * <p>
 *
 *  基础异常类
 *
 * @author Ant丶
 * @date 2018-04-27.
 */
public class BaseException extends RuntimeException {

    public BaseException(Exception e) {

        super(e);
    }

    public BaseException(String message) {
        super(message);
    }

    public BaseException(String message, Throwable cause) {
        super(message, cause);
    }

}
