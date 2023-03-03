package project.shop.controller;


import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import project.shop.dto.UserDto;
import project.shop.service.UserService;


@Controller
public class UserController {
	@Autowired
    private UserService userService; //서비스와 연결
	
//	@GetMapping("/test") //노테이션의 값으로 주소 지정
//    public String openBoardList() throws Exception{
//    	//templates 폴더 아래있는 /boardList.html을 의미함. Thymeleaf와 같은 템플릿엔진을 사용할 경우 스프링 부트의 자동 설정 기능으로 '.html'과 같은 접미사 생략 가능
//    	System.out.println("/test");
//    	System.out.println(userService.selectUserList());
//        //게시글 목록을 조회하기 위해 ServiceImpl 클래스의 selectBoardList 메서드 호출
//        return "/boardWrite";
//    }
	
	@GetMapping("/login")
	public String loginPage()
	{
		return "/login.html";
	}
	
	@PostMapping("/postLogin")		//작성된 게시글 등록 기능 메소드, html의 form 태그 action에서 입력한 주소
    public String postLogin(HttpSession session, UserDto userDto) throws Exception{
    	System.out.println("postLogin::"+userDto);
    	UserDto getUserDto = userService.selectUser(userDto);	//ID 정보로 유저정보 확인
    	System.out.println("post" + getUserDto);	//유저정보 제대로 받아왔는지 확인
    	if (getUserDto != null && getUserDto == userDto)
    	{
    		session.setAttribute("userDto", getUserDto);	//세션에 유저정보 저장
    		return "/";	//메인으로 이동
    	}
    	
    	return "redirect:/login";	//로그인 창으로 이동
    }
	
	@GetMapping("/join")
	public String joinPage()
	{
		return "/joinUser.html";
	}
	
	@PostMapping("/postJoin")
	public String postJoin(UserDto userDto)
	{
		return "redirect:/login";
	}
	
	@GetMapping("/idFind")
	public String idFindPage()
	{
		return "/idFind.html";
	}
	
	@GetMapping("/pwdFind")
	public String pwdFindPage()
	{
		return "/passworldFind.html";
	}
}
