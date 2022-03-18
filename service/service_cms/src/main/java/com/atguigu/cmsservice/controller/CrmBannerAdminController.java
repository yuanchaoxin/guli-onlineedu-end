package com.atguigu.cmsservice.controller;


import com.atguigu.cmsservice.entity.CrmBanner;
import com.atguigu.cmsservice.service.CrmBannerService;
import com.atguigu.commonutils.R;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

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
public class CrmBannerAdminController {

    @Resource
    private CrmBannerService crmBannerService;

    @PostMapping("/cmsservice/admin-banner/add")
    public R addBanner(@RequestParam CrmBanner crmBanner) {

        crmBannerService.save(crmBanner);
        return R.success();
    }

    @DeleteMapping("/cmsservice/admin-banner/delete/{bannerId}")
    public R deleteBanner(@PathVariable("bannerId") String bannerId) {

        crmBannerService.removeById(bannerId);
        return R.success();
    }

    @PostMapping("/cmsservice/admin-banner/updateBanner")
    public R updateBanner(@RequestParam CrmBanner crmBanner) {

        crmBannerService.updateById(crmBanner);
        return R.success();
    }

    @GetMapping("/cmsservice/admin-banner/bannerInfo/{bannerId}")
    public R getBannerInfo(@PathVariable("bannerId") String bannerId) {

        CrmBanner banner = crmBannerService.getById(bannerId);
        return R.success().data("banner", banner);
    }

    @GetMapping("/cmsservice/admin-banner/pageBanner/{current}/{size}")
    public R pageBanner(@PathVariable("current") long current, @PathVariable("size") long size) {

        Page<CrmBanner> page = new Page<>(current, size);

        IPage<CrmBanner> crmBannerIPage = crmBannerService.page(page, null);

        return R.success().data("total", crmBannerIPage.getTotal())
                .data("list", crmBannerIPage.getRecords());
    }
}

