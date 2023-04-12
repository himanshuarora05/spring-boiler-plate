package com.company.web.startup;

import com.company.core.util.ApplicationPropertyEnum;
import com.company.core.util.Constants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.Optional;
import java.util.Properties;

/**
 * @author mukulbansal
 */
@ComponentScan(basePackages = {"com.company"})
@SpringBootApplication
@EnableWebMvc
@EnableTransactionManagement
@EnableScheduling
@EnableJpaRepositories(basePackages = {"com.company.dao.repository"})
@EntityScan(basePackages = {"com.company.dao.entity"})
public class CompanyBackendApplication extends SpringBootServletInitializer {

    private static final Logger LOG = LoggerFactory.getLogger(com.company.web.startup.CompanyBackendApplication.class);

    public static void main(String[] args) throws IOException {
        SpringApplication.run(com.company.web.startup.CompanyBackendApplication.class, args);
        initialize("error-codes.properties", InitializationSource.RESOURCE);
        initialize(args, ApplicationPropertyEnum.SPRING_CONFIG_LOCATION);
    }

    private static void initialize(String[] args, ApplicationPropertyEnum applicationPropertyEnum) throws IOException {
        Optional<String> argOptional = Arrays
                .stream(args)
                .filter(x -> x.contains(applicationPropertyEnum.getKey()))
                .findFirst();
        if (!argOptional.isPresent() || argOptional.get().split(Constants.Symbols.EQUALS).length != 2) {
            LOG.error("Mandatory program argument missing- {}. Exiting...", applicationPropertyEnum.getKey());
            System.exit(0);
        }
        initialize(argOptional.get().split(Constants.Symbols.EQUALS)[1].trim(), InitializationSource.FILE_SYSTEM);
    }

    private static void initialize(String resource, InitializationSource initializationSource) throws IOException {
        LOG.info("Starting to load resource into system- {}", resource);
        InputStream inputStream = null;
        switch (initializationSource) {
            case FILE_SYSTEM:
                inputStream = new FileInputStream(new File(resource));
                break;
            case RESOURCE:
                inputStream = com.company.web.startup.CompanyBackendApplication.class.getClassLoader().getResourceAsStream(resource);
                break;
        }
        Properties properties = new Properties();
        properties.load(inputStream);
        for (String property : properties.stringPropertyNames()) {
            String key = property.trim();
            String value = properties.get(property).toString().trim();
            System.setProperty(key, value);
        }
        LOG.info("Ending loading of resource into system- {}", resource);
    }

    enum InitializationSource {
        RESOURCE,
        FILE_SYSTEM,
        ;
    }

}
