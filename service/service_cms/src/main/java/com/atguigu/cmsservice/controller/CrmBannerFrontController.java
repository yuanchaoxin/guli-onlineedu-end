package com.atguigu.cmsservice.controller;


import com.atguigu.cmsservice.entity.CrmBanner;
import com.atguigu.cmsservice.service.CrmBannerService;
import com.atguigu.commonutils.R;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p>
 * 首页banner表 前端控制器
 * </p>
 *
 * @author atguigu
 * @since 2022-03-05
 */
@RestController
@CrossOrigin
public class CrmBannerFrontController {

    @Resource
    private CrmBannerService crmBannerService;

    /**
     * 查询前两条数据
     * @return
     */
    @GetMapping("/cmsservice/front-banner/getAllBanner")
    public R getAllBanner() {

        List<CrmBanner> list = crmBannerService.getAllBanner();

        return R.success().data("list", list);
    }
}

