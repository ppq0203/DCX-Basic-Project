package project.shop.service;

import java.util.List;

import org.apache.tomcat.jni.File;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import project.shop.dto.BoardDto;
import project.shop.dto.OrderDto;
import project.shop.dto.SalesDto;
import project.shop.dto.UserDto;
import project.shop.mapper.ShopMapper;

@Service //서비스임을 선언
public class SalesService {
	@Autowired //Mapper와 연결
	private ShopMapper shopMapper;
	
	//상품등록
	public void insertProduct(SalesDto sales) throws Exception {
		shopMapper.insertProduct(sales);
	}

	public List<SalesDto> selectProdList(SalesDto sales) throws Exception {
		List<SalesDto> resultList = shopMapper.findAllProd(sales);
		return resultList;
	}

	public void insertOrder(OrderDto order) {
		shopMapper.insertOrder(order);
	}
}
