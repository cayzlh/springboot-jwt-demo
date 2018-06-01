package com.cayzlh.jwt.security.dto;

import lombok.Data;

import java.io.Serializable;

/**
 * Description:
 *
 * <p>
 *     认证请求体
 *      字段名一定要是 username 和 password
 * </p>
 *
 * @author Ant丶
 * @date 2018-05-10.
 */
@Data
public class AuthenticationRequest implements Serializable {

    private String username;
    private String password;

    public AuthenticationRequest() {
    }

    public AuthenticationRequest(String username, String password) {
        this.username = username;
        this.password = password;
    }
}
