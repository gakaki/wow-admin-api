package com.wow.adminapi.request.product;

import com.wow.common.request.ApiRequest;

import java.util.List;

/**
 * Created by zhengzhiqing on 16/7/21.
 */
public class ProductCreateRequest extends ApiRequest {

    private static final long serialVersionUID = 1L;

    private int categoryId;
    private String productName;
    private String sellingPoint;
    private String productModel;
    private int brandId;
    private List<Integer> designerList;
    private String originCountry;
    private String originCity;
    private int styleId;
    private Short length;
    private Short width;
    private Short height;
    private short applicablePeople;
    private boolean canCustomized;
    private List<Integer> applicableSceneList;
    private List<Integer> materialList;
    private String detailDescription; //商品描述
    private List<ColorSpecVo> colorSpecVoList;
    private List<ProductImgVo> productImgVoList;

    public int getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getSellingPoint() {
        return sellingPoint;
    }

    public void setSellingPoint(String sellingPoint) {
        this.sellingPoint = sellingPoint;
    }

    public String getProductModel() {
        return productModel;
    }

    public void setProductModel(String productModel) {
        this.productModel = productModel;
    }

    public int getBrandId() {
        return brandId;
    }

    public void setBrandId(int brandId) {
        this.brandId = brandId;
    }

    public List<Integer> getDesignerList() {
        return designerList;
    }

    public void setDesignerList(List<Integer> designerList) {
        this.designerList = designerList;
    }

    public String getOriginCountry() {
        return originCountry;
    }

    public void setOriginCountry(String originCountry) {
        this.originCountry = originCountry;
    }

    public String getOriginCity() {
        return originCity;
    }

    public void setOriginCity(String originCity) {
        this.originCity = originCity;
    }

    public int getStyleId() {
        return styleId;
    }

    public void setStyleId(int styleId) {
        this.styleId = styleId;
    }

    public Short getLength() {
        return length;
    }

    public void setLength(Short length) {
        this.length = length;
    }

    public Short getWidth() {
        return width;
    }

    public void setWidth(Short width) {
        this.width = width;
    }

    public Short getHeight() {
        return height;
    }

    public void setHeight(Short height) {
        this.height = height;
    }

    public short getApplicablePeople() {
        return applicablePeople;
    }

    public void setApplicablePeople(short applicablePeople) {
        this.applicablePeople = applicablePeople;
    }

    public boolean isCanCustomized() {
        return canCustomized;
    }

    public void setCanCustomized(boolean canCustomized) {
        this.canCustomized = canCustomized;
    }

    public List<Integer> getApplicableSceneList() {
        return applicableSceneList;
    }

    public void setApplicableSceneList(List<Integer> applicableSceneList) {
        this.applicableSceneList = applicableSceneList;
    }

    public List<Integer> getMaterialList() {
        return materialList;
    }

    public void setMaterialList(List<Integer> materialList) {
        this.materialList = materialList;
    }

    public String getDetailDescription() {
        return detailDescription;
    }

    public void setDetailDescription(String detailDescription) {
        this.detailDescription = detailDescription;
    }

    public List<ColorSpecVo> getColorSpecVoList() {
        return colorSpecVoList;
    }

    public void setColorSpecVoList(List<ColorSpecVo> colorSpecVoList) {
        this.colorSpecVoList = colorSpecVoList;
    }

    public List<ProductImgVo> getProductImgVoList() {
        return productImgVoList;
    }

    public void setProductImgVoList(List<ProductImgVo> productImgVoList) {
        this.productImgVoList = productImgVoList;
    }
}
