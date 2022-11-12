package com.example.ggkt.vod.service.impl;

import com.atguigu.ggkt.model.vod.Chapter;
import com.atguigu.ggkt.model.vod.Video;
import com.atguigu.ggkt.vo.vod.ChapterVo;
import com.atguigu.ggkt.vo.vod.VideoVo;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.ggkt.vod.mapper.ChapterMapper;
import com.example.ggkt.vod.service.ChapterService;
import com.example.ggkt.vod.service.VideoService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 课程 服务实现类
 * </p>
 *
 * @author atguigu
 * @since 2022-11-12
 */
@Service
public class ChapterServiceImpl extends ServiceImpl<ChapterMapper, Chapter> implements ChapterService {

    @Autowired
    VideoService videoService;

    @Override
    public List<ChapterVo> getTreeList(Long courseId) {
        //根据courseId获取全部章节
        QueryWrapper<Chapter> wrapperChapter = new QueryWrapper<>();
        wrapperChapter.eq("course_id", courseId);
        List<Chapter> chapterList = baseMapper.selectList(wrapperChapter);

        //根据courseId获取全部小节
        LambdaQueryWrapper<Video> wpVideo = new LambdaQueryWrapper<>();
        wpVideo.eq(Video::getCourseId, courseId);
        List<Video> videoList = videoService.list(wpVideo);

        Map<Long, ChapterVo> map = new HashMap<>();
        //封装章节
        for (Chapter chapter : chapterList) {
            ChapterVo chapterVo = new ChapterVo();
            BeanUtils.copyProperties(chapter, chapterVo);
            chapterVo.setChildren(new ArrayList<>());
            map.put(chapterVo.getId(), chapterVo);
        }

        //封装小节
        for (Video video : videoList) {
            VideoVo videoVo = new VideoVo();
            BeanUtils.copyProperties(video, videoVo);
            ChapterVo mapChapterVo = map.get(video.getChapterId());
            if (mapChapterVo != null) {
                mapChapterVo.getChildren().add(videoVo);
            }
            //map.get(video.getChapterId()).getChildren().add(videoVo);

        }

        List<ChapterVo> resultChapterList = new ArrayList<>(map.values());

        return resultChapterList;
    }
}
