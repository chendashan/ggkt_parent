package com.example.ggkt.vod.controller;


import com.atguigu.ggkt.model.vod.Teacher;
import com.example.ggkt.vod.service.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

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
public class TeacherController {

    @Autowired
    private TeacherService teacherService;


    //http://localhost:8301/admin/vod/teacher/findAll
    @GetMapping("findAll")
    public List<Teacher> findAllTeacher() {
        List<Teacher> list = teacherService.list();
        return list;
    }
}

