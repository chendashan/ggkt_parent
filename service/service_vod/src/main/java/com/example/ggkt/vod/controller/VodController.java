package com.example.ggkt.vod.controller;

import com.example.ggkt.exception.ExampleException;
import com.example.ggkt.result.Result;
import com.example.ggkt.vod.service.VodService;
import com.example.ggkt.vod.utils.ConstantPropertiesUtil;
import com.example.ggkt.vod.utils.Signature;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Random;

@Api(tags = "腾讯云点播")
@RestController
@RequestMapping("/admin/vod")
public class VodController {

    @Autowired
    private VodService vodService;


    @GetMapping("sign")
    public Result sign() {
        Signature sign = new Signature();
        // 设置 App 的云 API 密钥
        sign.setSecretId(ConstantPropertiesUtil.ACCESS_SECRET_ID);
        sign.setSecretKey(ConstantPropertiesUtil.ACCESS_SECRET_KEY);
        sign.setCurrentTime(System.currentTimeMillis() / 1000);
        sign.setRandom(new Random().nextInt(java.lang.Integer.MAX_VALUE));
        sign.setSignValidDuration(3600 * 24 * 2); // 签名有效期：2天
        try {
            String signature = sign.getUploadSignature();
            return Result.ok(signature);
        } catch (Exception e) {
            System.out.print("获取签名失败");
            e.printStackTrace();
            throw new ExampleException(20001, "获取签名失败");
        }
    }

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
