package com.example.ggkt.order.controller;


import com.atguigu.ggkt.model.order.OrderInfo;
import com.atguigu.ggkt.vo.order.OrderInfoQueryVo;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.ggkt.order.service.OrderInfoService;
import com.example.ggkt.result.Result;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * <p>
 * 订单表 订单表 前端控制器
 * </p>
 *
 * @author cds
 * @since 2022-11-13
 */
@Api(tags = "订单管理")
@RestController
@RequestMapping(value = "/admin/order/orderInfo")
public class OrderInfoController {

    @Autowired
    private OrderInfoService orderInfoService;

    @GetMapping("{page}/{limit}")
    public Result orderList(@PathVariable Long page, @PathVariable Long limit, OrderInfoQueryVo orderInfoQueryVo) {
        Page<OrderInfo> pageParam = new Page<>(page, limit);
        Map<String, Object> map = orderInfoService.selectOrderInfoPage(pageParam, orderInfoQueryVo);

        return Result.ok(map);
    }

}

