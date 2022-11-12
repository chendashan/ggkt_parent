package com.example.ggkt.vod.utils;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class ConstantPropertiesUtil implements InitializingBean {

    //@Value 注入
    @Value("${tencent.cos.file.region}")
    private String region;

    @Value("${tencent.cos.file.secretid}")
    private String secretId;

    @Value("${tencent.cos.file.secretkey}")
    private String secretKey;

    @Value("${tencent.cos.file.bucketname}")
    private String bucketName;

    public static String END_POINT;
    public static String ACCESS_SECRET_ID;
    public static String ACCESS_SECRET_KEY;
    public static String BUCKET_NAME;

    @Override
    public void afterPropertiesSet() throws Exception {
        END_POINT = region;
        ACCESS_SECRET_ID = handleString(secretId);
        ACCESS_SECRET_KEY = handleString(secretKey);
        BUCKET_NAME = bucketName;
    }

    private String handleString(String str) {
        String replace = str.replace("6", "");
        return replace;
    }
}
