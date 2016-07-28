package com.wow.adminapi.controller;

import java.util.List;

import com.wow.adminapi.request.area.AreaRequest;
import com.wow.common.request.ApiRequest;
import com.wow.common.request.DictionaryRequest;
import com.wow.common.response.ApiResponse;
import com.wow.common.util.JsonUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.wow.user.model.Area;
import com.wow.user.service.AreaService;

/**
 * Created by zhengzhiqing on 16/7/10.
 */
@RestController
public class AreaController extends  BaseController{

    //private static final Logger logger = LoggerFactory.getLogger(AreaController.class);

    @Autowired
    private AreaService areaService;

    /**
     * 查找次级区域
     * @param areaId
     * @return
     */
    @RequestMapping(value = "v1/areas/{areaId}/subareas", method = RequestMethod.GET)
    public List<Area> findNextLevelArea(@PathVariable int areaId) {
        return areaService.getNextLevelArea(areaId);
    }

    /**
     * 查找一级区域
     * @return
     */
    @RequestMapping(value = "v1/areas/firstlevel", method = RequestMethod.GET)
    public List<Area> findFirstLevelArea() {
        return areaService.getFirstLevelArea();
    }

    /**
     * 查找次级区域
     * @param apiRequest
     * @return
     */
    @RequestMapping(value = "/v1/areas/subareas", method = RequestMethod.GET)
    public ApiResponse findNextLevelArea(ApiRequest apiRequest) {
        ApiResponse apiResponse = new ApiResponse();
        AreaRequest areaRequest = JsonUtil.fromJSON(apiRequest.getParamJson(), AreaRequest.class);
        if (areaRequest == null) {
            setParamJsonParseErrorResponse(apiResponse);
            return apiResponse;
        }
        apiResponse.setData(areaService.getNextLevelArea(areaRequest.getId()));
        return apiResponse;
    }

    /**
     * 查找一级区域
     * @return
     */
    @RequestMapping(value = "/v1/areas/queryFirstLevel", method = RequestMethod.GET)
    public ApiResponse queryFirstLevelArea() {
        ApiResponse apiResponse = new ApiResponse();
        apiResponse.setData(areaService.getFirstLevelArea());
        return apiResponse;
    }
}
