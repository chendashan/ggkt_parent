package com.example.ggkt.activity.controller;


import com.atguigu.ggkt.model.activity.CouponInfo;
import com.example.ggkt.activity.service.CouponInfoService;
import com.example.ggkt.result.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 优惠券信息 前端控制器
 * </p>
 *
 * @author cds
 * @since 2022-11-18
 */
@RestController
@RequestMapping("/admin/activity/couponInfo")
public class CouponInfoController {

    @Autowired
    private CouponInfoService couponInfoService;

    @GetMapping("get/{id}")
    public Result get(@PathVariable String id) {
        CouponInfo info = couponInfoService.getById(id);
        return Result.ok(info);
    }

}

