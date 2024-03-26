package com.dw.Monaca.service.impl;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dw.Monaca.dto.ItemShopCartDto;
import com.dw.Monaca.dto.ResponseDto;
import com.dw.Monaca.enumStatus.ResultCode;
import com.dw.Monaca.exception.InvalidRequestException;
import com.dw.Monaca.jwtauthority.model.User;
import com.dw.Monaca.jwtauthority.repository.UserRepository;
import com.dw.Monaca.jwtauthority.util.SecurityUtil;
import com.dw.Monaca.model.CandyPoint;
import com.dw.Monaca.model.CandyPointItem;
import com.dw.Monaca.model.ItemShop;
import com.dw.Monaca.model.ItemShopCart;
import com.dw.Monaca.model.UserItem;
import com.dw.Monaca.repository.CandyPointItemRepository;
import com.dw.Monaca.repository.CandyPointRepository;
import com.dw.Monaca.repository.ItemShopCartRepository;
import com.dw.Monaca.repository.ItemShopRepository;
import com.dw.Monaca.repository.UserItemRepository;
import com.dw.Monaca.service.ItemShopCartService;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class ItemShopCartServiceImpl implements ItemShopCartService{

	private final ItemShopCartRepository itemShopCartRepository;
	private final UserRepository userRepository;
	private final UserItemRepository userItemRepository;
	private final ItemShopRepository itemShopRepository;
	private final CandyPointRepository candyPointRepository;
	private final CandyPointItemRepository candyPointItemRepository;
	
	@Autowired
	public ItemShopCartServiceImpl(ItemShopCartRepository itemShopCartRepository, UserRepository userRepository,
			UserItemRepository userItemRepository, ItemShopRepository itemShopRepository,
			CandyPointRepository candyPointRepository, CandyPointItemRepository candyPointItemRepository) {
		this.itemShopCartRepository = itemShopCartRepository;
		this.userRepository = userRepository;
		this.userItemRepository = userItemRepository;
		this.itemShopRepository = itemShopRepository;
		this.candyPointRepository = candyPointRepository;
		this.candyPointItemRepository = candyPointItemRepository;
	}


	private User getAuthenticatedUser() {
	    String currentLoginId = SecurityUtil.getCurrentLoginId()
	        .orElseThrow(() -> new InvalidRequestException("Authentication", "사용자 인증 정보를 찾을 수 없습니다."));
	    return userRepository.findByLoginId(currentLoginId)
	        .orElseThrow(() -> new InvalidRequestException("User Not Found", "회원이 아닙니다."));
	}

	
	// 현재 로그인한 User의 cart(장바구니)에 담아놓은 Item 전부 조회
	@Override
	public ResponseDto<List<ItemShopCart>> getAllItemShopCart() {
		User user = getAuthenticatedUser();
		List<ItemShopCart> itemShopCarts = itemShopCartRepository.findByUser(user);
		if(itemShopCarts.isEmpty()) {
			return new ResponseDto<>(ResultCode.SUCCESS.name(),itemShopCarts,"장바구니에 담아놓은 Item이 없습니다.");
		}
		return new ResponseDto<>(ResultCode.SUCCESS.name(),itemShopCarts,ResultCode.SUCCESS.getMsg());
	}

	
	// 현재 로그인한 User의 cart(장바구니)에 담아놓은 Item을 ID로 조회
	@Override
	public ResponseDto<ItemShopCart> getItemShopCartById(Long id) {
		 Optional<ItemShopCart> itemShopCartOptional = itemShopCartRepository.findById(id);
		    User user = getAuthenticatedUser();
		    if(itemShopCartOptional.isPresent()) {

		        ItemShopCart itemShopCart = itemShopCartOptional.get();

		        if(!itemShopCart.getUser().equals(user)) {
		            throw new InvalidRequestException("Unauthorized", "해당 장바구니에 대한 권한이 없습니다.");
		        }

		        return new ResponseDto<>(ResultCode.SUCCESS.name(),itemShopCart,ResultCode.SUCCESS.getMsg());

		    } else {

		        throw new InvalidRequestException("Not Found", "해당 ID를 가진 장바구니 아이템이 존재하지 않습니다.");
		    }
	}

	// 현재 로그인한 User의 cart에 Item 담기
	@Override
	public ResponseDto<Long> createItemShopCart(ItemShopCartDto itemShopCartDto) {
		 User user = getAuthenticatedUser(); // 현재 인증된 사용자를 가져옴
		   ItemShop itemShop = itemShopRepository.findById(itemShopCartDto.getItemShop())
		        .orElseThrow(() -> new InvalidRequestException("Item Not Found", "아이템을 찾을 수 없습니다."));

		    ItemShopCart itemShopCart = new ItemShopCart();
		    itemShopCart.setUser(user);
		    itemShopCart.setItemShop(itemShop);

		    ItemShopCart savedItemShopCart = itemShopCartRepository.save(itemShopCart);

		    return new ResponseDto<>(ResultCode.SUCCESS.name(), savedItemShopCart.getId(), "아이템이 장바구니에 추가되었습니다.");
		}

	// 결제 성공 시 ItemShopCart -> UserItem 저장
	private UserItem converToUserItem(ItemShopCart itemShopCart, User user) {
		UserItem userItem = new UserItem();
		userItem.setWearing(false);
		userItem.setItemShop(itemShopCart.getItemShop());
		userItem.setUser(user);
		return userItem;
	}
	
	
	@Override
	public ResponseDto<Long> purchaseItemShopCart(Long id) {
	    User user = getAuthenticatedUser(); // 현재 인증된 사용자를 가져옴
	    ItemShopCart itemShopCart = itemShopCartRepository.findById(id) // 용자가 장바구니에 담아놓은 아이템을 찾음.
	        .orElseThrow(() -> new InvalidRequestException("ItemShopCart Not Found", "장바구니 항목을 찾을 수 없습니다."));
	    
	    // 아이템 가격 //아이템의 가격을 확인.
	    int itemPrice = itemShopCart.getItemShop().getPrice();
	    
	    // 사용자의 총 캔디포인트 계산
	    // 리스트에서 스트림을 생성하고, mapToInt 메소드를 사용해서 각 CandyPoint 객체의 point 값을 추출하여 합계를 계산할 수 있음. 
	    int totalCandyPoints = candyPointRepository.findAllByUser(user)
	        .stream()
	        .mapToInt(CandyPoint::getPoint)
	        .sum();
	    
	    // 캔디포인트가 아이템 가격보다 적은 경우 예외 발생
	    if (totalCandyPoints < itemPrice) {
	        throw new InvalidRequestException("Insufficient CandyPoints", "캔디포인트가 부족합니다.");
	    }
	    
	    UserItem userItem = converToUserItem(itemShopCart, user);
	    UserItem savedUserItem = userItemRepository.save(userItem);
	    
	    // 결제 후 ItemShopCart에서 삭제
	    itemShopCartRepository.delete(itemShopCart);

	    // 캔디포인트 차감
	    CandyPoint candyPoint = new CandyPoint();
	    candyPoint.setUser(user);
	    candyPoint.setPoint(-itemPrice); // 아이템 가격만큼 차감
	    candyPoint.setTimeStamp(LocalDateTime.now());

	    // CandyPointItem 설정
	    CandyPointItem candyPointItem = candyPointItemRepository.findByItemName("Item Purchase")
	        .orElseGet(() -> {
	            CandyPointItem newItem = new CandyPointItem("Item Purchase");
	            return candyPointItemRepository.save(newItem);
	        });
	    candyPoint.setCandyPointItem(candyPointItem);

	    candyPointRepository.save(candyPoint);

	    return new ResponseDto<>(ResultCode.SUCCESS.name(), savedUserItem.getId(), "아이템이 구매되었습니다.");
	}


	// 현재 로그인한 User의 cart에 담아놓은 Item 전부 삭제
	@Override
	public ResponseDto<String> deleteAllItemShopCart() {
		User user = getAuthenticatedUser();
		List<ItemShopCart> carts = itemShopCartRepository.findByUser(user);
		
		if(carts.isEmpty()) {
			throw new InvalidRequestException("ItemShopCart Empty", "장바구니에 아이템이 존재하지 않습니다.");
		}

		itemShopCartRepository.deleteAll(carts);
		return new ResponseDto<>(ResultCode.SUCCESS.name(),"Deleted","장바구니가 비워졌습니다..");	
	}
	// 현재 로그인한 User의 cart에 담아놓은 Item ID로 선택하여 삭제
	@Override
	public ResponseDto<String> deleteItemShopCartById(Long id) {
		 User user = getAuthenticatedUser();
		    ItemShopCart cart = itemShopCartRepository.findById(id)
		        .orElseThrow(() -> new InvalidRequestException("ItemShopCart Not Found", "장바구니 항목을 찾을 수 없습니다."));

		    if (!cart.getUser().equals(user)) {
		        throw new InvalidRequestException("Unauthorized", "해당 장바구니 항목에 대한 권한이 없습니다.");
		    }

		    itemShopCartRepository.deleteById(id);
		    return new ResponseDto<>(ResultCode.SUCCESS.name(), "Deleted", "장바구니 항목이 삭제되었습니다.");
		}

}
