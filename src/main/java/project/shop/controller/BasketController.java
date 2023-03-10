package project.shop.controller;

import java.util.ArrayList;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import project.shop.dto.BasketDto;

@Controller
public class BasketController {
	@PostMapping("/addBasket")
	@ResponseBody
	public void addbasket(HttpSession session,@RequestParam("salesNo") int salesNo, @RequestParam("amount") int amount) throws Exception {
		BasketDto basketDto = new BasketDto();	//basketDto 생성
		basketDto.setSalesNo(salesNo);
		basketDto.setAmount(amount);
		ArrayList<BasketDto> baskets;
		
		if(session.getAttribute("baskets")==null)	//장바구니 리스트가 세션에 존재하는지 체크
		{
			baskets = new ArrayList<BasketDto>();
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
		session.setAttribute("baskets", baskets);
		return;
	}
	
	@PostMapping("/changeBasket")
	@ResponseBody
	public void changeBasket(HttpSession session,@RequestParam("salesNo") int salesNo, @RequestParam("amount") int amount) throws Exception {
		BasketDto basketDto = new BasketDto();	//basketDto 생성
		basketDto.setSalesNo(salesNo);
		ArrayList<BasketDto> baskets;
		
		baskets = (ArrayList<BasketDto>)session.getAttribute("baskets");
		
		int index = baskets.indexOf(basketDto);
		baskets.get(index).setAmount(amount);
		session.setAttribute("baskets", baskets);
		return;
	}
}
