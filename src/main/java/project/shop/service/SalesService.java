package project.shop.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import project.shop.dto.OrderDto;
import project.shop.dto.SalesDto;
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
	
	public SalesDto findProd(int num) throws Exception 
	{
		return shopMapper.findProd(num);
	}

	public List<SalesDto> selectCateList(String cate) 
	{
		List<SalesDto> resultList = shopMapper.findCate(cate);
		return resultList;
	}

	public List<Object> getOrder(Object user) {
		List<Object> resultList = shopMapper.getOrder(user);
		return resultList;
	}

	public List<SalesDto> searchProd(String sProd) {
		List<SalesDto> resultList = shopMapper.searchProd(sProd);
		return resultList;
	}

	public List<SalesDto> myProd(int userNo) {
		List<SalesDto> resultList = shopMapper.myPord(userNo);
		return resultList;
	}
}
