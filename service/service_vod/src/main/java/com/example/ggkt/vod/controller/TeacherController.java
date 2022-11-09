package com.example.ggkt.vod.controller;


import com.atguigu.ggkt.model.vod.Teacher;
import com.atguigu.ggkt.vo.vod.TeacherQueryVo;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.ggkt.exception.ExampleException;
import com.example.ggkt.result.Result;
import com.example.ggkt.vod.service.TeacherService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 讲师 前端控制器
 * </p>
 *
 * @author atguigu
 * @since 2022-11-04
 */
@RestController
@RequestMapping(value = "/admin/vod/teacher")
@CrossOrigin
public class TeacherController {

    @Autowired
    private TeacherService teacherService;


    //http://localhost:8301/admin/vod/teacher/findAll
    @GetMapping("findAll")
    public Result findAllTeacher() {

        //int i = 10 / 0;

//        try {
//            int i = 10 / 0;
//        } catch (Exception e) {
//            throw new ExampleException(201, "执行自定义异常 ExampleException");
//        }

        List<Teacher> list = teacherService.list();
        return Result.ok(list).message("查询数据成功");
    }

    @DeleteMapping("remove/{id}")
    public Result removeTeacher(@PathVariable Long id) {
        boolean isSuccess = teacherService.removeById(id);
        if (isSuccess) {
            return Result.ok();
        } else {
            return Result.fail();
        }
    }

    @ApiOperation("讲师条件查询分页")
    @PostMapping("queryTeacherPage/{current}/{limit}")
    public Result findPage(@PathVariable long current, @PathVariable long limit, @RequestBody(required = false) TeacherQueryVo teacherQueryVo) {
        Page<Teacher> pageParam = new Page<>(current, limit);
        if (teacherQueryVo == null) {
            IPage<Teacher> pageModel = teacherService.page(pageParam, null);
            return Result.ok(pageModel);
        } else {
            String name = teacherQueryVo.getName();
            Integer level = teacherQueryVo.getLevel();
            String joinDateBegin = teacherQueryVo.getJoinDateBegin();
            String joinDateEnd = teacherQueryVo.getJoinDateEnd();
            QueryWrapper<Teacher> queryWrapper = new QueryWrapper<>();
            if (!StringUtils.isEmpty(name)) {
                queryWrapper.like("name", name);
            }
            if (!StringUtils.isEmpty(level)) {
                queryWrapper.eq("level", name);
            }
            if (!StringUtils.isEmpty(joinDateBegin)) {
                queryWrapper.ge("join_date", joinDateBegin);
            }
            if (!StringUtils.isEmpty(joinDateEnd)) {
                queryWrapper.le("join_date", joinDateEnd);
            }

            IPage<Teacher> pageModel = teacherService.page(pageParam, queryWrapper);
            return Result.ok(pageModel);
        }

    }

    @ApiOperation("添加讲师")
    @PostMapping("addTeacher")
    public Result addTeacher(@RequestBody Teacher teacher) {
        boolean isSuccess = teacherService.save(teacher);
        if (isSuccess) {
            return Result.ok();
        } else {
            return Result.fail();
        }
    }

    @ApiOperation("根据id查询")
    @GetMapping("getById/{id}")
    public Result getById(@PathVariable Long id) {
        Teacher teacher = teacherService.getById(id);
        return Result.ok(teacher);
    }

    @ApiOperation("修改讲师信息")
    @PostMapping("updateTeacher")
    public Result updateTeacher(@RequestBody Teacher teacher) {
        boolean isSuccess = teacherService.updateById(teacher);
        if (isSuccess) {
            return Result.ok();
        } else {
            return Result.fail();
        }
    }

    @ApiOperation("批量删除讲师")
    @DeleteMapping("deleteBatch")
    public Result deleteBatch(@RequestBody List<Long> ids) {
        boolean isSuccess = teacherService.removeByIds(ids);
        if (isSuccess) {
            return Result.ok();
        } else {
            return Result.fail();
        }
    }

}

