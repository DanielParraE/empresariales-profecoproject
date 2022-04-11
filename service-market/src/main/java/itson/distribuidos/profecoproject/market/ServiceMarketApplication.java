package itson.distribuidos.profecoproject.market;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class ServiceMarketApplication {

	public static void main(String[] args) {
		SpringApplication.run(ServiceMarketApplication.class, args);
	}

}
