package spharos.review_bookmark.reviews_bookmarks.application;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import spharos.review_bookmark.bookmark.infrastructure.BookmarkRepository;
import spharos.review_bookmark.review.infrastructure.ReviewRepository;
import spharos.review_bookmark.reviews_bookmarks.dto.MostBookmarkReviewCountDto;
import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class ReviewBookmarkServiceImpl implements ReviewBookmarkService{

    private final ReviewRepository reviewRepository;
    private final BookmarkRepository bookmarkRepository;

    @Override
    public List<MostBookmarkReviewCountDto> getBestBookmarkReviewCount() {

        Pageable pageable = Pageable.ofSize(7);

        return bookmarkRepository.findMostFrequentServiceIds(pageable).stream()
                .map(dto -> MostBookmarkReviewCountDto.builder()
                        .serviceId(dto.getServiceId())
                        .bookmarkCount(dto.getBookmarkCount())
                        .reviewCount(reviewRepository.findCountByServiceId(dto.getServiceId()))
                        .build())
                .toList();

    }
}
