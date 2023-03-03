package project.shop.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import project.shop.dto.BoardDto;
import project.shop.dto.UserDto;
import project.shop.mapper.ShopMapper;

@Service //서비스임을 선언
public class UserService {
	@Autowired //Mapper와 연결
	private ShopMapper shopMapper;
	
	public int selectUserList() throws Exception {
		System.out.println("BoardService::"+shopMapper);
//		int resultList = shopMapper.selectUserList();
		return 0;
	}
	
	//userNo로 user정보를 불러오는 함수
	public UserDto selectUserNo(UserDto userDto) throws Exception {
		return shopMapper.selectUserNo(userDto);
	}
	
	//userId로 user정보를 불러오는 함수
	public UserDto selectUserId(UserDto userDto) throws Exception {
		return shopMapper.selectUserId(userDto);
	}
	
	//userEmail로 user정보를 불러오는 함수
	public UserDto selectUserEmail(UserDto userDto) throws Exception {
		return shopMapper.selectUserEmail(userDto);
	}
}
