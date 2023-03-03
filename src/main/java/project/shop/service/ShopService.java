package project.shop.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import project.shop.dto.BoardDto;
import project.shop.mapper.ShopMapper;

@Service //서비스임을 선언
public class ShopService {
	@Autowired //Mapper와 연결
	private ShopMapper boardMapper;


	public List<BoardDto> selectBoardList() throws Exception {
		System.out.println("BoardService::"+boardMapper);
		List<BoardDto> resultList = boardMapper.selectBoardList();
		return resultList;
	}
	
	public int select() throws Exception {
		// TODO Auto-generated method stub
		System.out.println("BoardService::"+boardMapper);
		return boardMapper.select();
	}
}
