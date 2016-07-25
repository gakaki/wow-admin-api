package com.wow.adminapi.request.product;

import java.io.Serializable;
import java.util.List;

/**
 * Created by zhengzhiqing on 16/7/21.
 */
public class ColorSpecVo implements Serializable {

    private static final long serialVersionUID = 1L;

    private int color;
    private String colorImg;

    List<SpecVo> specVoList;

    public int getColor() {
        return color;
    }

    public void setColor(int color) {
        this.color = color;
    }

    public String getColorImg() {
        return colorImg;
    }

    public void setColorImg(String colorImg) {
        this.colorImg = colorImg;
    }

    public List<SpecVo> getSpecVoList() {
        return specVoList;
    }

    public void setSpecVoList(List<SpecVo> specVoList) {
        this.specVoList = specVoList;
    }
}
