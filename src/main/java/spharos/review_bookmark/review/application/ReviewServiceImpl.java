package spharos.review_bookmark.review.application;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import spharos.review_bookmark.bookmark.domain.Bookmark;
import spharos.review_bookmark.bookmark.infrastructure.BookmarkRepository;
import spharos.review_bookmark.review.domain.Review;
import spharos.review_bookmark.review.dto.BookmarkReviewTotalNumberDto;
import spharos.review_bookmark.review.infrastructure.ReviewRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
@Slf4j
public class ReviewServiceImpl implements ReviewService{

    private final ReviewRepository reviewRepository;
    private final BookmarkRepository bookmarkRepository;

    @Override
    @Transactional
    public List<BookmarkReviewTotalNumberDto> countBookmarkReview(List<Long> serviceIdList) {

        int reviewCounter = 0;
        int bookmarkCounter = 0;

        List<BookmarkReviewTotalNumberDto> bookmarkReviewTotalNumberDtoList = new ArrayList<>();

        for (Long serviceId: serviceIdList) {

            List<Review> reviews = reviewRepository.findByServiceId(serviceId);
            reviewCounter = reviews.size();

            List<Bookmark> bookmarks = bookmarkRepository.findByServiceId(serviceId);
            bookmarkCounter = bookmarks.size();

            BookmarkReviewTotalNumberDto bookmarkReviewTotalNumberDto = BookmarkReviewTotalNumberDto.builder()
                    .serviceId(serviceId)
                    .totalBookmark(reviewCounter)
                    .totalReview(bookmarkCounter)
                    .build();
            bookmarkReviewTotalNumberDtoList.add(bookmarkReviewTotalNumberDto);
        }

        return bookmarkReviewTotalNumberDtoList;
    }
}
