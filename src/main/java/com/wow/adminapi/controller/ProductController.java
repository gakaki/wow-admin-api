package com.wow.adminapi.controller;

import com.wow.common.request.ApiRequest;
import com.wow.common.response.ApiResponse;
import com.wow.common.response.CommonResponse;
import com.wow.common.util.ErrorCodeUtil;
import com.wow.common.util.JsonUtil;
import com.wow.common.util.StringUtil;
import com.wow.common.util.ValidatorUtil;
import com.wow.product.service.ProductService;
import com.wow.product.vo.request.ProductCreateRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(maxAge = 3600)
public class ProductController extends BaseController {

    private static final Logger logger = LoggerFactory.getLogger(ProductController.class);

    @Autowired
    private ProductService productService;

    /**
     * 创建产品
     * @param apiRequest
     * @return
     */
    @RequestMapping(value = "/v1/product/create", method = RequestMethod.POST)
    public ApiResponse createProductInfo(ApiRequest apiRequest) {
        ApiResponse apiResponse = new ApiResponse();
        ProductCreateRequest productCreateRequest = JsonUtil.fromJSON(apiRequest.getParamJson(), ProductCreateRequest.class);

//        ProductCreateRequest productCreateRequest = JsonUtil.fromJSON("{\"colorSpecVoList\":[{\"colorId\":1,\"productColorImg\":\"product/spec/e1551d10bef1953166aa0e3192ad1598/o_1aoqrsi6aaia1qm1fdo57q1p0616.png\",\"colorDisplayName\":\"白色\",\"specVoList\":[{\"specName\":\"2\",\"sellPrice\":\"9\",\"costPrice\":\"4\",\"weight\":\"5\"},{\"specName\":\"3\",\"sellPrice\":\"8\",\"costPrice\":\"3\",\"weight\":\"6\"}]},{\"colorId\":2,\"productColorImg\":\"product/spec/e1551d10bef1953166aa0e3192ad1598/o_1aoqrslhvvv01j4jjimubllcf1b.png\",\"colorDisplayName\":\"银色\",\"specVoList\":[{\"specName\":\"2\",\"sellPrice\":\"7\",\"costPrice\":\"2\",\"weight\":\"73\"},{\"specName\":\"3\",\"sellPrice\":\"6\",\"costPrice\":\"1\",\"weight\":\"43\"}]}],\"categoryId\":10001,\"productName\":\"测试录入商品\",\"sellingPoint\":\"12312\",\"productModel\":\"434\",\"brandId\":25,\"designerVoList\":[{\"id\":140,\"designerName\":\"Aaron Probyn\",\"designerSex\":1,\"designerCountry\":\"\",\"designerHomeUrl\":\"http://www.wowdsgn.com/designers/aaron-probyn.html\",\"designerPhoto\":\"http://www.wowdsgn.com/media/catalog/category/Aaron_Probyn_.jpg\",\"designerVideo\":\"\",\"designerDesc\":\"Aaron Probyn 在金斯敦大学的产品与家具设计进行了研究。毕业后，他在Habitat担任了六年多Table Top部门的设计经理。现今，Aaron Probyn有了他自己的设计工作室。他为许多不同的公司，如Tom Dixon, Conran Studio and Normann Copenhagen.设计作品。“我相信关于设计有一些非常原始的东西。作为人类，我们有必要让物体从生理上以及情感上丰富我们的生活。我的设计理念是非常保守的。对于设计我有一套很实际的方法。我喜欢把玩材料、寻找新的工艺。我认为要达到好的设计和质量应该更容易，可悲的是，过去人们为了生活而添置物品的时光已经一去不复返了。” \",\"designerStyle\":\"\",\"isActive\":true,\"isDeleted\":false,\"primary\":true}],\"originCountryId\":\"1\",\"originCity\":\"213\",\"styleId\":1,\"length\":\"12\",\"width\":\"12\",\"height\":\"21\",\"applicablePeople\":1,\"canCustomized\":true,\"applicableSceneList\":[\"1\",\"2\"],\"materialList\":[\"1\",\"12\"],\"detailDescription\":\"13123\",\"productImgVoList\":[{\"imgDesc\":\"正面图\",\"imgUrl\":\"product/693e9af84d3dfcc71e640e005bdc5e2e/o_1aoqrtjpb14rk1ht7rl8h6h4p51g.png\",\"sortOrder\":1,\"primary\":true},{\"imgDesc\":\"侧面图\",\"imgUrl\":\"product/693e9af84d3dfcc71e640e005bdc5e2e/o_1aoqrv04l7in1iov1ais9q1uir1q.png\",\"sortOrder\":2,\"primary\":true},{\"imgDesc\":\"细节图\",\"imgUrl\":\"product/693e9af84d3dfcc71e640e005bdc5e2e/o_1aoqrv5ib15396f1a8f1vqk1arb1v.png\",\"sortOrder\":3,\"primary\":true},{\"imgDesc\":\"细节图\",\"imgUrl\":\"product/693e9af84d3dfcc71e640e005bdc5e2e/o_1aoqrvc391sq213gf17kk1ei1j4924.png\",\"sortOrder\":4,\"primary\":true},{\"imgDesc\":\"细节图\",\"imgUrl\":\"product/693e9af84d3dfcc71e640e005bdc5e2e/o_1aoqrvgin146b1v91a0tlmh1ua329.png\",\"sortOrder\":5,\"primary\":true},{\"imgUrl\":\"product/693e9af84d3dfcc71e640e005bdc5e2e/o_1aoqrtp0f80c9p711e314re15741l.png\",\"imgDesc\":\"3213\",\"primary\":false,\"sortOrder\":1}]}", ProductCreateRequest.class);
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