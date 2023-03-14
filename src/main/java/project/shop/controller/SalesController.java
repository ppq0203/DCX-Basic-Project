package project.shop.controller;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

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

import project.shop.dto.BasketDto;
import project.shop.dto.OrderDto;
import project.shop.dto.ReviewDto;
import project.shop.dto.SalesDto;
import project.shop.dto.UserDto;
import project.shop.service.SalesService;
import project.shop.service.UserService;


@Controller
public class SalesController {
	@Autowired
    private SalesService salesService; //서비스와 연결
	@Autowired
	private UserService userService;
	
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
				String path = System.getProperty("user.dir")+"/src/main/resources/static/files/";//파일이 저장될 디렉토리 url
				System.out.println(path);
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
	
	//판매정보 호출 기반
	@GetMapping("/showprod")
	public ModelAndView prodPage(UserDto user, @RequestParam("salesNo") int num) throws Exception
	{
		System.out.println("/showprod");
    	ModelAndView mv = new ModelAndView("/productShow");
        SalesDto sales = salesService.findProd(num);
        List<ReviewDto> list = salesService.showReview(num); 
        List<UserDto> users = new ArrayList();
        for(int i = 0; i < list.size(); i++)
        {
        	int userNo = list.get(i).getUserNo();
        	System.out.println(userNo);
        	user.setUserNo(userNo);
        	UserDto getUserDto = userService.findUser(user);
        	users.add(getUserDto);
        }
        System.out.println(" [+] " + sales + list);
        System.out.println(mv);
        
        mv.addObject("sales", sales);
        mv.addObject("list", list);
        mv.addObject("users", users);
        
		System.out.println(mv);
		
		return mv;
	}
	
	//판매정보 호출 기반
	@GetMapping("/cateprod")
	public ModelAndView catelist(@RequestParam("productCategory") String cate) throws Exception
	{
		System.out.println("/cateprod");
    	ModelAndView mv = new ModelAndView("/productCate");
        
    	List<SalesDto> list = salesService.selectCateList(cate);
        
        mv.addObject("list", list);
		System.out.println(mv);
		
		return mv;
	}
	
	
	@GetMapping("/orderdate")
	public String insertOrder(OrderDto order, HttpSession session)
	{
		ArrayList<BasketDto> basket = (ArrayList<BasketDto>) session.getAttribute("baskets");
		Object userO = session.getAttribute("userDto");
		
		order.setUserNo(((UserDto) userO).getUserNo());
		
		String uuid = UUID.randomUUID().toString();
		String[] test = uuid.split("-");
		String orderNo = "";
		for(int i = 0; i < 3; i++)
		{
			orderNo = orderNo + test[i];
		}
		
		order.setOrderNo(orderNo);
		
		for(int i = 0; i < basket.size(); i++)
		{
			order.setSalesCount(basket.get(i).getAmount());
			order.setSalesNo(basket.get(i).getSalesDto().getSalesNo());
			salesService.insertOrder(order);
		}
		System.out.println("date inputed");
		return "redirect:/main";
	}
	
	//판매정보 호출 기반
	@GetMapping("/myOrder")
	public ModelAndView orderlist(HttpSession session) throws Exception
	{
		Object user = session.getAttribute("userDto");
		int userNo = ((UserDto) user).getUserNo();
		System.out.println("/myOrder");
		System.out.println("path :: " + System.getProperty("user.dir")+"/src/main/resources/static/files");
    	ModelAndView mv = new ModelAndView("myOrder");
        List<Object> list = salesService.getOrder(userNo);
        
        mv.addObject("list", list);
		System.out.println(mv);
		
		return mv;
	}
	
	//검색기능
	@GetMapping("/searchP")
	public ModelAndView searchProd(@RequestParam("keyword") String sProd)
	{
		ModelAndView mv = new ModelAndView("/searchResult");
		System.out.println(sProd);
		List<SalesDto> list = salesService.searchProd(sProd);
		
		mv.addObject("list", list);
		System.out.println(mv);
		
		return mv;
	}
	
	//등록 상품 조회
	@GetMapping("/myProd")
	public ModelAndView myProd(HttpSession session)
	{
		Object user = session.getAttribute("userDto");
		ModelAndView mv = new ModelAndView("/myProd");
		System.out.println(((UserDto) user).getUserNo());
		List<SalesDto> list = salesService.myProd(((UserDto) user).getUserNo());
		
		mv.addObject("list", list);
		System.out.println(mv);
		
		return mv;
	}
	
	//리뷰 페이지
	@GetMapping("/reviewWrite")
	public String reviewWritepage(@RequestParam("No") int salesNo)
	{	
		return "writeReview";
	}
	
	@PostMapping("/reviewWrite.do")
	public String reviewWrite(ReviewDto review, @RequestParam("No") int salesNo, HttpSession session)
	{
		Object user = session.getAttribute("userDto");
		review.setUserNo(((UserDto) user).getUserNo());
		review.setSalesNo(salesNo);
		System.out.println("tttset :: " + review);
		salesService.insertReview(review);
		
		return "redirect:/main";
	}
	
	@GetMapping("/reviewShow")
	public ModelAndView reviewShow(@RequestParam("No") int salesNo)
	{
		ModelAndView mv = new ModelAndView("showReview");
		List<ReviewDto> list = salesService.showReview(salesNo);
		System.out.println(list);
		mv.addObject("list", list);
		System.out.println(mv);
		
		return mv;
	}
	
}
