package com.cayzlh.jwt.service;

import com.cayzlh.jwt.mapper.AuthRoleMapper;
import com.cayzlh.jwt.mapper.AuthUserMapper;
import com.cayzlh.jwt.model.AuthRole;
import com.cayzlh.jwt.model.AuthUser;
import com.cayzlh.jwt.model.example.AuthRoleExample;
import com.cayzlh.jwt.model.example.AuthUserExample;
import com.google.common.base.Preconditions;
import com.google.common.collect.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Description:
 *
 * <p>
 * Authoritarian服务类
 * </p>
 *
 * @author Ant丶
 * @date 2018-05-22.
 */
@Service
public class AuthService {

  private final AuthUserMapper authUserMapper;
  private final AuthRoleMapper authRoleMapper;

  @Autowired
  public AuthService(AuthUserMapper authUserMapper, AuthRoleMapper authRoleMapper) {
    this.authUserMapper = authUserMapper;
    this.authRoleMapper = authRoleMapper;
  }

  /**
   * 通过用户名加载用户
   *
   * @param userName 用户名
   * @return AuthUser
   */
  public AuthUser loadUserByUserName(String userName) {
    Preconditions.checkNotNull(userName);

    return authUserMapper.selectOneByExample(new AuthUserExample().createCriteria()
        .andUserNameEqualTo(userName)
        .example());
  }

  /**
   * 通过用户名返回用户角色
   *
   * @param userName 用户名
   * @return 用户角色
   */
  public List<AuthRole> loadAuthoritiesByUserName(String userName) {
    Preconditions.checkNotNull(userName);

    List<AuthRole> authorities = authRoleMapper
        .selectByExample(new AuthRoleExample().createCriteria()
            .andUserNameEqualTo(userName)
            .example());

    if (null == authorities) {
      authorities = Lists.newArrayList();
    }
    return authorities;

  }


}
