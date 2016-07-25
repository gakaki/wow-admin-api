package com.wow.adminapi.request.product;

import java.io.Serializable;

/**
 * Created by zhengzhiqing on 16/7/21.
 */
public class ProductImgVo implements Serializable {

    private static final long serialVersionUID = 1L;

    private String imgUrl;

    private String imgDesc;

    private Boolean isPrimary;

    private Byte sortOrder;

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public String getImgDesc() {
        return imgDesc;
    }

    public void setImgDesc(String imgDesc) {
        this.imgDesc = imgDesc;
    }

    public Byte getSortOrder() {
        return sortOrder;
    }

    public void setSortOrder(Byte sortOrder) {
        this.sortOrder = sortOrder;
    }

    public Boolean getPrimary() {
        return isPrimary;
    }

    public void setPrimary(Boolean primary) {
        isPrimary = primary;
    }
}
