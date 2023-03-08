package project.shop.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import project.shop.dto.CustomerServiceDto;
import project.shop.dto.ReviewDto;
import project.shop.dto.SalesDto;
import project.shop.service.ShopService;

@Controller // 컨트롤러라고 선언함
public class ShopBoardController {

    @Autowired
    private ShopService boardService; //서비스와 연결
    
    @GetMapping("/writereview")		//리뷰 작성 페이지
    public String openReviewWrite() throws Exception{
    	return "/reviewWrite";
    }
    
    @PostMapping("/writereview.do") //리뷰 작성 및 호출
    public ModelAndView openReview(ReviewDto review) throws Exception
    {
    	boardService.insertReview(review);
    	System.out.println("/writereview.do");
    	ModelAndView mv = new ModelAndView("/dbtest2");
        List<ReviewDto> list = boardService.findReviewList(review);  
        mv.addObject("list", list);
		System.out.println("found");
		
		return mv;
    }
    
    //문의 작성 페이지
    @GetMapping("/customer")
    public String opencustomer()
    {
    	return "test/dbtest";
    }
    
    //문의 작성 및 호출
    @PostMapping("/customer.do")
    public ModelAndView openCustomerList(CustomerServiceDto customer) throws Exception
    {
    	boardService.insertCustomer(customer);
    	ModelAndView mv = new ModelAndView("test/dbtest2");
    	List<CustomerServiceDto> list = boardService.findCustomerList(customer);
    	mv.addObject("list", list);
    	return mv;
    }
    
}