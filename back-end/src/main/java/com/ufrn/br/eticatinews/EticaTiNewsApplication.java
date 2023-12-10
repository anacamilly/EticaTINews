package com.ufrn.br.eticatinews;

import com.ufrn.br.eticatinews.config.RsaKeyProperties;
import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
@EnableConfigurationProperties(RsaKeyProperties.class)
public class EticaTiNewsApplication {

    public static void main(String[] args) {
        SpringApplication.run(EticaTiNewsApplication.class, args);
    }

    @Bean
    public ModelMapper mapper(){
        return new ModelMapper();
    }
}
