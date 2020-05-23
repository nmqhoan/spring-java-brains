package io.javabrains.discoveryserver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
@EnableEurekaServer
public class EurekaDiscoveryServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(EurekaDiscoveryServerApplication.class, args);
	}

//	@RefreshScope
//	@RestController
//	class MessageRestController {
//
//		@Value("${my.greeting}")
//		private String message;
//
//		@RequestMapping("/message")
//		String getMessage() {
//			return this.message;
//		}
//	}
}
