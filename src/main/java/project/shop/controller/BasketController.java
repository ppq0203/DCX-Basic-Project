package project.shop.controller;

import java.util.ArrayList;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import project.shop.dto.BasketDto;
import project.shop.dto.SalesDto;
import project.shop.service.SalesService;

@Controller
public class BasketController {
	
	@Autowired
    private SalesService salesService; //서비스와 연결
	//장바구니에 담기
	@PostMapping("/addBasket")
	@ResponseBody
	public int addbasket(HttpSession session,@RequestParam("salesNo") int salesNo, @RequestParam("amount") int amount) throws Exception {
		System.out.println("addBasket");
		System.out.println(salesNo);
		System.out.println(session.getAttribute("baskets"));
		BasketDto basketDto = new BasketDto();	//basketDto 생성
		SalesDto salesDto = salesService.findProd(salesNo);
		basketDto.setSalesDto(salesDto);
		basketDto.setAmount(amount);
		ArrayList<BasketDto> baskets;
		
		if(session.getAttribute("baskets")==null)	//장바구니 리스트가 세션에 존재하는지 체크
		{
			baskets = new ArrayList<BasketDto>();
			session.setAttribute("baskets", baskets);
		}
		else
		{
			baskets = (ArrayList<BasketDto>)session.getAttribute("baskets");
		}
		
		int index = baskets.indexOf(basketDto);
		if(index == -1)
		{
			baskets.add(basketDto);
		}
		else
		{
			baskets.get(index).setAmount(baskets.get(index).getAmount()+amount);
		}
		System.out.println(session.getAttribute("baskets"));
		return 1;
	}
	//장바구니 변경
	@PostMapping("/changeBasket")
	@ResponseBody
	public void changeBasket(HttpSession session,@RequestParam("salesDto") SalesDto salesDto, @RequestParam("amount") int amount) throws Exception {
		BasketDto basketDto = new BasketDto();	//basketDto 생성
		basketDto.setSalesDto(salesDto);
		ArrayList<BasketDto> baskets;
		
		baskets = (ArrayList<BasketDto>)session.getAttribute("baskets");
		
		int index = baskets.indexOf(basketDto);
		baskets.get(index).setAmount(amount);
		session.setAttribute("baskets", baskets);
		return;
	}
	
	@GetMapping("/showBasket")
	public ModelAndView showBasket(HttpSession session)
	{
		Object userO = session.getAttribute("userDto");	//세션에 저장된 유저정보 불러옮
		if(userO == null)	//세션에 정보가 없으면
			return new ModelAndView("/login");
		if(session.getAttribute("baskets")==null)	//장바구니 리스트가 세션에 존재하는지 체크
		{
			ArrayList<BasketDto> baskets = new ArrayList<BasketDto>();
			session.setAttribute("baskets", baskets);
		}
		ModelAndView mv = new ModelAndView("/shoppingBasket");
		mv.addObject("baskets", session.getAttribute("baskets"));
		return mv;
	}
	
	@PostMapping("/basketUpdate")
	public String basketUpdate(HttpSession session, SalesDto salesDto) throws Exception
	{
		SalesDto sales = salesService.findProd(salesDto.getSalesNo());
		BasketDto basketDto = new BasketDto(); 
		basketDto.setSalesDto(salesDto);
		
		ArrayList<BasketDto> baskets;
		baskets = (ArrayList<BasketDto>)session.getAttribute("baskets");
		
		baskets.remove(basketDto);
		return "redirect:/showBasket";
	}
}
