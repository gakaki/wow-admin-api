package com.wow.adminapi.controller;

import com.wow.page.model.PageBannerConfig;
import com.wow.page.model.PageSceneConfig;
import com.wow.page.model.PageTopicConfig;
import com.wow.product.model.Product;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by zhengzhiqing on 16/6/23.
 */
@RestController
@RequestMapping
public class PageController {
    private static final Logger logger = LoggerFactory.getLogger(PageController.class);

    @RequestMapping(value = "/v1.0/pages/banners?pageType={pageType}", method = RequestMethod.GET)
    public List<PageBannerConfig> getBannersOnPage(@RequestParam Integer pageType) {
        return null;
    }

    @RequestMapping(value = "/v1.0/pages/scenes?pageType={pageType}", method = RequestMethod.GET)
    public List<PageSceneConfig> getScenesOnPage(@RequestParam Integer pageType) {
        return null;
    }

    @RequestMapping(value = "/v1.0/pages/topics?pageType={pageType}", method = RequestMethod.GET)
    public List<PageTopicConfig> getTopicsOnPage(@RequestParam Integer pageType) {
        return null;
    }
}
