package com.example.ggkt.vod.service;

import com.atguigu.ggkt.model.vod.Video;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 课程视频 服务类
 * </p>
 *
 * @author atguigu
 * @since 2022-11-12
 */
public interface VideoService extends IService<Video> {

    void removeByCourseId(Long id);

    //删除小结的时候删除视频
    void removeVideoById(Long id);
}
