package com.knockout.virtualthreads;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.task.TaskExecutionAutoConfiguration;
import org.springframework.boot.web.embedded.tomcat.TomcatProtocolHandlerCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.core.task.AsyncTaskExecutor;
import org.springframework.core.task.support.TaskExecutorAdapter;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.concurrent.Executors;
/**
 * 
 */
@SpringBootApplication
@RestController
public class VirtualThreadsApplication {

	public static void main(String[] args) {
		SpringApplication.run(VirtualThreadsApplication.class, args);
	}
	
	@GetMapping("/lowLatency")
    public String sayHello() {
		String url = "http://localhost:8080/lowLatency";
		return new RestTemplate().getForObject(url, String.class);
	}

	@GetMapping("/latency")
	public String sayHi(@RequestParam(value = "seconds", defaultValue = "30") Integer seconds) {
		String url = "http://localhost:8080/latency?seconds="+seconds;
		return new RestTemplate().getForObject(url, String.class);
	}

	@Bean
	TomcatProtocolHandlerCustomizer<?> protocolHandlerVirtualThreadExecutorCustomizer() {
		return protocolHandler -> {
			protocolHandler.setExecutor(Executors.newVirtualThreadPerTaskExecutor());
		};
	}

	@Bean(TaskExecutionAutoConfiguration.APPLICATION_TASK_EXECUTOR_BEAN_NAME)
	public AsyncTaskExecutor asyncTaskExecutor() {
		return new TaskExecutorAdapter(Executors.newVirtualThreadPerTaskExecutor());
	}

}
