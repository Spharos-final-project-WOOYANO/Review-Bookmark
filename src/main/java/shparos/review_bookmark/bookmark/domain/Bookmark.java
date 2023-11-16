package shparos.review_bookmark.bookmark.domain;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@Table(name = "bookmark")
public class Bookmark {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false, length = 50, name = "user_email")
    private String userEmail;
    @Column(nullable = false, name = "service_id")
    private Long serviceId;

    private Bookmark(String userEmail, Long serviceId) {
        this.userEmail = userEmail;
        this.serviceId = serviceId;
    }

    // 찜 등록
    public static Bookmark createBookmark(String userEmail, Long serviceId) {
        return new Bookmark(userEmail, serviceId);
    }

}
