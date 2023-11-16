package shparos.review_bookmark.bookmark.application;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import shparos.review_bookmark.bookmark.domain.Bookmark;
import shparos.review_bookmark.bookmark.infrastructure.BookmarkRepository;
import shparos.review_bookmark.bookmark.vo.response.BookmarkResponse;
import shparos.review_bookmark.global.common.response.ResponseCode;
import shparos.review_bookmark.global.exception.CustomException;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@RequiredArgsConstructor
@Service
public class BookmarkServiceImpl implements BookmarkService{

    private final BookmarkRepository bookmarkRepository;

    // 찜리스트조회
    @Override
    public List<BookmarkResponse> getBookmarkList(String email) {

        // 유저 이메일로 찜리스트조회
        List<Bookmark> bookmarkList = bookmarkRepository.findByUserEmailOrderByIdDesc(email);

        // 찜리스트가 없는 경우 null을 return
        if(bookmarkList.isEmpty()) {
            return null;
        }

        List<BookmarkResponse> responseList = new ArrayList<>();
        for(Bookmark bookmark:bookmarkList) {
            BookmarkResponse response = BookmarkResponse.builder()
                    .bookmarkId(bookmark.getId())
                    .serviceId(bookmark.getServiceId())
                    .build();
            responseList.add(response);
        }

        return responseList;
    }

    // 찜 삭제
    @Override
    @Transactional
    public void deleteBookmark(Long bookmarkId) {

        // 찜 삭제
        Bookmark bookmark = bookmarkRepository.findById(bookmarkId)
                .orElseThrow(() -> new CustomException(ResponseCode.CANNOT_FIND_BOOKMARK));
        bookmarkRepository.delete(bookmark);
    }

    // 찜 추가
    @Override
    @Transactional
    public void registerBookmark(String email, Long serviceId) {

        // 찜 추가
        Bookmark bookmark = Bookmark.createBookmark(email, serviceId);
        bookmarkRepository.save(bookmark);
    }
}
