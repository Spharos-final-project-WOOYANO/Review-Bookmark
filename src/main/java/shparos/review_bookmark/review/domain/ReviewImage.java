package shparos.review_bookmark.review.domain;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@Table(name = "review_image")
public class ReviewImage {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(nullable = false)
    private Review review;
    @Column(nullable = false, name = "image_url")
    private String imageUrl;

    private ReviewImage(Review review, String imageUrl) {
        this.review = review;
        this.imageUrl = imageUrl;
    }

    // 리뷰 이미지 등록
    public static ReviewImage createReviewImage(Review review, String imageUrl) {
        return new ReviewImage(review, imageUrl);
    }

}
