package com.company.core.config;

import com.company.core.util.ApplicationPropertyEnum;
import org.springframework.boot.web.servlet.MultipartConfigFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.unit.DataSize;

import javax.servlet.MultipartConfigElement;

/**
 * @author mukulbansal
 */
@Configuration
public class BeanConfiguration {

    @Bean
    MultipartConfigElement multipartConfigElement() {
        MultipartConfigFactory factory = new MultipartConfigFactory();
        factory.setMaxFileSize(DataSize.ofMegabytes(Integer.valueOf(ApplicationPropertyEnum.MULTIPART_MAX_REQUEST_MB.getValue())));
        factory.setMaxRequestSize(DataSize.ofMegabytes(Integer.valueOf(ApplicationPropertyEnum.MULTIPART_MAX_REQUEST_MB.getValue())));
        return factory.createMultipartConfig();
    }
}
