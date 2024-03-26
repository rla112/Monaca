package com.dw.Monaca.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dw.Monaca.dto.ResponseDto;
import com.dw.Monaca.enumStatus.ResultCode;
import com.dw.Monaca.exception.InvalidRequestException;
import com.dw.Monaca.jwtauthority.model.User;
import com.dw.Monaca.jwtauthority.repository.UserRepository;
import com.dw.Monaca.jwtauthority.util.SecurityUtil;
import com.dw.Monaca.model.Candy;
import com.dw.Monaca.repository.CandyRepository;
import com.dw.Monaca.service.CandyService;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class CandyServiceImpl implements CandyService {

	private final CandyRepository candyRepository;
	private final UserRepository userRepository;
	
	@Autowired
	public CandyServiceImpl(CandyRepository candyRepository, UserRepository userRepository) {
		super();
		this.candyRepository = candyRepository;
		this.userRepository = userRepository;
	}


	private User getAuthenticatedUser() {
	    String currentLoginId = SecurityUtil.getCurrentLoginId()
	        .orElseThrow(() -> new InvalidRequestException("Authentication", "사용자 인증 정보를 찾을 수 없습니다."));
	    return userRepository.findByLoginId(currentLoginId)
	        .orElseThrow(() -> new InvalidRequestException("User Not Found", "회원이 아닙니다."));
	}

	
	// 모든 candy 불러오기
    @Override
    public ResponseDto<List<Candy>> getAllCandies() {
    	getAuthenticatedUser();
        List<Candy> candies = candyRepository.findAll();
        if (candies.isEmpty()) {
            throw new InvalidRequestException("Candy empty", "캔디가 존재하지 않습니다.");
        }
        return new ResponseDto<>(ResultCode.SUCCESS.name(), candies, ResultCode.SUCCESS.getMsg());
    }

    // candyID로 candy 불러오기 
    @Override
    public ResponseDto<Candy> getCandyById(Long id) {
    	getAuthenticatedUser();
        Optional<Candy> optionalCandy = candyRepository.findById(id);
        if (optionalCandy.isPresent()) {
            Candy candy = optionalCandy.get();
            return new ResponseDto<>(ResultCode.SUCCESS.name(), candy, ResultCode.SUCCESS.getMsg());
        } else {
            // 존재하지 않는 캔디에 대한 예외 처리
            throw new InvalidRequestException("캔디를 찾을 수 없습니다.", "Candy with ID " + id + " does not exist.");
        }
    }

    
    // candy 저장하기
    @Override
    public ResponseDto<Candy> saveCandy(Candy candy) {
      Candy candies = new Candy();
      candies.setCandyName(candy.getCandyName());
      candies.setImage(candy.getImage());
      candyRepository.save(candies);
      
      return new ResponseDto<>(ResultCode.SUCCESS.name(),candies,ResultCode.SUCCESS.getMsg());
    }

    // candy 수정하기
    @Override
    public ResponseDto<Candy> updateCandy(Candy updateCandy, Long id) {
    	Optional<Candy> candyOptional = candyRepository.findById(id);
    	if(!candyOptional.isPresent()) {
    		throw new InvalidRequestException("Candy Not Found", "해당 캔디를 찾을 수 없습니다." );
    	}
    	
    	Candy candy = candyOptional.get();
    	
    	candy.setCandyName(updateCandy.getCandyName());
    	candy.setImage(updateCandy.getImage());
    	candyRepository.save(candy);
    	
    	return new ResponseDto<>(ResultCode.SUCCESS.name(),candy,ResultCode.SUCCESS.getMsg());
    	
    }

    // candy 삭제하기 // 삭제 후에는 삭제 성공 여부와 메시지만을 전달하면 되기 때문에 <Candy>가 아닌 <String>을 넣어줌
    public ResponseDto<String> deleteCandyById(Long id) {
        // 캔디 ID에 해당하는 캔디가 존재하는지 확인
        if (candyRepository.existsById(id)) {
            // 캔디 ID에 해당하는 캔디를 삭제하고 성공 메시지를 반환
            candyRepository.deleteById(id);
            return new ResponseDto<>(ResultCode.SUCCESS.name(), "캔디가 성공적으로 삭제되었습니다.", ResultCode.SUCCESS.getMsg());
        } else {
            // 캔디 ID에 해당하는 캔디가 존재하지 않으면 예외 처리
            throw new InvalidRequestException("캔디를 삭제할 수 없습니다.", "Candy with ID " + id + " does not exist.");
        }
    }
    
	
}
