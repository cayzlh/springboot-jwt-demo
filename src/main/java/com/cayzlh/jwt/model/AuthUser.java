package com.cayzlh.jwt.model;

import java.io.Serializable;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * Table:
 *   t_auth_user
 *
 * @author Ant丶
 * @mbg.generated
 */
public class AuthUser implements Serializable {
    /**
     * Description:
     *   主键
     *
     * Column:
     *   t_auth_user.id
     *
     * @mbg.generated
     */
    private Long id;

    /**
     * Description:
     *   用户名(登录名)
     *
     * Column:
     *   t_auth_user.user_name
     *
     * @mbg.generated
     */
    private String userName;

    /**
     * Description:
     *   用户昵称
     *
     * Column:
     *   t_auth_user.nick_name
     *
     * @mbg.generated
     */
    private String nickName;

    /**
     * Description:
     *   密码
     *
     * Column:
     *   t_auth_user.password
     *
     * @mbg.generated
     */
    private String password;

    /**
     * Description:
     *   手机号码
     *
     * Column:
     *   t_auth_user.phone
     *
     * @mbg.generated
     */
    private String phone;

    /**
     * Description:
     *   邮箱
     *
     * Column:
     *   t_auth_user.email
     *
     * @mbg.generated
     */
    private String email;

    /**
     * Description:
     *   用户类型
     *
     * Column:
     *   t_auth_user.user_type
     *
     * @mbg.generated
     */
    private String userType;

    /**
     * Description:
     *   微信openid
     *
     * Column:
     *   t_auth_user.wechat_open_id
     *
     * @mbg.generated
     */
    private String wechatOpenId;

    /**
     * Description:
     *   微信unionid
     *
     * Column:
     *   t_auth_user.wechat_union_id
     *
     * @mbg.generated
     */
    private String wechatUnionId;

    /**
     * Description:
     *   是否可用 1 是 0 否
     *
     * Column:
     *   t_auth_user.enabled
     *
     * @mbg.generated
     */
    private Integer enabled;

    /**
     * Description:
     *   创建时间
     *
     * Column:
     *   t_auth_user.create_time
     *
     * @mbg.generated
     */
    private Date createTime;

    /**
     * Description:
     *   更新时间
     *
     * Column:
     *   t_auth_user.update_time
     *
     * @mbg.generated
     */
    private Date updateTime;

    /**
     * Description:
     *   最后一次修改密码的时间
     *
     * Column:
     *   t_auth_user.last_password_reset_date
     *
     * @mbg.generated
     */
    private Date lastPasswordResetDate;

    /**
     * Description:
     *  This field corresponds to the database table t_auth_user
     *
     * @mbg.generated
     */
    private static final long serialVersionUID = 1L;

    /**
     * Description:
     *  This field corresponds to the database table t_auth_user
     *
     * @mbg.generated
     */
    private Map<String, Boolean> selectiveColumns = new HashMap<String, Boolean>();

    /**
     * Description:
     *  返回 t_auth_user.id 的值
     *
     * @return 字段 t_auth_user.id 的值
     *
     * @mbg.generated
     */
    public Long getId() {
        return id;
    }

    /**
     * Description:
     *  设置 t_auth_user.id 的值.
     *
     * @param id 字段 t_auth_user.id 的值.
     *
     * @mbg.generated
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Description:
     *  返回 t_auth_user.user_name 的值
     *
     * @return 字段 t_auth_user.user_name 的值
     *
     * @mbg.generated
     */
    public String getUserName() {
        return userName;
    }

    /**
     * Description:
     *  设置 t_auth_user.user_name 的值.
     *
     * @param userName 字段 t_auth_user.user_name 的值.
     *
     * @mbg.generated
     */
    public void setUserName(String userName) {
        this.userName = userName == null ? null : userName.trim();
    }

    /**
     * Description:
     *  返回 t_auth_user.nick_name 的值
     *
     * @return 字段 t_auth_user.nick_name 的值
     *
     * @mbg.generated
     */
    public String getNickName() {
        return nickName;
    }

    /**
     * Description:
     *  设置 t_auth_user.nick_name 的值.
     *
     * @param nickName 字段 t_auth_user.nick_name 的值.
     *
     * @mbg.generated
     */
    public void setNickName(String nickName) {
        this.nickName = nickName == null ? null : nickName.trim();
    }

    /**
     * Description:
     *  返回 t_auth_user.password 的值
     *
     * @return 字段 t_auth_user.password 的值
     *
     * @mbg.generated
     */
    public String getPassword() {
        return password;
    }

    /**
     * Description:
     *  设置 t_auth_user.password 的值.
     *
     * @param password 字段 t_auth_user.password 的值.
     *
     * @mbg.generated
     */
    public void setPassword(String password) {
        this.password = password == null ? null : password.trim();
    }

    /**
     * Description:
     *  返回 t_auth_user.phone 的值
     *
     * @return 字段 t_auth_user.phone 的值
     *
     * @mbg.generated
     */
    public String getPhone() {
        return phone;
    }

    /**
     * Description:
     *  设置 t_auth_user.phone 的值.
     *
     * @param phone 字段 t_auth_user.phone 的值.
     *
     * @mbg.generated
     */
    public void setPhone(String phone) {
        this.phone = phone == null ? null : phone.trim();
    }

    /**
     * Description:
     *  返回 t_auth_user.email 的值
     *
     * @return 字段 t_auth_user.email 的值
     *
     * @mbg.generated
     */
    public String getEmail() {
        return email;
    }

    /**
     * Description:
     *  设置 t_auth_user.email 的值.
     *
     * @param email 字段 t_auth_user.email 的值.
     *
     * @mbg.generated
     */
    public void setEmail(String email) {
        this.email = email == null ? null : email.trim();
    }

    /**
     * Description:
     *  返回 t_auth_user.user_type 的值
     *
     * @return 字段 t_auth_user.user_type 的值
     *
     * @mbg.generated
     */
    public String getUserType() {
        return userType;
    }

    /**
     * Description:
     *  设置 t_auth_user.user_type 的值.
     *
     * @param userType 字段 t_auth_user.user_type 的值.
     *
     * @mbg.generated
     */
    public void setUserType(String userType) {
        this.userType = userType == null ? null : userType.trim();
    }

    /**
     * Description:
     *  返回 t_auth_user.wechat_open_id 的值
     *
     * @return 字段 t_auth_user.wechat_open_id 的值
     *
     * @mbg.generated
     */
    public String getWechatOpenId() {
        return wechatOpenId;
    }

    /**
     * Description:
     *  设置 t_auth_user.wechat_open_id 的值.
     *
     * @param wechatOpenId 字段 t_auth_user.wechat_open_id 的值.
     *
     * @mbg.generated
     */
    public void setWechatOpenId(String wechatOpenId) {
        this.wechatOpenId = wechatOpenId == null ? null : wechatOpenId.trim();
    }

    /**
     * Description:
     *  返回 t_auth_user.wechat_union_id 的值
     *
     * @return 字段 t_auth_user.wechat_union_id 的值
     *
     * @mbg.generated
     */
    public String getWechatUnionId() {
        return wechatUnionId;
    }

    /**
     * Description:
     *  设置 t_auth_user.wechat_union_id 的值.
     *
     * @param wechatUnionId 字段 t_auth_user.wechat_union_id 的值.
     *
     * @mbg.generated
     */
    public void setWechatUnionId(String wechatUnionId) {
        this.wechatUnionId = wechatUnionId == null ? null : wechatUnionId.trim();
    }

    /**
     * Description:
     *  返回 t_auth_user.enabled 的值
     *
     * @return 字段 t_auth_user.enabled 的值
     *
     * @mbg.generated
     */
    public Integer getEnabled() {
        return enabled;
    }

    /**
     * Description:
     *  设置 t_auth_user.enabled 的值.
     *
     * @param enabled 字段 t_auth_user.enabled 的值.
     *
     * @mbg.generated
     */
    public void setEnabled(Integer enabled) {
        this.enabled = enabled;
    }

    /**
     * Description:
     *  返回 t_auth_user.create_time 的值
     *
     * @return 字段 t_auth_user.create_time 的值
     *
     * @mbg.generated
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * Description:
     *  设置 t_auth_user.create_time 的值.
     *
     * @param createTime 字段 t_auth_user.create_time 的值.
     *
     * @mbg.generated
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    /**
     * Description:
     *  返回 t_auth_user.update_time 的值
     *
     * @return 字段 t_auth_user.update_time 的值
     *
     * @mbg.generated
     */
    public Date getUpdateTime() {
        return updateTime;
    }

    /**
     * Description:
     *  设置 t_auth_user.update_time 的值.
     *
     * @param updateTime 字段 t_auth_user.update_time 的值.
     *
     * @mbg.generated
     */
    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    /**
     * Description:
     *  返回 t_auth_user.last_password_reset_date 的值
     *
     * @return 字段 t_auth_user.last_password_reset_date 的值
     *
     * @mbg.generated
     */
    public Date getLastPasswordResetDate() {
        return lastPasswordResetDate;
    }

    /**
     * Description:
     *  设置 t_auth_user.last_password_reset_date 的值.
     *
     * @param lastPasswordResetDate 字段 t_auth_user.last_password_reset_date 的值.
     *
     * @mbg.generated
     */
    public void setLastPasswordResetDate(Date lastPasswordResetDate) {
        this.lastPasswordResetDate = lastPasswordResetDate;
    }

    /**
     * Description: This method was generated by MyBatis Generator.
     *
     * @return toString 的结果.
     * @mbg.generated
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", userName=").append(userName);
        sb.append(", nickName=").append(nickName);
        sb.append(", password=").append(password);
        sb.append(", phone=").append(phone);
        sb.append(", email=").append(email);
        sb.append(", userType=").append(userType);
        sb.append(", wechatOpenId=").append(wechatOpenId);
        sb.append(", wechatUnionId=").append(wechatUnionId);
        sb.append(", enabled=").append(enabled);
        sb.append(", createTime=").append(createTime);
        sb.append(", updateTime=").append(updateTime);
        sb.append(", lastPasswordResetDate=").append(lastPasswordResetDate);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }

    /**
     * Description: This method was generated by MyBatis Generator.
     *
     * @return builder 的结果.
     * @mbg.generated
     */
    public static AuthUser.Builder builder() {
        return new AuthUser.Builder();
    }

    /**
     * Description: This method was generated by MyBatis Generator.
     *
     * @return hasSelective 的结果.
     * @mbg.generated
     */
    public boolean hasSelective() {
        return this.selectiveColumns.size() > 0;
    }

    /**
     * Description: This method was generated by MyBatis Generator.
     *
     * @return hasSelective 的结果.
     * @mbg.generated
     */
    public boolean hasSelective(String column) {
        return this.selectiveColumns.get(column) != null;
    }

    /**
     * Description: This method was generated by MyBatis Generator.
     *
     * @return selective 的结果.
     * @mbg.generated
     */
    public AuthUser selective(Column ... columns) {
        this.selectiveColumns.clear();
        if (columns != null) {
            for (Column column : columns) {
                this.selectiveColumns.put(column.value(), true);
            }
        }
        return this;
    }

    /**
     * Description:
     *  This class corresponds to the database table t_auth_user
     *
     * @author Ant丶
     * @mbg.generated
     */
    public static class Builder {
        /**
         * Description:
         *  This field corresponds to the database table t_auth_user
         *
         * @mbg.generated
         */
        private AuthUser obj;

        /**
         * Description: This method was generated by MyBatis Generator.
         *
         * @return Builder 的结果.
         * @mbg.generated
         */
        public Builder() {
            this.obj = new AuthUser();
        }

        /**
         * Description:
         *  设置 t_auth_user.id 的值.
         *
         * @param id 字段 t_auth_user.id 的值.
         *
         * @mbg.generated
         */
        public Builder id(Long id) {
            obj.setId(id);
            return this;
        }

        /**
         * Description:
         *  设置 t_auth_user.user_name 的值.
         *
         * @param userName 字段 t_auth_user.user_name 的值.
         *
         * @mbg.generated
         */
        public Builder userName(String userName) {
            obj.setUserName(userName);
            return this;
        }

        /**
         * Description:
         *  设置 t_auth_user.nick_name 的值.
         *
         * @param nickName 字段 t_auth_user.nick_name 的值.
         *
         * @mbg.generated
         */
        public Builder nickName(String nickName) {
            obj.setNickName(nickName);
            return this;
        }

        /**
         * Description:
         *  设置 t_auth_user.password 的值.
         *
         * @param password 字段 t_auth_user.password 的值.
         *
         * @mbg.generated
         */
        public Builder password(String password) {
            obj.setPassword(password);
            return this;
        }

        /**
         * Description:
         *  设置 t_auth_user.phone 的值.
         *
         * @param phone 字段 t_auth_user.phone 的值.
         *
         * @mbg.generated
         */
        public Builder phone(String phone) {
            obj.setPhone(phone);
            return this;
        }

        /**
         * Description:
         *  设置 t_auth_user.email 的值.
         *
         * @param email 字段 t_auth_user.email 的值.
         *
         * @mbg.generated
         */
        public Builder email(String email) {
            obj.setEmail(email);
            return this;
        }

        /**
         * Description:
         *  设置 t_auth_user.user_type 的值.
         *
         * @param userType 字段 t_auth_user.user_type 的值.
         *
         * @mbg.generated
         */
        public Builder userType(String userType) {
            obj.setUserType(userType);
            return this;
        }

        /**
         * Description:
         *  设置 t_auth_user.wechat_open_id 的值.
         *
         * @param wechatOpenId 字段 t_auth_user.wechat_open_id 的值.
         *
         * @mbg.generated
         */
        public Builder wechatOpenId(String wechatOpenId) {
            obj.setWechatOpenId(wechatOpenId);
            return this;
        }

        /**
         * Description:
         *  设置 t_auth_user.wechat_union_id 的值.
         *
         * @param wechatUnionId 字段 t_auth_user.wechat_union_id 的值.
         *
         * @mbg.generated
         */
        public Builder wechatUnionId(String wechatUnionId) {
            obj.setWechatUnionId(wechatUnionId);
            return this;
        }

        /**
         * Description:
         *  设置 t_auth_user.enabled 的值.
         *
         * @param enabled 字段 t_auth_user.enabled 的值.
         *
         * @mbg.generated
         */
        public Builder enabled(Integer enabled) {
            obj.setEnabled(enabled);
            return this;
        }

        /**
         * Description:
         *  设置 t_auth_user.create_time 的值.
         *
         * @param createTime 字段 t_auth_user.create_time 的值.
         *
         * @mbg.generated
         */
        public Builder createTime(Date createTime) {
            obj.setCreateTime(createTime);
            return this;
        }

        /**
         * Description:
         *  设置 t_auth_user.update_time 的值.
         *
         * @param updateTime 字段 t_auth_user.update_time 的值.
         *
         * @mbg.generated
         */
        public Builder updateTime(Date updateTime) {
            obj.setUpdateTime(updateTime);
            return this;
        }

        /**
         * Description:
         *  设置 t_auth_user.last_password_reset_date 的值.
         *
         * @param lastPasswordResetDate 字段 t_auth_user.last_password_reset_date 的值.
         *
         * @mbg.generated
         */
        public Builder lastPasswordResetDate(Date lastPasswordResetDate) {
            obj.setLastPasswordResetDate(lastPasswordResetDate);
            return this;
        }

        /**
         * Description: This method was generated by MyBatis Generator.
         *
         * @return build 的结果.
         * @mbg.generated
         */
        public AuthUser build() {
            return this.obj;
        }
    }

    /**
     * This enum was generated by MyBatis Generator.
     * This enum corresponds to the database table t_auth_user
     *
     * @mbg.generated
     */
    public enum Column {
        id("id"),
        userName("user_name"),
        nickName("nick_name"),
        password("password"),
        phone("phone"),
        email("email"),
        userType("user_type"),
        wechatOpenId("wechat_open_id"),
        wechatUnionId("wechat_union_id"),
        enabled("enabled"),
        createTime("create_time"),
        updateTime("update_time"),
        lastPasswordResetDate("last_password_reset_date");

        /**
         * Description:
         *  This field corresponds to the database table t_auth_user
         *
         * @mbg.generated
         */
        private final String column;

        /**
         * Description: This method was generated by MyBatis Generator.
         *
         * @return value 的结果.
         * @mbg.generated
         */
        public String value() {
            return this.column;
        }

        /**
         * Description: This method was generated by MyBatis Generator.
         *
         * @return getValue 的结果.
         * @mbg.generated
         */
        public String getValue() {
            return this.column;
        }

        /**
         * Description: This method was generated by MyBatis Generator.
         *
         * @param  column column
         * @return Column 的结果.
         * @mbg.generated
         */
        Column(String column) {
            this.column = column;
        }

        /**
         * Description: This method was generated by MyBatis Generator.
         *
         * @return desc 的结果.
         * @mbg.generated
         */
        public String desc() {
            return this.column + " DESC";
        }

        /**
         * Description: This method was generated by MyBatis Generator.
         *
         * @return asc 的结果.
         * @mbg.generated
         */
        public String asc() {
            return this.column + " ASC";
        }
    }
}