package project.shop.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import project.shop.dto.CustomerServiceDto;
import project.shop.dto.OrderDto;
import project.shop.dto.ReviewDto;
import project.shop.dto.SalesDto;
import project.shop.dto.UserDto;

@Mapper //Mapper라고 선언함
public interface ShopMapper {
	// 여기서 지정한 메서드의 이름은 쿼리의 이름과 동일해야 함 (selectBoardList)
	List<UserDto> findAllUser(UserDto user) throws Exception; //모든 유저 정보받기
	List<SalesDto> findAllProd(SalesDto sales) throws Exception; //모든 상품 정보받기
	List<SalesDto> findCate(String cate); //카테고리로 상품정보 받기.
	List<CustomerServiceDto> findCustomerList(CustomerServiceDto customer); // 문의사항 정보받기
	List<Object> getOrder(Object user); //주문내역 보기
	List<SalesDto> searchProd(String sProd); //검색으로 상품 찾기.
	List<SalesDto> myPord(int userNo); //내 상품목록 보기.
	List<ReviewDto> showReview(int salesNo); //상품 리뷰 보기.
	void insertReview(ReviewDto review); //리뷰등록
	void insertUser(UserDto user); //회원가입
	void insertProduct(SalesDto sales); //상품등록
	void insertCustomer(CustomerServiceDto customer); // 문의작성
	void insertOrder(OrderDto order); // 구매날짜 작성
	void changePw(UserDto user); //비밀번호 변경
	void changeUser(UserDto user); //유저 정보 변경
	void deleteUser(UserDto user); //유저 제거
	UserDto findUser(UserDto user); //회원 정보받기
	SalesDto findProd(int num); //상품정보 받기.
	
	
	UserDto loginUser(UserDto user) throws Exception;	//userDto의 where no = userNo or id = userId or email = userEmail 에 해당하는 userDto를 return 
//	UserDto selectUserId(UserDto userDto) throws Exception;	//userDto의 where id = userID 에 해당하는 userDto를 return 
//	UserDto selectUserEmail(UserDto userDto) throws Exception;	//userDto의 where id = userEmail 에 해당하는 userDto를 return
}