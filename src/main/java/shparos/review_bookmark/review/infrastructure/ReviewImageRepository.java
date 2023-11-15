package shparos.review_bookmark.review.infrastructure;

import org.springframework.data.jpa.repository.JpaRepository;
import shparos.review_bookmark.review.domain.ReviewImage;

public interface ReviewImageRepository extends JpaRepository<ReviewImage, Long> {
}
