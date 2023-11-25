package spharos.review_bookmark.review.infrastructure;

import org.springframework.data.jpa.repository.JpaRepository;
import spharos.review_bookmark.review.domain.Review;
import java.util.List;

public interface ReviewRepository extends JpaRepository<Review, Long> {

    List<Review> findByUserEmailOrderByIdDesc(String email);

    List<Review> findByServiceId(Long serviceId);

    Review findByReservationNum(String reservationNum);


}