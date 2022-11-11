package com.example.ggkt.vod.controller;


import com.atguigu.ggkt.model.vod.Subject;
import com.example.ggkt.result.Result;
import com.example.ggkt.vod.service.SubjectService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
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

    @ApiOperation("导出Excel")
    @GetMapping("exportData")
    public void exportData(HttpServletResponse response) {
        subjectService.exportExcel(response);
    }

    @ApiOperation("导入课程")
    @PostMapping("importData")
    public Result importData(MultipartFile file) {
        subjectService.importData(file);
        return Result.ok().message("导入课程成功");
    }

}

