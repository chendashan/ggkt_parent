package com.example.ggkt.vod.service.impl;

import com.atguigu.ggkt.model.vod.Course;
import com.atguigu.ggkt.model.vod.Subject;
import com.atguigu.ggkt.model.vod.Teacher;
import com.atguigu.ggkt.vo.vod.CourseQueryVo;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.ggkt.vod.mapper.CourseMapper;
import com.example.ggkt.vod.service.CourseService;
import com.example.ggkt.vod.service.SubjectService;
import com.example.ggkt.vod.service.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

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
public class CourseServiceImpl extends ServiceImpl<CourseMapper, Course> implements CourseService {

    @Autowired
    private TeacherService teacherService;

    @Autowired
    private SubjectService subjectService;

    @Override
    public Map<String, Object> findPageCourse(Page<Course> pageParam, CourseQueryVo queryVo) {
        QueryWrapper<Course> queryWrapper = new QueryWrapper<>();
        Long subjectId = queryVo.getSubjectId();  //一层分类
        Long subjectParentId = queryVo.getSubjectParentId();  //二层分类
        Long teacherId = queryVo.getTeacherId();
        String title = queryVo.getTitle();

        if (!StringUtils.isEmpty(subjectId)) {
            queryWrapper.eq("subject_id", subjectId);
        }
        if (!StringUtils.isEmpty(subjectParentId)) {
            queryWrapper.eq("subject_parent_id", subjectParentId);
        }
        if (!StringUtils.isEmpty(teacherId)) {
            queryWrapper.eq("teacher_id", teacherId);
        }
        if (!StringUtils.isEmpty(title)) {
            queryWrapper.like("title", title);
        }

        Page<Course> coursePage = baseMapper.selectPage(pageParam, queryWrapper);
        long totalCount = coursePage.getTotal();
        long totalPage = coursePage.getPages();
        List<Course> records = coursePage.getRecords();

        records.stream().forEach(item -> {
            this.getNameById(item);
        });

        Map<String, Object> map = new HashMap<>();
        map.put("totalCount", totalCount);
        map.put("totalPage", totalPage);
        map.put("records", records);

        return map;
    }

    private Course getNameById(Course course) {
        Teacher teacher = teacherService.getById(course.getTeacherId());
        if (teacher != null) {
            String name = teacher.getName();
            course.getParam().put("teacherName", name);
        }

        Subject subjectOne = subjectService.getById(course.getSubjectParentId());
        if (subjectOne != null) {
            course.getParam().put("subjectParentTitle", subjectOne.getTitle());
        }
        Subject subjectTwo = subjectService.getById(course.getSubjectId());
        if (subjectTwo != null) {
            course.getParam().put("subjectTitle", subjectTwo.getTitle());
        }
        return course;
    }
}
