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
	
	public int select() throws Exception {
		// TODO Auto-generated method stub
		System.out.println("UserService::"+shopMapper);
		return shopMapper.select();
	}
	
	public List<UserDto> selectUserList(UserDto user) throws Exception {
		System.out.println("UserService::"+shopMapper);
		List<UserDto> resultList = shopMapper.findUser(user);
		return resultList;
	}
	
	//회원가입
	public void insertUser(UserDto user) throws Exception {
		shopMapper.insertUser(user);
	}
	
	public UserDto loginUser(UserDto user) throws Exception {
		return shopMapper.loginUser(user);
	}
	
	//비밀번호 변경
	public void changePw(UserDto user) throws Exception {
		shopMapper.changePw(user);
	}
	
	//유저 제거
	public void deleteUser(UserDto user) throws Exception {
		shopMapper.deleteUser(user);
	}
	
	//아이디 찾기
	public void findUser(UserDto user) throws Exception {
		shopMapper.findUser(user);
	}
}
