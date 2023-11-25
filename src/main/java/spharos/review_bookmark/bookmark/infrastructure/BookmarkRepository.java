package spharos.review_bookmark.bookmark.infrastructure;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import spharos.review_bookmark.bookmark.domain.Bookmark;
import spharos.review_bookmark.reviews_bookmarks.dto.ServiceIdCountBookmarkDto;
import java.util.List;

public interface BookmarkRepository extends JpaRepository<Bookmark, Long> {

    List<Bookmark> findByUserEmailOrderByIdDesc(String email);
    List<Bookmark> findByServiceId(Long serviceId);
    @Query("SELECT b.serviceId, COUNT(b.serviceId)" +
            "FROM Bookmark b " +
            "GROUP BY b.serviceId " +
            "ORDER BY COUNT(b.serviceId) DESC")
    List<ServiceIdCountBookmarkDto> findMostFrequentServiceIds(Pageable pageable); //jpql은 limit지원이 안되므로 페이징 사용
}