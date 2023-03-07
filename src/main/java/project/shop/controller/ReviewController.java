package project.shop.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import project.shop.dto.BoardDto;
import project.shop.dto.ReviewDto;
import project.shop.dto.SalesDto;
import project.shop.service.ShopService;

@Controller // 컨트롤러라고 선언함
public class ReviewController {

    @Autowired
    private ShopService reviewService; //서비스와 연결
    
    @GetMapping("/writereview")		//게시글 작성 화면 호출
    public String openReviewWrite() throws Exception{
    	return "/dbtest";
    }
    
    @PostMapping("/writereview.do")
    public ModelAndView openReview(ReviewDto review) throws Exception
    {
    	reviewService.insertReview(review);
    	System.out.println("/writereview.do");
    	ModelAndView mv = new ModelAndView("/dbtest2");
        List<ReviewDto> list = reviewService.findReviewList(review);  
        mv.addObject("list", list);
		System.out.println("found");
		
		return mv;
    }
    
   
    
    @GetMapping("/writereview.do")
    public String openReview()
    {
    	return "/dbtest2";
    }
    
}