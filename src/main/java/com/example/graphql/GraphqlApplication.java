package com.example.graphql;

import com.example.graphql.utils.AESUtils;
import com.ulisesbocchio.jasyptspringboot.annotation.EnableEncryptableProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.AutoConfigurationPackage;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.sql.DriverManager;

@SpringBootApplication
@EnableEncryptableProperties
@AutoConfigurationPackage
public class GraphqlApplication {

	public static void main(String[] args) {
		SpringApplication.run(GraphqlApplication.class, args);
	}

}
