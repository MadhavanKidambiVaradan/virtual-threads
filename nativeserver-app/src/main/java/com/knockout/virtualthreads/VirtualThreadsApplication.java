package com.knockout.virtualthreads;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.task.TaskExecutionAutoConfiguration;
import org.springframework.boot.web.embedded.tomcat.TomcatProtocolHandlerCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.core.task.AsyncTaskExecutor;
import org.springframework.core.task.support.TaskExecutorAdapter;
import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.concurrent.Executors;

@SpringBootApplication
@RestController
public class VirtualThreadsApplication {

	public static void main(String[] args) {
		SpringApplication.run(VirtualThreadsApplication.class, args);
	}
	
	@GetMapping("/lowLatency")
    public String sayHello() {
		return "hello";
	}

	@GetMapping("/latency")
	public String sayHi(@RequestParam(value = "seconds", defaultValue = "30") Integer seconds) {
		try {
			Thread.sleep(seconds*1000);
		} catch (InterruptedException e) {
			throw new RuntimeException(e);
		}
		return "hello";
	}

	/*@Bean
	TomcatProtocolHandlerCustomizer<?> protocolHandlerVirtualThreadExecutorCustomizer() {
		return protocolHandler -> {
			protocolHandler.setExecutor(Executors.newVirtualThreadPerTaskExecutor());
		};
	}*/


}
