package com.example.ggkt.vod.service.impl;

import com.atguigu.ggkt.model.vod.Video;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.ggkt.vod.mapper.VideoMapper;
import com.example.ggkt.vod.service.VideoService;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 课程视频 服务实现类
 * </p>
 *
 * @author atguigu
 * @since 2022-11-12
 */
@Service
public class VideoServiceImpl extends ServiceImpl<VideoMapper, Video> implements VideoService {

}