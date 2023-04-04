package com.example.hdy.config;

import com.example.hdy.constant.Constants;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@Configuration
public class WebMvcConfig extends WebMvcConfigurerAdapter {
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedOrigins("*")
                .allowedMethods("*")
                .allowCredentials(true);
    }

    @Configuration
    public class MyPicConfig implements WebMvcConfigurer {
        @Override
        public void addResourceHandlers(ResourceHandlerRegistry registry) {
            String os = System.getProperty("os.name");
            if (os.toLowerCase().startsWith("win")) { // windos系统
                registry.addResourceHandler("/video/**")
                        .addResourceLocations("file:" + Constants.RESOURCE_WIN_PATH + "\\video\\");
                registry.addResourceHandler("/img/**")
                        .addResourceLocations("file:" + Constants.RESOURCE_WIN_PATH + "\\img\\");
            } else { // MAC、Linux系统
                registry.addResourceHandler("/video/**")
                        .addResourceLocations("file:" + Constants.RESOURCE_MAC_PATH + "/video/");
                registry.addResourceHandler("/img/**")
                        .addResourceLocations("file:" + Constants.RESOURCE_WIN_PATH + "/img/");
            }
        }
    }
}
