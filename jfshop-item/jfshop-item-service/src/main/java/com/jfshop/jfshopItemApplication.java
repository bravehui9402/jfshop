package com.jfshop;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import tk.mybatis.spring.annotation.MapperScan;

@SpringBootApplication
@EnableDiscoveryClient
@MapperScan("com.jfshop.item.mapper")
public class jfshopItemApplication {
    public static void main(String[] args) {
        SpringApplication.run(jfshopItemApplication.class);
    }
}
