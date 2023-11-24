package spharos.review_bookmark.review.application;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import spharos.review_bookmark.global.common.response.ResponseCode;
import spharos.review_bookmark.global.exception.CustomException;
import spharos.review_bookmark.review.domain.Review;
import spharos.review_bookmark.review.domain.ReviewImage;
import spharos.review_bookmark.review.infrastructure.ReviewImageRepository;
import spharos.review_bookmark.review.infrastructure.ReviewRepository;
import spharos.review_bookmark.review.vo.request.ReviewReplyModifyRequest;
import spharos.review_bookmark.review.vo.request.ReviewReplyRegisterRequest;
import spharos.review_bookmark.review.vo.response.ClientReviewDetailResponse;
import spharos.review_bookmark.review.vo.response.ClientReviewListResponse;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@RequiredArgsConstructor
@Service
public class ClientReviewServiceImpl implements ClientReviewService {

    private final ReviewRepository reviewRepository;
    private final ReviewImageRepository reviewImageRepository;


    // 리뷰 리스트 조회(전체)
    @Override
    public List<ClientReviewListResponse> getClientReviewList(Long serviceId) {

        // 리뷰 리스트 조회
        List<Review> reviewList = reviewRepository.findByServiceId(serviceId);

        // 리뷰가 없으면 null을 리턴
        if(reviewList.isEmpty()) {
            return null;
        }

        List<ClientReviewListResponse> responseList = new ArrayList<>();
        for(Review review : reviewList) {
            ClientReviewListResponse response = ClientReviewListResponse.builder()
                    .reviewId(review.getId())
                    .reservationNum(review.getReservationNum())
                    .createdAt(review.getCreatedAt())
                    .reuse(review.getReuse())
                    .userEmail(review.getUserEmail())
                    .content(review.getContent())
                    .build();
            responseList.add(response);
        }

        return responseList;
    }

    // 리뷰 상세 조회
    @Override
    public ClientReviewDetailResponse getClientReviewDetail(Long reviewId) {

        // 리뷰 정보를 조회
        Review review = reviewRepository.findById(reviewId)
                .orElseThrow(() -> new CustomException(ResponseCode.CANNOT_FIND_REVIEW));

        // 리뷰 이미지 정보를 조회
        List<ReviewImage> reviewImageList = reviewImageRepository.findByReview(review);
        List<String> imageUrlList = new ArrayList<>();
        if(!reviewImageList.isEmpty()) {
            for(ReviewImage reviewImage : reviewImageList) {
                imageUrlList.add(reviewImage.getImageUrl());
            }
        }

        return ClientReviewDetailResponse.builder()
                .reviewId(review.getId())
                .reviewImageUrlList(imageUrlList)
                .userEmail(review.getUserEmail())
                .reuse(review.getReuse())
                .createdAt(review.getCreatedAt())
                .content(review.getContent())
                .answerContent(review.getAnswerContent())
                .build();
    }

    // 리뷰 답글 작성
    @Override
    @Transactional
    public void registerReviewReply(ReviewReplyRegisterRequest request) {

        // 리뷰 정보를 조회
        Review review = reviewRepository.findById(request.getReviewId())
                .orElseThrow(() -> new CustomException(ResponseCode.CANNOT_FIND_REVIEW));

        // 리뷰 답글 작성
        review.registerReply(request.getAnswerContent(), LocalDateTime.now());
    }

    // 리뷰 답글 수정
    @Override
    @Transactional
    public void modifyReviewReply(ReviewReplyModifyRequest request) {

        // 리뷰 정보를 조회
        Review review = reviewRepository.findById(request.getReviewId())
                .orElseThrow(() -> new CustomException(ResponseCode.CANNOT_FIND_REVIEW));

        // 리뷰 답글 수정
        review.modifyReply(request.getAnswerContent(), LocalDateTime.now());
    }
}
