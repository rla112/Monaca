package com.dw.Monaca.service.impl;

import java.util.Collections;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dw.Monaca.dto.ResponseDto;
import com.dw.Monaca.enumStatus.ResultCode;
import com.dw.Monaca.exception.InvalidRequestException;
import com.dw.Monaca.jwtauthority.model.User;
import com.dw.Monaca.jwtauthority.repository.UserRepository;
import com.dw.Monaca.jwtauthority.util.SecurityUtil;
import com.dw.Monaca.model.Rating;
import com.dw.Monaca.repository.RatingRepository;
import com.dw.Monaca.service.RatingService;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class RatingServiceImpl implements RatingService {

	private final RatingRepository ratingRepository;
	private final UserRepository userRepository;

	@Autowired
	public RatingServiceImpl(RatingRepository ratingRepository, UserRepository userRepository) {
	super();
	this.ratingRepository = ratingRepository;
	this.userRepository = userRepository;
}

	private User getAuthenticatedUser() {
	    String currentLoginId = SecurityUtil.getCurrentLoginId()
	        .orElseThrow(() -> new InvalidRequestException("Authentication", "사용자 인증 정보를 찾을 수 없습니다."));
	    return userRepository.findByLoginId(currentLoginId)
	        .orElseThrow(() -> new InvalidRequestException("User Not Found", "회원이 아닙니다."));
	}
	
	public ResponseDto<Rating> upgradeToOrangeCandy(User user) {
	    Rating rating = ratingRepository.findByCandyGrade("ORANGE_CANDY")
	                .orElseGet(() -> {
	                    Rating newRating = new Rating("ORANGE_CANDY", "image_path");
	                    return ratingRepository.save(newRating);
	                });
	    user.setRatings(Collections.singleton(rating));
	    userRepository.save(user);
	    return new ResponseDto<>(ResultCode.SUCCESS.name(), rating, ResultCode.SUCCESS.getMsg());
	}


	public ResponseDto<Rating> upgradeToYellowCandy(User user) {
	    Rating rating = ratingRepository.findByCandyGrade("YELLOW_CANDY")
	                .orElseGet(() -> {
	                    Rating newRating = new Rating("YELLOW_CANDY", "image_path");
	                    return ratingRepository.save(newRating);
	                });
	    user.setRatings(Collections.singleton(rating));
	    userRepository.save(user);
	    return new ResponseDto<>(ResultCode.SUCCESS.name(), rating, ResultCode.SUCCESS.getMsg());
	}

	public ResponseDto<Rating> upgradeToGreenCandy(User user) {
	    Rating rating = ratingRepository.findByCandyGrade("GREEN_CANDY")
	                .orElseGet(() -> {
	                    Rating newRating = new Rating("GREEN_CANDY", "image_path");
	                    return ratingRepository.save(newRating);
	                });
	    user.setRatings(Collections.singleton(rating));
	    userRepository.save(user);
	    return new ResponseDto<>(ResultCode.SUCCESS.name(), rating, ResultCode.SUCCESS.getMsg());
	}

}
