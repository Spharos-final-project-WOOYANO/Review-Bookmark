package spharos.review_bookmark.review.application;

import spharos.review_bookmark.review.dto.BookmarkReviewTotalNumberDto;
import spharos.review_bookmark.review.dto.ReviewListDto;
import java.util.List;

public interface ReviewService {

    List<BookmarkReviewTotalNumberDto >countBookmarkReview(List<Long> serviceIdList);
    List<ReviewListDto> retrieveReviewList(Long serviceId);
}
