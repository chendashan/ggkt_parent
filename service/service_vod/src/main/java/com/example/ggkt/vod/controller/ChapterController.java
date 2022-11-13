package com.example.ggkt.vod.controller;


import com.atguigu.ggkt.model.vod.Chapter;
import com.atguigu.ggkt.vo.vod.ChapterVo;
import com.example.ggkt.result.Result;
import com.example.ggkt.vod.service.ChapterService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 课程 前端控制器
 * </p>
 *
 * @author atguigu
 * @since 2022-11-12
 */
@RestController
@RequestMapping(value = "/admin/vod/chapter")
//@CrossOrigin
public class ChapterController {

    @Autowired
    ChapterService chapterService;

    @ApiOperation("嵌套章节数据列表")
    @GetMapping("getNestedTreeList/{courseId}")
    public Result getTreeList(@PathVariable Long courseId) {
        List<ChapterVo> list = chapterService.getTreeList(courseId);

        return Result.ok(list);
    }

    @ApiOperation("添加章节")
    @PostMapping("save")
    public Result save(@RequestBody Chapter chapter) {
        chapterService.save(chapter);
        return Result.ok();
    }

    @ApiOperation("根据id查询章节")
    @GetMapping("get/{id}")
    public Result get(@PathVariable Long id) {
        Chapter chapter = chapterService.getById(id);
        return Result.ok(chapter);
    }

    @ApiOperation("修改章节")
    @PostMapping("update")
    public Result update(@RequestBody Chapter chapter) {
        chapterService.updateById(chapter);
        return Result.ok();
    }

    @ApiOperation("删除章节")
    @DeleteMapping("remove/{id}")
    public Result remove(@PathVariable Long id) {
        boolean success = chapterService.removeById(id);
        return Result.ok(success);
    }

}

