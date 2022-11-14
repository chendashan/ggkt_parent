package com.example.ggkt.vod.controller;


import com.example.ggkt.result.Result;
import com.example.ggkt.vod.service.VideoVisitorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * <p>
 * 视频来访者记录表 前端控制器
 * </p>
 *
 * @author cds
 * @since 2022-11-14
 */
@RestController
@RequestMapping("/admin/vod/videoVisitor")
public class VideoVisitorController {

    @Autowired
    private VideoVisitorService videoVisitorService;

    @GetMapping("findCount/{courseId}/{startDate}/{endDate}")
    public Result findCount(@PathVariable Long courseId,
                            @PathVariable String startDate,
                            @PathVariable String endDate) {

        Map<String, Object> map = videoVisitorService.findCount(courseId, startDate, endDate);

        return Result.ok(map);

    }

}

