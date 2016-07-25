package com.wow.adminapi.vo;

import com.wow.common.request.ApiRequest;

/**
 * Created by zhengzhiqing on 16/7/6.
 */
public class RegisterRequest extends ApiRequest {

    /**  */
    private static final long serialVersionUID = 1L;

    private String userName;

    private String password;

    private String realName;

    private String nickName;

    private String mobile;

    private String email;

    private Integer employeeNo;

    private Byte sex;

    private String team;

    private Boolean isTeamLeader;

    private String createBy;

    private String updateBy;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRealName() {
        return realName;
    }

    public void setRealName(String realName) {
        this.realName = realName;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Integer getEmployeeNo() {
        return employeeNo;
    }

    public void setEmployeeNo(Integer employeeNo) {
        this.employeeNo = employeeNo;
    }

    public Byte getSex() {
        return sex;
    }

    public void setSex(Byte sex) {
        this.sex = sex;
    }

    public String getTeam() {
        return team;
    }

    public void setTeam(String team) {
        this.team = team;
    }

    public Boolean getTeamLeader() {
        return isTeamLeader;
    }

    public void setTeamLeader(Boolean teamLeader) {
        isTeamLeader = teamLeader;
    }

    public String getCreateBy() {
        return createBy;
    }

    public void setCreateBy(String createBy) {
        this.createBy = createBy;
    }

    public String getUpdateBy() {
        return updateBy;
    }

    public void setUpdateBy(String updateBy) {
        this.updateBy = updateBy;
    }
}
