package com.wow.adminapi.model;

import java.io.Serializable;
import java.util.Date;

public class EmployeeLoginLog implements Serializable {
    private Integer id;

    private Integer employeeId;

    private String userAgentInfo;

    private Long loginIp;

    private Date loginTime;

    private String sessionToken;

    private static final long serialVersionUID = 1L;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(Integer employeeId) {
        this.employeeId = employeeId;
    }

    public String getUserAgentInfo() {
        return userAgentInfo;
    }

    public void setUserAgentInfo(String userAgentInfo) {
        this.userAgentInfo = userAgentInfo == null ? null : userAgentInfo.trim();
    }

    public Long getLoginIp() {
        return loginIp;
    }

    public void setLoginIp(Long loginIp) {
        this.loginIp = loginIp;
    }

    public Date getLoginTime() {
        return loginTime;
    }

    public void setLoginTime(Date loginTime) {
        this.loginTime = loginTime;
    }

    public String getSessionToken() {
        return sessionToken;
    }

    public void setSessionToken(String sessionToken) {
        this.sessionToken = sessionToken == null ? null : sessionToken.trim();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", employeeId=").append(employeeId);
        sb.append(", userAgentInfo=").append(userAgentInfo);
        sb.append(", loginIp=").append(loginIp);
        sb.append(", loginTime=").append(loginTime);
        sb.append(", sessionToken=").append(sessionToken);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}