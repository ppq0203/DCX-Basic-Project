package project.shop.controller;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import project.shop.dto.OrderDto;
import project.shop.dto.SalesDto;
import project.shop.dto.UserDto;
import project.shop.service.SalesService;


@Controller
public class SalesController {
	@Autowired
    private SalesService salesService; //서비스와 연결
	
	//상품등록 페이지
	@GetMapping("/productWrite")
	public String addprodPage(@ModelAttribute SalesDto sales, Model model, HttpSession session)
	{
		Object userO = session.getAttribute("userDto");	//세션에 저장된 유저정보 불러옮
		if(userO == null)
		{
			return "/login";
		}
		model.addAttribute("Sales", sales);
		return "/productWrite";
	}
	
	//판매정보 호출 기반
	@PostMapping("/showprod")
	public ModelAndView prodPage(@RequestParam("salesNo") int num) throws Exception
	{
		System.out.println("/showprod");
    	ModelAndView mv = new ModelAndView("/productShow");
        SalesDto sales = salesService.findProd(num);
        System.out.println(" [+] " + sales + num);
        
        mv.addObject("sales", sales);
		System.out.println(mv);
		
		return mv;
	}
	
	//판매정보 호출 기반
		@GetMapping("/listprod")
		public ModelAndView prodlist(SalesDto sales) throws Exception
		{
			System.out.println("/listprod");
	    	ModelAndView mv = new ModelAndView("test/dbtest");
	        List<SalesDto> list = salesService.selectProdList(sales);
	        
	        mv.addObject("list", list);
			System.out.println(mv);
			
			return mv;
		}
	
	//판매정보 및 상품등록
	@PostMapping("/productWrite.do")
	public String insertProduct(@ModelAttribute SalesDto sales, HttpSession session) throws Exception
	{
		Object userO = session.getAttribute("userDto");	//세션에 저장된 유저정보 불러옮
		if(userO == null)
		{
			return "/login";
		}
		String imageFileName = "";
		for(MultipartFile file : sales.getImageFile())
		{
			imageFileName = imageFileName + file.getOriginalFilename() + "$%$";
			String path = "";//파일이 저장될 디렉토리 url
			
			Path imagePath = Paths.get(path + file.getOriginalFilename());
			
			try {
				System.out.println("try");
				Files.write(imagePath, file.getBytes());
				System.out.println("tried");
			} catch(Exception e) {
				
			}
		}
		sales.setSalesImg(imageFileName);
		
		int userNo = (int) ((UserDto) userO).getUserNo();
		sales.setUserNo(userNo);
		
		System.out.println(" [+] post :: "+sales);
		salesService.insertProduct(sales);
		
		return "redirect:/main";
	}
	
	@PostMapping("/orderdate")
	public String insertOrder(OrderDto order)
	{
		salesService.insertOrder(order);
		System.out.println("date inputed");
		return "redirect:/main";
	}
}
