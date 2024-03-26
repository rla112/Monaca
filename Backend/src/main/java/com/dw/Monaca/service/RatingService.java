package com.dw.Monaca.service;

import com.dw.Monaca.dto.ResponseDto;
import com.dw.Monaca.jwtauthority.model.User;
import com.dw.Monaca.model.Rating;

public interface RatingService {

	// RedCandy(유저 가입 시 기본 등급) => 기본 4가지 무료강의, 기본 캐릭터 세트 제공
	
	// OrangeCandy(등업기준 출석사탕 15개) => RedCandy 혜택 + 유료강의 1개, (출석 사탕 29개 모으면 하나 더 제공..?)=> 캔디포인트 10개 지급 ..? 변경 물어보기
	ResponseDto<Rating> upgradeToOrangeCandy(User user);
	// YellowCandy(등업기준 출석사탕 30개) => OrangeCandy 혜택 + 유료강의 1개 + 아이템 1개 무료 제공
	ResponseDto<Rating> upgradeToYellowCandy(User user);
	// GreenCandy(등업기준 출석사탕 45개) => 전 강의 무료 + 아이템 2개 무료 제공
	ResponseDto<Rating> upgradeToGreenCandy(User user);
	// RainbowCandy(관리자,교수) => 전 강의 무료 + 모든 아이템 무료 제공
}
