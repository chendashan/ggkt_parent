package com.example.ggkt.activity.service.impl;

import com.atguigu.ggkt.model.activity.CouponInfo;
import com.atguigu.ggkt.model.activity.CouponUse;
import com.atguigu.ggkt.model.user.UserInfo;
import com.atguigu.ggkt.vo.activity.CouponUseQueryVo;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.ggkt.activity.mapper.CouponInfoMapper;
import com.example.ggkt.activity.service.CouponInfoService;
import com.example.ggkt.activity.service.CouponUseService;
import com.example.ggkt.client.user.UserInfoFeignClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;

/**
 * <p>
 * 优惠券信息 服务实现类
 * </p>
 *
 * @author cds
 * @since 2022-11-18
 */
@Service
public class CouponInfoServiceImpl extends ServiceImpl<CouponInfoMapper, CouponInfo> implements CouponInfoService {

    @Autowired
    private CouponUseService couponUseService;

    @Autowired
    private UserInfoFeignClient userInfoFeignClient;

    @Override
    public IPage<CouponUse> selectCouponUse(Page<CouponUse> pageParam, CouponUseQueryVo couponUseQueryVo) {
        //获取条件
        Long couponId = couponUseQueryVo.getCouponId();
        String couponStatus = couponUseQueryVo.getCouponStatus();
        String getTimeBegin = couponUseQueryVo.getGetTimeBegin();
        String getTimeEnd = couponUseQueryVo.getGetTimeEnd();

        QueryWrapper<CouponUse> queryWrapper = new QueryWrapper<>();
        if (!StringUtils.isEmpty(couponId)) {
            queryWrapper.eq("coupon_id", couponId);
        }
        if (!StringUtils.isEmpty(couponStatus)) {
            queryWrapper.eq("coupon_status", couponStatus);
        }
        if (!StringUtils.isEmpty(getTimeBegin)) {
            queryWrapper.ge("get_time", getTimeBegin);
        }
        if (!StringUtils.isEmpty(getTimeEnd)) {
            queryWrapper.le("get_time", getTimeEnd);
        }

        IPage<CouponUse> pageModel = couponUseService.page(pageParam, queryWrapper);

        List<CouponUse> records = pageModel.getRecords();
        records.stream().forEach(item -> {
            this.getUserInfoById(item);
        });

        return pageModel;
    }

    private CouponUse getUserInfoById(CouponUse couponUse) {
        Long userId = couponUse.getUserId();
        if (!StringUtils.isEmpty(userId)) {
            UserInfo userinfo = userInfoFeignClient.getById(userId);
            if (userinfo != null) {
                couponUse.getParam().put("nickName", userinfo.getNickName());
                couponUse.getParam().put("phone", userinfo.getPhone());
            }
        }
        return couponUse;
    }
}
