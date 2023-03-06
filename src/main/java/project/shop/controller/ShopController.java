package project.shop.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import project.shop.dto.UserDto;
import project.shop.service.ShopService;

@Controller // 컨트롤러라고 선언함
public class ShopController {
	@Autowired
	private ShopService shopService; //서비스와 연결
	
	@GetMapping("/main") //노테이션의 값으로 주소 지정
    public ModelAndView mainPage() throws Exception{
    	//templates 폴더 아래있는 /boardList.html을 의미함. Thymeleaf와 같은 템플릿엔진을 사용할 경우 스프링 부트의 자동 설정 기능으로 '.html'과 같은 접미사 생략 가능
    	ModelAndView mv = new ModelAndView("/mainPage"); 
//    	System.out.println(shopService.select());
        //메인페이지 상품을 띄우기 위해 SalesService 클래스의 selectSalesList 메서드 호출
//        List<SalesDto> list = shopService.selectSalesList();  
//        mv.addObject("list", list);
        return mv;
    }
	
}
