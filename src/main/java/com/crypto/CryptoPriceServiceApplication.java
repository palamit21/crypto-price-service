package com.crypto;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableFeignClients( {"com.crypto.client", "com.crypto"})
@SpringBootApplication
public class CryptoPriceServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(CryptoPriceServiceApplication.class, args);
	}

}
