package com.wow.adminapi.controller;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.wow.common.request.ApiRequest;
import com.wow.common.response.ApiResponse;
import com.wow.common.response.CommonResponse;
import com.wow.common.util.ErrorCodeUtil;
import com.wow.common.util.JsonUtil;
import com.wow.common.util.StringUtil;
import com.wow.common.util.ValidatorUtil;
import com.wow.product.service.ProductService;
import com.wow.product.vo.request.ColorSpecVo;
import com.wow.product.vo.request.DesignerVo;
import com.wow.product.vo.request.ProductCreateRequest;
import com.wow.product.vo.request.ProductImgVo;
import com.wow.product.vo.request.SpecVo;

@RestController
@CrossOrigin(maxAge = 3600)
public class ProductController extends BaseController {

    private static final Logger logger = LoggerFactory.getLogger(ProductController.class);

    @Autowired
    private ProductService productService;

//    @Autowired
//    private ProductSerialService productSerialService;
//
//    @Autowired
//    private PriceService priceService;
//
//    @Autowired
//    private BrandService brandService;
//
//    @Autowired
//    private DesignerService designerService;
//
//    private static final Integer productPrimaryImgCountLimit = 5;

    public static void test(String[] args) {
        ProductCreateRequest request = new ProductCreateRequest();
        request.setCategoryId(1);
        request.setProductName("产品名字");
        request.setProductModel("型号");
        request.setSellingPoint("卖点");
        request.setBrandId(1);
        request.setOriginCountryId(1);
        request.setOriginCity("shanghai");
        request.setStyleId((byte)1);
        request.setLength((short)100);
        request.setWidth((short)100);
        request.setHeight((short)100);
        request.setApplicablePeople((byte)1);
        request.setCanCustomized(false);
        request.setDetailDescription("detail desc");

        List<DesignerVo> designerVoList = new ArrayList<>();
        DesignerVo designerVo = new DesignerVo();
        designerVo.setDesignerId(1);
        designerVo.setPrimary(true);
        DesignerVo designerVo2 = new DesignerVo();
        designerVo2.setDesignerId(2);
        designerVo2.setPrimary(false);
        designerVoList.add(designerVo);
        designerVoList.add(designerVo2);
        request.setDesignerVoList(designerVoList);

        List<Integer> applicableSceneList = new ArrayList<>();
        applicableSceneList.add(1);
        applicableSceneList.add(2);
        request.setApplicableSceneList(applicableSceneList);

        List<Integer> materialList = new ArrayList<>();
        materialList.add(1);
        materialList.add(2);
        request.setMaterialList(materialList);

        List<ProductImgVo> productImageVoList = new ArrayList<>();
        ProductImgVo productImgVo = new ProductImgVo();
        productImgVo.setImgUrl("www.baidu.com");
        productImgVo.setImgDesc("img desc");
        productImgVo.setPrimary(true);
        productImgVo.setSortOrder((byte)1);
        productImageVoList.add(productImgVo);


        ProductImgVo productImgVo1 = new ProductImgVo();
        productImgVo1.setImgUrl("www.baidu.com");
        productImgVo1.setImgDesc("img desc");
        productImgVo1.setPrimary(false);
        productImgVo1.setSortOrder((byte)1);
        productImageVoList.add(productImgVo1);
        request.setProductImgVoList(productImageVoList);

        List<ColorSpecVo> colorSpecVoList = new ArrayList<>();
        ColorSpecVo colorSpecVo = new ColorSpecVo();
        colorSpecVo.setColorDisplayName("深红色");
        colorSpecVo.setColorId((byte)1);
        colorSpecVo.setProductColorImg("www.baidu.com");
        List<SpecVo> specVoList = new ArrayList<>();
        SpecVo specVo = new SpecVo();
        specVo.setArrivalLatency((byte)10);
        specVo.setCostPrice(BigDecimal.TEN);
        specVo.setEnabled(true);
        specVo.setSellPrice(BigDecimal.TEN);
        specVo.setSpecName("规格名");
        specVo.setWeight(BigDecimal.TEN);
        specVoList.add(specVo);
        colorSpecVo.setSpecVoList(specVoList);
        colorSpecVoList.add(colorSpecVo);
        request.setColorSpecVoList(colorSpecVoList);
        String json = JsonUtil.pojo2Json(request);
        System.out.println(json);

        ProductCreateRequest productCreateRequest = JsonUtil.fromJSON(json, ProductCreateRequest.class);
        //判断json格式参数是否有误
        if (productCreateRequest == null) {
            System.out.println("failed");
        } else {
            System.out.println("success");
        }

    }

    /**
     * 创建产品
     * @param apiRequest
     * @return
     */
    @RequestMapping(value = "/v1/product/create", method = RequestMethod.POST)
    public ApiResponse createProductInfo(ApiRequest apiRequest) {
        ApiResponse apiResponse = new ApiResponse();
        ProductCreateRequest productCreateRequest = JsonUtil.fromJSON(apiRequest.getParamJson(), ProductCreateRequest.class);
        //判断json格式参数是否有误
        if (productCreateRequest == null) {
            setParamJsonParseErrorResponse(apiResponse);
            return apiResponse;
        }

        String errorMsg = ValidatorUtil.getError(productCreateRequest);
        //如果校验错误 则返回
        if (StringUtil.isNotEmpty(errorMsg)) {
            setInvalidParameterResponse(apiResponse, errorMsg);
            return apiResponse;
        }

        try {
            CommonResponse commonResponse = productService.createProductInfo(productCreateRequest);

            if (ErrorCodeUtil.isFailedResponse(commonResponse.getResCode())) {
                setServiceErrorResponse(apiResponse, commonResponse);
            }
        } catch (Exception e) {
            logger.error("创建产品发生错误---" + e);
            e.printStackTrace();
            setInternalErrorResponse(apiResponse);
        }
        return apiResponse;
    }
}