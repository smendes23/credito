package br.com.teste.credito;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@EnableJpaRepositories
@EnableAspectJAutoProxy
@SpringBootApplication
public class CreditoApplication {

    public static void main(String[] args) {
        SpringApplication.run(CreditoApplication.class, args);
    }

}
