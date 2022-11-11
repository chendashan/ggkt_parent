package com.example.ggkt.vod.service.impl;


import com.atguigu.ggkt.model.vod.Subject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.ggkt.vod.mapper.SubjectMapper;
import com.example.ggkt.vod.service.SubjectService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 课程科目 服务实现类
 * </p>
 *
 * @author atguigu
 * @since 2022-11-11
 */
@Service
public class SubjectServiceImpl extends ServiceImpl<SubjectMapper, Subject> implements SubjectService {

    @Override
    public List<Subject> selectSubjectList(Long id) {

        QueryWrapper<Subject> wrapper = new QueryWrapper<>();
        wrapper.eq("parent_id", id);
        List<Subject> subjectList = baseMapper.selectList(wrapper);

        for (Subject subject : subjectList) {
            if (isChildren(subject.getId())) {
                subject.setHasChildren(true);
            }
        }

        return subjectList;
    }

    private boolean isChildren(Long id) {
        QueryWrapper<Subject> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("parent_id", id);
        Integer count = baseMapper.selectCount(queryWrapper);
        return count > 0;
    }

}
