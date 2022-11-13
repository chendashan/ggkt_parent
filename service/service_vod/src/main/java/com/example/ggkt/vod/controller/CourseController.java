package com.example.ggkt.vod.controller;


import com.atguigu.ggkt.model.vod.Course;
import com.atguigu.ggkt.vo.vod.CourseFormVo;
import com.atguigu.ggkt.vo.vod.CoursePublishVo;
import com.atguigu.ggkt.vo.vod.CourseQueryVo;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.ggkt.result.Result;
import com.example.ggkt.vod.service.CourseService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * <p>
 * 课程 前端控制器
 * </p>
 *
 * @author atguigu
 * @since 2022-11-12
 */

@Api(tags = "具体课程")
@RestController
@RequestMapping(value = "/admin/vod/course")
//@CrossOrigin
public class CourseController {

    @Autowired
    private CourseService courseService;


    @ApiOperation("添加课程信息")
    @PostMapping("save")
    public Result save(@RequestBody CourseFormVo courseFormVo) {
        Long courseId = courseService.saveCourseInfo(courseFormVo);
        return Result.ok(courseId);
    }

    @ApiOperation("点播课程列表")
    @GetMapping("{page}/{limit}")
    public Result courseList(@PathVariable Long page, @PathVariable Long limit, CourseQueryVo queryVo) {
        Page<Course> pageParam = new Page<>(page, limit);
        Map<String, Object> map = courseService.findPageCourse(pageParam, queryVo);

        return Result.ok(map);
    }

    @GetMapping("get/{id}")
    public Result get(@PathVariable Long id) {
        CourseFormVo courseFormVo = courseService.getCourseInfoById(id);
        return Result.ok(courseFormVo);
    }

    @PostMapping("update")
    public Result update(@RequestBody CourseFormVo courseFormVo) {
        courseService.updateCourseInfo(courseFormVo);

        return Result.ok(courseFormVo.getId());
    }


    @ApiOperation("根据id获取课程发布信息")
    @GetMapping("getCoursePublishVo/{id}")
    public Result getCoursePublishVo(@PathVariable Long id) {
        CoursePublishVo coursePublishVo = courseService.getCoursePublishVo(id);

        return Result.ok(coursePublishVo);
    }

    @PutMapping("publishCourse/{id}")
    public Result publishCourse(@PathVariable Long id) {
        courseService.publishCourse(id);
        return Result.ok();
    }

    @DeleteMapping("remove/{id}")
    public Result removeCourse(@PathVariable Long id) {
        boolean isSuccess = courseService.removeCourse(id);
        return Result.ok(isSuccess);
    }

}

