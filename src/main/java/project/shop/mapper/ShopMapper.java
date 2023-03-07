package project.shop.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import project.shop.dto.BoardDto;
import project.shop.dto.CustomerServiceDto;
import project.shop.dto.OrderDto;
import project.shop.dto.ReviewDto;
import project.shop.dto.SalesDto;
import project.shop.dto.UserDto;

@Mapper //Mapper라고 선언함
public interface ShopMapper {
	// 여기서 지정한 메서드의 이름은 쿼리의 이름과 동일해야 함 (selectBoardList)
	List<BoardDto> selectBoardList() throws Exception;
	List<UserDto> findAllUser(UserDto user) throws Exception; //모든 유저 정보받기
	List<ReviewDto> findReviewList(ReviewDto review)throws Exception; //모든 리뷰 정보 받
	List<SalesDto> findAllProd(SalesDto sales) throws Exception; //모든 상품 정보받기
	List<CustomerServiceDto> findCustomerList(CustomerServiceDto customer); // 문의사항 정보받기
	int select() throws Exception;
	void insertBoard(BoardDto board);
	void insertReview(ReviewDto review); //리뷰등록
	void insertUser(UserDto user); //회원가입
	void insertProduct(SalesDto sales); //상품등록
	void insertCustomer(CustomerServiceDto customer); // 문의작성
	void insertOrder(OrderDto order); // 구매날짜 작성
	void changePw(UserDto user); //비밀번호 변경
	void changeUser(UserDto user); //유저 정보 변경
	void deleteUser(UserDto user); //유저 제거
	UserDto findUser(UserDto user); //회원 정보받기
	
	UserDto loginUser(UserDto user) throws Exception;	//userDto의 where no = userNo or id = userId or email = userEmail 에 해당하는 userDto를 return 
//	UserDto selectUserId(UserDto userDto) throws Exception;	//userDto의 where id = userID 에 해당하는 userDto를 return 
//	UserDto selectUserEmail(UserDto userDto) throws Exception;	//userDto의 where id = userEmail 에 해당하는 userDto를 return
	
	
	
	
	
	
}