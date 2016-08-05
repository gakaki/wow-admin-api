package com.wow.adminapi.request.topic;

import com.wow.common.request.ApiRequest;

/**
 * Created by win7 on 2016/8/5.
 */
public class TopicQueryRequest extends ApiRequest {

    private static final long serialVersionUID = 1L;

    private Integer groupId;

    public Integer getGroupId() {
        return groupId;
    }

    public void setGroupId(Integer groupId) {
        this.groupId = groupId;
    }
}
