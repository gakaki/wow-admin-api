package com.wow.adminapi.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class EmployeeSessionExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public EmployeeSessionExample() {
        oredCriteria = new ArrayList<Criteria>();
    }

    public void setOrderByClause(String orderByClause) {
        this.orderByClause = orderByClause;
    }

    public String getOrderByClause() {
        return orderByClause;
    }

    public void setDistinct(boolean distinct) {
        this.distinct = distinct;
    }

    public boolean isDistinct() {
        return distinct;
    }

    public List<Criteria> getOredCriteria() {
        return oredCriteria;
    }

    public void or(Criteria criteria) {
        oredCriteria.add(criteria);
    }

    public Criteria or() {
        Criteria criteria = createCriteriaInternal();
        oredCriteria.add(criteria);
        return criteria;
    }

    public Criteria createCriteria() {
        Criteria criteria = createCriteriaInternal();
        if (oredCriteria.size() == 0) {
            oredCriteria.add(criteria);
        }
        return criteria;
    }

    protected Criteria createCriteriaInternal() {
        Criteria criteria = new Criteria();
        return criteria;
    }

    public void clear() {
        oredCriteria.clear();
        orderByClause = null;
        distinct = false;
    }

    protected abstract static class GeneratedCriteria {
        protected List<Criterion> criteria;

        protected GeneratedCriteria() {
            super();
            criteria = new ArrayList<Criterion>();
        }

        public boolean isValid() {
            return criteria.size() > 0;
        }

        public List<Criterion> getAllCriteria() {
            return criteria;
        }

        public List<Criterion> getCriteria() {
            return criteria;
        }

        protected void addCriterion(String condition) {
            if (condition == null) {
                throw new RuntimeException("Value for condition cannot be null");
            }
            criteria.add(new Criterion(condition));
        }

        protected void addCriterion(String condition, Object value, String property) {
            if (value == null) {
                throw new RuntimeException("Value for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value));
        }

        protected void addCriterion(String condition, Object value1, Object value2, String property) {
            if (value1 == null || value2 == null) {
                throw new RuntimeException("Between values for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value1, value2));
        }

        public Criteria andIdIsNull() {
            addCriterion("id is null");
            return (Criteria) this;
        }

        public Criteria andIdIsNotNull() {
            addCriterion("id is not null");
            return (Criteria) this;
        }

        public Criteria andIdEqualTo(Integer value) {
            addCriterion("id =", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotEqualTo(Integer value) {
            addCriterion("id <>", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThan(Integer value) {
            addCriterion("id >", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("id >=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThan(Integer value) {
            addCriterion("id <", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThanOrEqualTo(Integer value) {
            addCriterion("id <=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdIn(List<Integer> values) {
            addCriterion("id in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotIn(List<Integer> values) {
            addCriterion("id not in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdBetween(Integer value1, Integer value2) {
            addCriterion("id between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotBetween(Integer value1, Integer value2) {
            addCriterion("id not between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andEmployeeIdIsNull() {
            addCriterion("employee_id is null");
            return (Criteria) this;
        }

        public Criteria andEmployeeIdIsNotNull() {
            addCriterion("employee_id is not null");
            return (Criteria) this;
        }

        public Criteria andEmployeeIdEqualTo(Integer value) {
            addCriterion("employee_id =", value, "employeeId");
            return (Criteria) this;
        }

        public Criteria andEmployeeIdNotEqualTo(Integer value) {
            addCriterion("employee_id <>", value, "employeeId");
            return (Criteria) this;
        }

        public Criteria andEmployeeIdGreaterThan(Integer value) {
            addCriterion("employee_id >", value, "employeeId");
            return (Criteria) this;
        }

        public Criteria andEmployeeIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("employee_id >=", value, "employeeId");
            return (Criteria) this;
        }

        public Criteria andEmployeeIdLessThan(Integer value) {
            addCriterion("employee_id <", value, "employeeId");
            return (Criteria) this;
        }

        public Criteria andEmployeeIdLessThanOrEqualTo(Integer value) {
            addCriterion("employee_id <=", value, "employeeId");
            return (Criteria) this;
        }

        public Criteria andEmployeeIdIn(List<Integer> values) {
            addCriterion("employee_id in", values, "employeeId");
            return (Criteria) this;
        }

        public Criteria andEmployeeIdNotIn(List<Integer> values) {
            addCriterion("employee_id not in", values, "employeeId");
            return (Criteria) this;
        }

        public Criteria andEmployeeIdBetween(Integer value1, Integer value2) {
            addCriterion("employee_id between", value1, value2, "employeeId");
            return (Criteria) this;
        }

        public Criteria andEmployeeIdNotBetween(Integer value1, Integer value2) {
            addCriterion("employee_id not between", value1, value2, "employeeId");
            return (Criteria) this;
        }

        public Criteria andSessionTokenIsNull() {
            addCriterion("session_token is null");
            return (Criteria) this;
        }

        public Criteria andSessionTokenIsNotNull() {
            addCriterion("session_token is not null");
            return (Criteria) this;
        }

        public Criteria andSessionTokenEqualTo(String value) {
            addCriterion("session_token =", value, "sessionToken");
            return (Criteria) this;
        }

        public Criteria andSessionTokenNotEqualTo(String value) {
            addCriterion("session_token <>", value, "sessionToken");
            return (Criteria) this;
        }

        public Criteria andSessionTokenGreaterThan(String value) {
            addCriterion("session_token >", value, "sessionToken");
            return (Criteria) this;
        }

        public Criteria andSessionTokenGreaterThanOrEqualTo(String value) {
            addCriterion("session_token >=", value, "sessionToken");
            return (Criteria) this;
        }

        public Criteria andSessionTokenLessThan(String value) {
            addCriterion("session_token <", value, "sessionToken");
            return (Criteria) this;
        }

        public Criteria andSessionTokenLessThanOrEqualTo(String value) {
            addCriterion("session_token <=", value, "sessionToken");
            return (Criteria) this;
        }

        public Criteria andSessionTokenLike(String value) {
            addCriterion("session_token like", value, "sessionToken");
            return (Criteria) this;
        }

        public Criteria andSessionTokenNotLike(String value) {
            addCriterion("session_token not like", value, "sessionToken");
            return (Criteria) this;
        }

        public Criteria andSessionTokenIn(List<String> values) {
            addCriterion("session_token in", values, "sessionToken");
            return (Criteria) this;
        }

        public Criteria andSessionTokenNotIn(List<String> values) {
            addCriterion("session_token not in", values, "sessionToken");
            return (Criteria) this;
        }

        public Criteria andSessionTokenBetween(String value1, String value2) {
            addCriterion("session_token between", value1, value2, "sessionToken");
            return (Criteria) this;
        }

        public Criteria andSessionTokenNotBetween(String value1, String value2) {
            addCriterion("session_token not between", value1, value2, "sessionToken");
            return (Criteria) this;
        }

        public Criteria andUserAgentInfoIsNull() {
            addCriterion("user_agent_info is null");
            return (Criteria) this;
        }

        public Criteria andUserAgentInfoIsNotNull() {
            addCriterion("user_agent_info is not null");
            return (Criteria) this;
        }

        public Criteria andUserAgentInfoEqualTo(String value) {
            addCriterion("user_agent_info =", value, "userAgentInfo");
            return (Criteria) this;
        }

        public Criteria andUserAgentInfoNotEqualTo(String value) {
            addCriterion("user_agent_info <>", value, "userAgentInfo");
            return (Criteria) this;
        }

        public Criteria andUserAgentInfoGreaterThan(String value) {
            addCriterion("user_agent_info >", value, "userAgentInfo");
            return (Criteria) this;
        }

        public Criteria andUserAgentInfoGreaterThanOrEqualTo(String value) {
            addCriterion("user_agent_info >=", value, "userAgentInfo");
            return (Criteria) this;
        }

        public Criteria andUserAgentInfoLessThan(String value) {
            addCriterion("user_agent_info <", value, "userAgentInfo");
            return (Criteria) this;
        }

        public Criteria andUserAgentInfoLessThanOrEqualTo(String value) {
            addCriterion("user_agent_info <=", value, "userAgentInfo");
            return (Criteria) this;
        }

        public Criteria andUserAgentInfoLike(String value) {
            addCriterion("user_agent_info like", value, "userAgentInfo");
            return (Criteria) this;
        }

        public Criteria andUserAgentInfoNotLike(String value) {
            addCriterion("user_agent_info not like", value, "userAgentInfo");
            return (Criteria) this;
        }

        public Criteria andUserAgentInfoIn(List<String> values) {
            addCriterion("user_agent_info in", values, "userAgentInfo");
            return (Criteria) this;
        }

        public Criteria andUserAgentInfoNotIn(List<String> values) {
            addCriterion("user_agent_info not in", values, "userAgentInfo");
            return (Criteria) this;
        }

        public Criteria andUserAgentInfoBetween(String value1, String value2) {
            addCriterion("user_agent_info between", value1, value2, "userAgentInfo");
            return (Criteria) this;
        }

        public Criteria andUserAgentInfoNotBetween(String value1, String value2) {
            addCriterion("user_agent_info not between", value1, value2, "userAgentInfo");
            return (Criteria) this;
        }

        public Criteria andLastLoginTimeIsNull() {
            addCriterion("last_login_time is null");
            return (Criteria) this;
        }

        public Criteria andLastLoginTimeIsNotNull() {
            addCriterion("last_login_time is not null");
            return (Criteria) this;
        }

        public Criteria andLastLoginTimeEqualTo(Date value) {
            addCriterion("last_login_time =", value, "lastLoginTime");
            return (Criteria) this;
        }

        public Criteria andLastLoginTimeNotEqualTo(Date value) {
            addCriterion("last_login_time <>", value, "lastLoginTime");
            return (Criteria) this;
        }

        public Criteria andLastLoginTimeGreaterThan(Date value) {
            addCriterion("last_login_time >", value, "lastLoginTime");
            return (Criteria) this;
        }

        public Criteria andLastLoginTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("last_login_time >=", value, "lastLoginTime");
            return (Criteria) this;
        }

        public Criteria andLastLoginTimeLessThan(Date value) {
            addCriterion("last_login_time <", value, "lastLoginTime");
            return (Criteria) this;
        }

        public Criteria andLastLoginTimeLessThanOrEqualTo(Date value) {
            addCriterion("last_login_time <=", value, "lastLoginTime");
            return (Criteria) this;
        }

        public Criteria andLastLoginTimeIn(List<Date> values) {
            addCriterion("last_login_time in", values, "lastLoginTime");
            return (Criteria) this;
        }

        public Criteria andLastLoginTimeNotIn(List<Date> values) {
            addCriterion("last_login_time not in", values, "lastLoginTime");
            return (Criteria) this;
        }

        public Criteria andLastLoginTimeBetween(Date value1, Date value2) {
            addCriterion("last_login_time between", value1, value2, "lastLoginTime");
            return (Criteria) this;
        }

        public Criteria andLastLoginTimeNotBetween(Date value1, Date value2) {
            addCriterion("last_login_time not between", value1, value2, "lastLoginTime");
            return (Criteria) this;
        }

        public Criteria andLastLoginIpIsNull() {
            addCriterion("last_login_ip is null");
            return (Criteria) this;
        }

        public Criteria andLastLoginIpIsNotNull() {
            addCriterion("last_login_ip is not null");
            return (Criteria) this;
        }

        public Criteria andLastLoginIpEqualTo(Long value) {
            addCriterion("last_login_ip =", value, "lastLoginIp");
            return (Criteria) this;
        }

        public Criteria andLastLoginIpNotEqualTo(Long value) {
            addCriterion("last_login_ip <>", value, "lastLoginIp");
            return (Criteria) this;
        }

        public Criteria andLastLoginIpGreaterThan(Long value) {
            addCriterion("last_login_ip >", value, "lastLoginIp");
            return (Criteria) this;
        }

        public Criteria andLastLoginIpGreaterThanOrEqualTo(Long value) {
            addCriterion("last_login_ip >=", value, "lastLoginIp");
            return (Criteria) this;
        }

        public Criteria andLastLoginIpLessThan(Long value) {
            addCriterion("last_login_ip <", value, "lastLoginIp");
            return (Criteria) this;
        }

        public Criteria andLastLoginIpLessThanOrEqualTo(Long value) {
            addCriterion("last_login_ip <=", value, "lastLoginIp");
            return (Criteria) this;
        }

        public Criteria andLastLoginIpIn(List<Long> values) {
            addCriterion("last_login_ip in", values, "lastLoginIp");
            return (Criteria) this;
        }

        public Criteria andLastLoginIpNotIn(List<Long> values) {
            addCriterion("last_login_ip not in", values, "lastLoginIp");
            return (Criteria) this;
        }

        public Criteria andLastLoginIpBetween(Long value1, Long value2) {
            addCriterion("last_login_ip between", value1, value2, "lastLoginIp");
            return (Criteria) this;
        }

        public Criteria andLastLoginIpNotBetween(Long value1, Long value2) {
            addCriterion("last_login_ip not between", value1, value2, "lastLoginIp");
            return (Criteria) this;
        }

        public Criteria andLastRefreshTimeIsNull() {
            addCriterion("last_refresh_time is null");
            return (Criteria) this;
        }

        public Criteria andLastRefreshTimeIsNotNull() {
            addCriterion("last_refresh_time is not null");
            return (Criteria) this;
        }

        public Criteria andLastRefreshTimeEqualTo(Date value) {
            addCriterion("last_refresh_time =", value, "lastRefreshTime");
            return (Criteria) this;
        }

        public Criteria andLastRefreshTimeNotEqualTo(Date value) {
            addCriterion("last_refresh_time <>", value, "lastRefreshTime");
            return (Criteria) this;
        }

        public Criteria andLastRefreshTimeGreaterThan(Date value) {
            addCriterion("last_refresh_time >", value, "lastRefreshTime");
            return (Criteria) this;
        }

        public Criteria andLastRefreshTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("last_refresh_time >=", value, "lastRefreshTime");
            return (Criteria) this;
        }

        public Criteria andLastRefreshTimeLessThan(Date value) {
            addCriterion("last_refresh_time <", value, "lastRefreshTime");
            return (Criteria) this;
        }

        public Criteria andLastRefreshTimeLessThanOrEqualTo(Date value) {
            addCriterion("last_refresh_time <=", value, "lastRefreshTime");
            return (Criteria) this;
        }

        public Criteria andLastRefreshTimeIn(List<Date> values) {
            addCriterion("last_refresh_time in", values, "lastRefreshTime");
            return (Criteria) this;
        }

        public Criteria andLastRefreshTimeNotIn(List<Date> values) {
            addCriterion("last_refresh_time not in", values, "lastRefreshTime");
            return (Criteria) this;
        }

        public Criteria andLastRefreshTimeBetween(Date value1, Date value2) {
            addCriterion("last_refresh_time between", value1, value2, "lastRefreshTime");
            return (Criteria) this;
        }

        public Criteria andLastRefreshTimeNotBetween(Date value1, Date value2) {
            addCriterion("last_refresh_time not between", value1, value2, "lastRefreshTime");
            return (Criteria) this;
        }

        public Criteria andIsLogoutIsNull() {
            addCriterion("is_logout is null");
            return (Criteria) this;
        }

        public Criteria andIsLogoutIsNotNull() {
            addCriterion("is_logout is not null");
            return (Criteria) this;
        }

        public Criteria andIsLogoutEqualTo(Boolean value) {
            addCriterion("is_logout =", value, "isLogout");
            return (Criteria) this;
        }

        public Criteria andIsLogoutNotEqualTo(Boolean value) {
            addCriterion("is_logout <>", value, "isLogout");
            return (Criteria) this;
        }

        public Criteria andIsLogoutGreaterThan(Boolean value) {
            addCriterion("is_logout >", value, "isLogout");
            return (Criteria) this;
        }

        public Criteria andIsLogoutGreaterThanOrEqualTo(Boolean value) {
            addCriterion("is_logout >=", value, "isLogout");
            return (Criteria) this;
        }

        public Criteria andIsLogoutLessThan(Boolean value) {
            addCriterion("is_logout <", value, "isLogout");
            return (Criteria) this;
        }

        public Criteria andIsLogoutLessThanOrEqualTo(Boolean value) {
            addCriterion("is_logout <=", value, "isLogout");
            return (Criteria) this;
        }

        public Criteria andIsLogoutIn(List<Boolean> values) {
            addCriterion("is_logout in", values, "isLogout");
            return (Criteria) this;
        }

        public Criteria andIsLogoutNotIn(List<Boolean> values) {
            addCriterion("is_logout not in", values, "isLogout");
            return (Criteria) this;
        }

        public Criteria andIsLogoutBetween(Boolean value1, Boolean value2) {
            addCriterion("is_logout between", value1, value2, "isLogout");
            return (Criteria) this;
        }

        public Criteria andIsLogoutNotBetween(Boolean value1, Boolean value2) {
            addCriterion("is_logout not between", value1, value2, "isLogout");
            return (Criteria) this;
        }

        public Criteria andLogoutTimeIsNull() {
            addCriterion("logout_time is null");
            return (Criteria) this;
        }

        public Criteria andLogoutTimeIsNotNull() {
            addCriterion("logout_time is not null");
            return (Criteria) this;
        }

        public Criteria andLogoutTimeEqualTo(Date value) {
            addCriterion("logout_time =", value, "logoutTime");
            return (Criteria) this;
        }

        public Criteria andLogoutTimeNotEqualTo(Date value) {
            addCriterion("logout_time <>", value, "logoutTime");
            return (Criteria) this;
        }

        public Criteria andLogoutTimeGreaterThan(Date value) {
            addCriterion("logout_time >", value, "logoutTime");
            return (Criteria) this;
        }

        public Criteria andLogoutTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("logout_time >=", value, "logoutTime");
            return (Criteria) this;
        }

        public Criteria andLogoutTimeLessThan(Date value) {
            addCriterion("logout_time <", value, "logoutTime");
            return (Criteria) this;
        }

        public Criteria andLogoutTimeLessThanOrEqualTo(Date value) {
            addCriterion("logout_time <=", value, "logoutTime");
            return (Criteria) this;
        }

        public Criteria andLogoutTimeIn(List<Date> values) {
            addCriterion("logout_time in", values, "logoutTime");
            return (Criteria) this;
        }

        public Criteria andLogoutTimeNotIn(List<Date> values) {
            addCriterion("logout_time not in", values, "logoutTime");
            return (Criteria) this;
        }

        public Criteria andLogoutTimeBetween(Date value1, Date value2) {
            addCriterion("logout_time between", value1, value2, "logoutTime");
            return (Criteria) this;
        }

        public Criteria andLogoutTimeNotBetween(Date value1, Date value2) {
            addCriterion("logout_time not between", value1, value2, "logoutTime");
            return (Criteria) this;
        }

        public Criteria andIsActiveIsNull() {
            addCriterion("is_active is null");
            return (Criteria) this;
        }

        public Criteria andIsActiveIsNotNull() {
            addCriterion("is_active is not null");
            return (Criteria) this;
        }

        public Criteria andIsActiveEqualTo(Boolean value) {
            addCriterion("is_active =", value, "isActive");
            return (Criteria) this;
        }

        public Criteria andIsActiveNotEqualTo(Boolean value) {
            addCriterion("is_active <>", value, "isActive");
            return (Criteria) this;
        }

        public Criteria andIsActiveGreaterThan(Boolean value) {
            addCriterion("is_active >", value, "isActive");
            return (Criteria) this;
        }

        public Criteria andIsActiveGreaterThanOrEqualTo(Boolean value) {
            addCriterion("is_active >=", value, "isActive");
            return (Criteria) this;
        }

        public Criteria andIsActiveLessThan(Boolean value) {
            addCriterion("is_active <", value, "isActive");
            return (Criteria) this;
        }

        public Criteria andIsActiveLessThanOrEqualTo(Boolean value) {
            addCriterion("is_active <=", value, "isActive");
            return (Criteria) this;
        }

        public Criteria andIsActiveIn(List<Boolean> values) {
            addCriterion("is_active in", values, "isActive");
            return (Criteria) this;
        }

        public Criteria andIsActiveNotIn(List<Boolean> values) {
            addCriterion("is_active not in", values, "isActive");
            return (Criteria) this;
        }

        public Criteria andIsActiveBetween(Boolean value1, Boolean value2) {
            addCriterion("is_active between", value1, value2, "isActive");
            return (Criteria) this;
        }

        public Criteria andIsActiveNotBetween(Boolean value1, Boolean value2) {
            addCriterion("is_active not between", value1, value2, "isActive");
            return (Criteria) this;
        }

        public Criteria andSessionTokenLikeInsensitive(String value) {
            addCriterion("upper(session_token) like", value.toUpperCase(), "sessionToken");
            return (Criteria) this;
        }

        public Criteria andUserAgentInfoLikeInsensitive(String value) {
            addCriterion("upper(user_agent_info) like", value.toUpperCase(), "userAgentInfo");
            return (Criteria) this;
        }
    }

    public static class Criteria extends GeneratedCriteria {

        protected Criteria() {
            super();
        }
    }

    public static class Criterion {
        private String condition;

        private Object value;

        private Object secondValue;

        private boolean noValue;

        private boolean singleValue;

        private boolean betweenValue;

        private boolean listValue;

        private String typeHandler;

        public String getCondition() {
            return condition;
        }

        public Object getValue() {
            return value;
        }

        public Object getSecondValue() {
            return secondValue;
        }

        public boolean isNoValue() {
            return noValue;
        }

        public boolean isSingleValue() {
            return singleValue;
        }

        public boolean isBetweenValue() {
            return betweenValue;
        }

        public boolean isListValue() {
            return listValue;
        }

        public String getTypeHandler() {
            return typeHandler;
        }

        protected Criterion(String condition) {
            super();
            this.condition = condition;
            this.typeHandler = null;
            this.noValue = true;
        }

        protected Criterion(String condition, Object value, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.typeHandler = typeHandler;
            if (value instanceof List<?>) {
                this.listValue = true;
            } else {
                this.singleValue = true;
            }
        }

        protected Criterion(String condition, Object value) {
            this(condition, value, null);
        }

        protected Criterion(String condition, Object value, Object secondValue, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.secondValue = secondValue;
            this.typeHandler = typeHandler;
            this.betweenValue = true;
        }

        protected Criterion(String condition, Object value, Object secondValue) {
            this(condition, value, secondValue, null);
        }
    }
}