package project.shop.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import project.shop.dto.BoardDto;
import project.shop.dto.CustomerServiceDto;
import project.shop.dto.ReviewDto;
import project.shop.mapper.ShopMapper;

@Service //서비스임을 선언
public class ShopService {
	@Autowired //Mapper와 연결
	private ShopMapper shopMapper;


	public List<ReviewDto> findReviewList(ReviewDto review) throws Exception {
		System.out.println("BoardService::"+shopMapper);
		List<ReviewDto> resultList = shopMapper.findReviewList(review);
		return resultList;
	}
	
	public List<CustomerServiceDto> findCustomerList(CustomerServiceDto customer) {
		List<CustomerServiceDto> resultList = shopMapper.findCustomerList(customer);
		return resultList;
	}
	
//	public int select() throws Exception {
//		// TODO Auto-generated method stub
//		System.out.println("BoardService::"+shopMapper);
//		return shopMapper.select();
//	}

	public void insertReview(ReviewDto review) {
		shopMapper.insertReview(review);
	}

	public void insertCustomer(CustomerServiceDto customer) {
		shopMapper.insertCustomer(customer);
		
	}

}
