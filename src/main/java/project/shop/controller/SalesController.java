package project.shop.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import project.shop.dto.SalesDto;
import project.shop.service.SalesService;


@Controller
public class SalesController {
	@Autowired
    private SalesService salesService; //서비스와 연결
	
	//상품등록 페이지
	@GetMapping("/addprod")
	public String addprodPage(@ModelAttribute SalesDto sales, Model model)
	{
		model.addAttribute("Sales", sales);
		return "/dbtest2";
	}
		
	@GetMapping("/showprod")
	public ModelAndView prodPage(SalesDto sales) throws Exception
	{
		System.out.println("/showprod");
    	ModelAndView mv = new ModelAndView("/dbtest");
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
		return "redirect:/showprod";
	}
}
