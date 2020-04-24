package com.bestsch.resetPsd;

import com.bestsch.bschapistd.config.EnableBschApiStd;
import com.bestsch.bschsso.EnableBschSso;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

/**
 * 启动类
 */
@SpringBootApplication
@EnableAsync
@EnableBschSso //用户中心接口
@EnableBschApiStd
public class ResetPsdApplication {

    public static void main(String[] args) {
        SpringApplication.run(ResetPsdApplication.class, args);
    }

}
