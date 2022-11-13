package com.example.ggkt.order.service.impl;

import com.atguigu.ggkt.model.order.OrderDetail;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.ggkt.order.mapper.OrderDetailMapper;
import com.example.ggkt.order.service.OrderDetailService;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 订单明细 订单明细 服务实现类
 * </p>
 *
 * @author cds
 * @since 2022-11-13
 */
@Service
public class OrderDetailServiceImpl extends ServiceImpl<OrderDetailMapper, OrderDetail> implements OrderDetailService {

}
