package project.shop.controller;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import project.shop.dto.OrderDto;
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
		return "/productWrite";
	}
	
	//판매정보 호출 기반
	@GetMapping("/showprod")
	public ModelAndView prodPage(SalesDto sales) throws Exception
	{
		System.out.println("/showprod");
    	ModelAndView mv = new ModelAndView("/productShow");
        List<SalesDto> list = salesService.selectProdList(sales);  
        mv.addObject("list", list);
		System.out.println("found");
		
		return mv;
	}
	
	//판매정보 및 상품등록
	@PostMapping("/addprod.do")
	public String insertProduct(SalesDto sales) throws Exception
	{
		System.out.println(" [+] post "+sales);
		salesService.insertProduct(sales);
		return "redirect:/showprod";
	}
	
	//파일 업로드 테스트
	@PostMapping("/upload")
	public String upload(@RequestParam("files") List<MultipartFile> files) throws Exception
	{
		for(MultipartFile file : files)
		{
			String imageFileName = file.getOriginalFilename();
			System.out.println("done :: "+imageFileName);
			String path = "";//파일이 저장될 디렉토리 url
			
			Path imagePath = Paths.get(path + imageFileName);
			
			try {
				System.out.println("try");
				Files.write(imagePath, file.getBytes());
				System.out.println("tried");
			} catch(Exception e) {
				
			}
		}
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
