package com.example.ggkt.vod.controller;


import com.atguigu.ggkt.model.vod.Subject;
import com.example.ggkt.result.Result;
import com.example.ggkt.vod.service.SubjectService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 课程科目 前端控制器
 * </p>
 *
 * @author atguigu
 * @since 2022-11-11
 */
@Api(tags = "课程表控制")
@RestController
@RequestMapping("/admin/vod/subject")
@CrossOrigin
public class SubjectController {

    @Autowired
    private SubjectService subjectService;

    @ApiOperation("课程表分类列表")
    @GetMapping("getChildSubject/{id}")
    public Result selectSubject(@PathVariable Long id) {
        List<Subject> subjectList = subjectService.selectSubjectList(id);
        return Result.ok(subjectList);
    }

}

