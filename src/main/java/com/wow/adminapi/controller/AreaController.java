package com.wow.adminapi.controller;

import com.wow.user.model.Area;
import com.wow.user.service.AreaService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created by zhengzhiqing on 16/7/10.
 */
@RestController
public class AreaController {

    private static final Logger logger = LoggerFactory.getLogger(AreaController.class);

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

}
