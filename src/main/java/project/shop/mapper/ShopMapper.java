package project.shop.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import project.shop.dto.BoardDto;
import project.shop.dto.UserDto;

@Mapper		// Mapper라고 선언함
public interface ShopMapper {
	// 여기서 지정한 메서드의 이름은 쿼리의 이름과 동일해야 함 (selectBoardList)
	List<BoardDto> selectBoardList() throws Exception;
	int select() throws Exception;
	void insertBoard(BoardDto board);
	void insertUser(UserDto user); //회원가입
	void changePw(UserDto user); //비밀번호 변경
	void deleteUser(UserDto user); //유저 제거
	void findId(UserDto user); //아이디 찾기 
	
	
	UserDto selectUser(UserDto userDto) throws Exception;	//userDto의 where no = userNo or id = userId or email = userEmail 에 해당하는 userDto를 return 
//	UserDto selectUserId(UserDto userDto) throws Exception;	//userDto의 where id = userID 에 해당하는 userDto를 return 
//	UserDto selectUserEmail(UserDto userDto) throws Exception;	//userDto의 where id = userEmail 에 해당하는 userDto를 return
	
}