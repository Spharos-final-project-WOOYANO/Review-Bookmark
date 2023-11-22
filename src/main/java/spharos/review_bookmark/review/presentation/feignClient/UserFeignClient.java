package spharos.review_bookmark.review.presentation.feignClient;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import spharos.review_bookmark.review.vo.response.ReviewWriterResponse;

import java.util.List;

@FeignClient(name = "user-service",url = "http://localhost:8000/api/v1/users")
public interface UserFeignClient{

    @PostMapping("/review/writer")
    List<ReviewWriterResponse> getReviewWriter(@RequestBody List<String> userEmail);
}