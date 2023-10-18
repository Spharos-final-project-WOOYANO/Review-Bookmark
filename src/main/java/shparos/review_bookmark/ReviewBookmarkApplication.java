package shparos.review_bookmark;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class ReviewBookmarkApplication {

	public static void main(String[] args) {
		SpringApplication.run(ReviewBookmarkApplication.class, args);
	}

}
