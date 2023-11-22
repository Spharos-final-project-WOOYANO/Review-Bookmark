package spharos.review_bookmark.review.application;

import spharos.review_bookmark.review.dto.BookmarkReviewTotalNumberDto;

import java.util.List;

public interface ReviewService {

    List<BookmarkReviewTotalNumberDto >countBookmarkReview(List<Long> serviceIdList);
}
