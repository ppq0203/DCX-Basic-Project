package project.shop.controller;


import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import project.shop.dto.BasketDto;
import project.shop.dto.OrderDto;
import project.shop.dto.SalesDto;
import project.shop.dto.UserDto;
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
	
	
	@PostMapping("goPaymentPage")
	public ModelAndView goPaymentPage(HttpSession session, int salesNo) throws Exception
	{
		System.out.println("paymentPage");
		ModelAndView mv = new ModelAndView("paymentPage");
		ArrayList<BasketDto> baskets;
		if (salesNo > 0)	//즉시구매시 상품번호전달
		{
			SalesDto salesDto = salesService.findProd(salesNo);
			BasketDto basketDto;
			basketDto = new BasketDto();
			basketDto.setSalesDto(salesDto);	//즉시구매 데이터 전송
			basketDto.setAmount(1);
			System.out.println(basketDto);
			baskets = new ArrayList<BasketDto>();
			baskets.add(basketDto);
		}
		else	//장바구니 구매시 -1 전달
		{
			System.out.println(session.getAttribute("baskets"));
			if(session.getAttribute("baskets") == null || ((ArrayList<BasketDto>)session.getAttribute("baskets")).size() <= 0 )
				return new ModelAndView("redirect:/showBasket");
			
			baskets = (ArrayList<BasketDto>)session.getAttribute("baskets");	//장바구니 데이터 전송
		}
		System.out.println(baskets);
		mv.addObject("baskets", baskets);
		mv.addObject("userDto", session.getAttribute("userDto"));
		return mv;
	}
	
	@PostMapping("postPaymentPage")
	public String postPaymentPage(HttpSession session,int salesNo, int amount) throws Exception
	{
		System.out.println("postPaymentPage");
		UserDto user = (UserDto)session.getAttribute("userDto");
		// 중복없는 orderNo 생성
		String uuid = UUID.randomUUID().toString();
		String[] test = uuid.split("-");
		String orderNo = "";
		for(int i = 0; i < 3; i++)
		{
			orderNo = orderNo + test[i];
		}
		
		if (salesNo > 0)	//즉시구매시 상품번호전달
		{
			OrderDto orderDto = new OrderDto();
			orderDto.setUserNo(user.getUserNo());
			orderDto.setSalesNo(salesNo);
			orderDto.setOrderNo(orderNo);
			orderDto.setSalesCount(amount);
			System.out.println(orderDto);
//			orderDto.getAmount(amount);	//수량 저장
			salesService.insertOrder(orderDto);	// 주문정보 저장
		}
		else	//장바구니 구매시 -1 전달
		{
			ArrayList<BasketDto> baskets = (ArrayList<BasketDto>)session.getAttribute("baskets");
			for (BasketDto basket : baskets)
			{
				OrderDto orderDto = new OrderDto();
				orderDto.setUserNo(user.getUserNo());
				orderDto.setSalesNo(basket.getSalesDto().getSalesNo());
				orderDto.setOrderNo(orderNo);
				orderDto.setSalesCount(basket.getAmount());
				System.out.println(orderDto);
//				orderDto.getAmount(basket.getAmount);	//수량 저장
				salesService.insertOrder(orderDto);	// 주문정보 저장
			}
		}
		session.removeAttribute("baskets");	//결제완료시 장바구니 내역 제거
		return "redirect:/myPage";
	}
}
