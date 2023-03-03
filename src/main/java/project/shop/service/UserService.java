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
		int resultList = shopMapper.selectUserList();
		return resultList;
	}
	
	public void insertUser(UserDto user) throws Exception {
		shopMapper.insertUser(user);
	}
	
	public UserDto selectUser(UserDto userDto) throws Exception {
		int resultList = shopMapper.selectUserList();
		return null;
	}
	
	public void changePw(UserDto user) throws Exception {
		shopMapper.changePw(user);
	}
	
	public void deleteUser(UserDto user) throws Exception {
		shopMapper.deleteUser(user);
	}
}
