package spharos.review_bookmark.bookmark.infrastructure;

import org.springframework.data.jpa.repository.JpaRepository;
import spharos.review_bookmark.bookmark.domain.Bookmark;

import java.util.List;

public interface BookmarkRepository extends JpaRepository<Bookmark, Long> {

    List<Bookmark> findByUserEmailOrderByIdDesc(String email);
    List<Bookmark> findByServiceId(Long serviceId);

}