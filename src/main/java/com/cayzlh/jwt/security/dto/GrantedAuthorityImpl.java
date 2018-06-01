package com.cayzlh.jwt.security.dto;

import lombok.Data;
import org.springframework.security.core.GrantedAuthority;

/**
 * Description:
 *
 * <p>
 *     权限类型，负责存储权限和角色
 * </p>
 *
 * @author Ant丶
 * @date 2018-05-11.
 */
@Data
public class GrantedAuthorityImpl implements GrantedAuthority {

    private String authority;

    public GrantedAuthorityImpl(String authority) {
        this.authority = authority;
    }

    @Override
    public String getAuthority() {
        return this.authority;
    }
}
