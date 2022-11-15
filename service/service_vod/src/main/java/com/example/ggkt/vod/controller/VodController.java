package com.example.ggkt.vod.controller;

import com.example.ggkt.result.Result;
import com.example.ggkt.vod.service.VodService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@Api(tags = "腾讯云点播")
@RestController
@RequestMapping("/admin/vod")
public class VodController {

    @Autowired
    private VodService vodService;

    @PostMapping("upload")
    public Result upload() {
        String fileId = vodService.updateVideo();

        return Result.ok(fileId);
    }

    @DeleteMapping("remove/{fileId}")
    public Result remove(@PathVariable String fileId) {
        vodService.removeVideo(fileId);
        return Result.ok();
    }


}
