package com.wow.adminapi.model;

import java.io.Serializable;
import java.util.Date;

public class EmployeeSession implements Serializable {
    private Integer id;

    private Integer employeeId;

    private String sessionToken;

    private String userAgentInfo;

    private Date lastLoginTime;

    private Long lastLoginIp;

    private Date lastRefreshTime;

    private Boolean isLogout;

    private Date logoutTime;

    private Boolean isActive;

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

    public String getSessionToken() {
        return sessionToken;
    }

    public void setSessionToken(String sessionToken) {
        this.sessionToken = sessionToken == null ? null : sessionToken.trim();
    }

    public String getUserAgentInfo() {
        return userAgentInfo;
    }

    public void setUserAgentInfo(String userAgentInfo) {
        this.userAgentInfo = userAgentInfo == null ? null : userAgentInfo.trim();
    }

    public Date getLastLoginTime() {
        return lastLoginTime;
    }

    public void setLastLoginTime(Date lastLoginTime) {
        this.lastLoginTime = lastLoginTime;
    }

    public Long getLastLoginIp() {
        return lastLoginIp;
    }

    public void setLastLoginIp(Long lastLoginIp) {
        this.lastLoginIp = lastLoginIp;
    }

    public Date getLastRefreshTime() {
        return lastRefreshTime;
    }

    public void setLastRefreshTime(Date lastRefreshTime) {
        this.lastRefreshTime = lastRefreshTime;
    }

    public Boolean getIsLogout() {
        return isLogout;
    }

    public void setIsLogout(Boolean isLogout) {
        this.isLogout = isLogout;
    }

    public Date getLogoutTime() {
        return logoutTime;
    }

    public void setLogoutTime(Date logoutTime) {
        this.logoutTime = logoutTime;
    }

    public Boolean getIsActive() {
        return isActive;
    }

    public void setIsActive(Boolean isActive) {
        this.isActive = isActive;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", employeeId=").append(employeeId);
        sb.append(", sessionToken=").append(sessionToken);
        sb.append(", userAgentInfo=").append(userAgentInfo);
        sb.append(", lastLoginTime=").append(lastLoginTime);
        sb.append(", lastLoginIp=").append(lastLoginIp);
        sb.append(", lastRefreshTime=").append(lastRefreshTime);
        sb.append(", isLogout=").append(isLogout);
        sb.append(", logoutTime=").append(logoutTime);
        sb.append(", isActive=").append(isActive);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}