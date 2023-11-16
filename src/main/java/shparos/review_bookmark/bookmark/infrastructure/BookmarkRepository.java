package shparos.review_bookmark.bookmark.infrastructure;

import org.springframework.data.jpa.repository.JpaRepository;
import shparos.review_bookmark.bookmark.domain.Bookmark;

import java.util.List;

public interface BookmarkRepository extends JpaRepository<Bookmark, Long> {

    List<Bookmark> findByUserEmailOrderByIdDesc(String email);

}
