package shparos.review_bookmark;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/review-bookmark")
public class TestController {
    @GetMapping("/test")
    public String testMethod(){
        return "Review Bookmark Service";
    }
}
