package com.cayzlh.jwt.exception;

/**
 * 描述 :
 * <p>
 *
 *  Token异常
 *
 * @author Ant丶
 * @date 2018-04-27.
 */
public class TokenException extends BaseException {

    private static final long serialVersionUID = 1L;

    public static final String TOKEN_EXP_MSG = "Token已过期";

    public static final String TOKEN_UNSUPPORTED_MSG = "Token格式不对";

    public static final String TOKEN_MALFORMED_MSG = "Token没有被正确构造";

    public static final String TOKEN_SIGNATURE_MSG = "签名失败";

    public static final String TOKEN_ILLEGALARGUMENT_MSG = "非法参数异常";

    public TokenException(String message) {
        super(message);
    }

}
