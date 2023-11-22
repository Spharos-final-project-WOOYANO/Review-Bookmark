package spharos.review_bookmark.review.domain;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import spharos.review_bookmark.global.common.response.domain.BaseEntity;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@Table(name = "review")
public class Review extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false, length = 50, name = "user_email")
    private String userEmail;
    @Column(nullable = false, name = "service_id")
    private Long serviceId;
    @Column(nullable = false, length = 10, name = "reservation_num")
    private String reservationNum;
    @Column(nullable = false, length = 500, name = "content")
    private String content;
    @Column(nullable = false, name = "reuse", columnDefinition = "boolean default true")
    private Boolean reuse;
    @Column(length = 500, name = "answer_content")
    private String answerContent;
    @Column(name = "answer_created_at")
    private LocalDateTime answerCreatedAt;
    @Column(name = "answer_updated_at")
    private LocalDateTime answerUpdatedAt;

    private Review(String userEmail, Long serviceId, String reservationNum, String content, Boolean reuse) {
        this.userEmail = userEmail;
        this.serviceId = serviceId;
        this.reservationNum = reservationNum;
        this.content = content;
        this.reuse = reuse;
    }

    // 리뷰 등록
    public static Review createReview(String userEmail, Long serviceId, String reservationNum,
                                        String content, Boolean reuse) {
        return new Review(userEmail, serviceId, reservationNum, content, reuse);
    }

}