package project.shop.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import project.shop.dto.CustomerServiceDto;
import project.shop.mapper.ShopMapper;

@Service //서비스임을 선언
public class ShopService {
	@Autowired //Mapper와 연결
	private ShopMapper shopMapper;
	
	public List<CustomerServiceDto> findCustomerList(CustomerServiceDto customer) {
		List<CustomerServiceDto> resultList = shopMapper.findCustomerList(customer);
		return resultList;
	}

	public void insertCustomer(CustomerServiceDto customer) {
		shopMapper.insertCustomer(customer);
		
	}

}
