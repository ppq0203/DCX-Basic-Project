package project.shop.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import project.shop.dto.BoardDto;
import project.shop.dto.SalesDto;
import project.shop.dto.UserDto;
import project.shop.mapper.ShopMapper;
import project.shop.service.SalesService;
import project.shop.service.UserService;


@Controller
public class SalesController {
	@Autowired
    private SalesService salesService; //서비스와 연결
	
	//상품등록 페이지
	@GetMapping("/addprod")
	public String addprodPage(@ModelAttribute SalesDto sales, Model model)
	{
		model.addAttribute("Sales", sales);
		return "/productWrite";
	}
		
	//상품목록 페이지
	@GetMapping("/showprod")
	public String prodPage()
	{
		return "/dbtest";
	}
	
	@PostMapping("/showprod")
	public ModelAndView prodPage(SalesDto sales) throws Exception
	{
		//templates 폴더 아래있는 /boardList.html을 의미함. Thymeleaf와 같은 템플릿엔진을 사용할 경우 스프링 부트의 자동 설정 기능으로 '.html'과 같은 접미사 생략 가능
				System.out.println("/showprod");
		    	ModelAndView mv = new ModelAndView("/dbtest"); 
		        //게시글 목록을 조회하기 위해 ServiceImpl 클래스의 selectBoardList 메서드 호출
		        List<SalesDto> list = salesService.selectProdList(sales);  
		        mv.addObject("list", list);
				System.out.println("found");
				
				return mv;
	}
	
	@PostMapping("insertProduct")
	public String insertProduct(SalesDto sales) throws Exception
	{
		System.out.println(" [+] post "+sales);
		salesService.insertProduct(sales);
		return "redirect:/main";
	}
}
