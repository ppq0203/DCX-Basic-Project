package project.shop.controller;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import project.shop.dto.SalesDto;
import project.shop.service.SalesService;

@Controller // 컨트롤러라고 선언함
public class ShopController {
	@Autowired
	private SalesService salesService; //서비스와 연결
	
	@GetMapping("/main") //노테이션의 값으로 주소 지정
    public ModelAndView mainPage(SalesDto sales) throws Exception{
    	ModelAndView mv = new ModelAndView("/mainPage");
        List<SalesDto> list = salesService.selectProdList(sales);
        
        mv.addObject("list", list);
		System.out.println(mv);
		
		return mv;
    }
	
	@GetMapping("/header")
	public String header()
	{
		return "/header";
	}
	
	@GetMapping("/footer")
	public String footer()
	{
		return "/footer";
	}
}
