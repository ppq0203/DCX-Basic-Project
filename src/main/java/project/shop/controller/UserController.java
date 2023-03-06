package project.shop.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import project.shop.dto.BoardDto;
import project.shop.dto.UserDto;
import project.shop.mapper.ShopMapper;
import project.shop.service.UserService;


@Controller
public class UserController {
	@Autowired
    private UserService userService; //서비스와 연결
	
	//로그인 페이지
	@GetMapping("/login")
	public String loginPage()
	{
		return "/login";
	}
	
//	public ModelAndView openBoardList() throws Exception{
//	//templates 폴더 아래있는 /boardList.html을 의미함. Thymeleaf와 같은 템플릿엔진을 사용할 경우 스프링 부트의 자동 설정 기능으로 '.html'과 같은 접미사 생략 가능
//	System.out.println("/board/openBoardList.do");
//	ModelAndView mv = new ModelAndView("/boardList"); 
//	System.out.println(boardService.select());
//    //게시글 목록을 조회하기 위해 ServiceImpl 클래스의 selectBoardList 메서드 호출
//    List<BoardDto> list = boardService.selectBoardList();  
//    mv.addObject("list", list);
//
//    return mv;
//}
	
	@PostMapping("/postLogin")		//작성된 게시글 등록 기능 메소드, html의 form 태그 action에서 입력한 주소
    public ModelAndView postLogin(HttpSession session, UserDto user) throws Exception{
    	System.out.println("postLogin::"+user);
    	ModelAndView mv = null;
//    	UserDto getUserDto = userService.findUser(user);	//ID 정보로 유저정보 확인
//        System.out.println("post" + getUserDto);	//유저정보 제대로 받아왔는지 확인
//    	if (getUserDto != null && getUserDto.getUserPw() == user.getUserPw())
//    	{
//    		session.setAttribute("userDto", getUserDto);	//세션에 유저정보 저장
//    		mv = new ModelAndView("/main");
//    	}
//    	else
//    	{
//    		mv = new ModelAndView("/login");
//    		mv.addObject("alertOption", 1);
//    		mv.addObject("message", "아이디 비밀번호를 확인하세요");
//    	}
    	
	  //test code	
    	if(user.getUserId().equals("1"))
    	{
    		mv = new ModelAndView("/test");
    		mv.addObject("alertOption", 1);
    		mv.addObject("message", "아이디 비밀번호를 확인하세요");
    	}
    	else
    	{
    		session.setAttribute("session", "session!");
    		mv = new ModelAndView("redirect:/main");
    	}
    	
    	return mv;	//로그인 창으로 이동
    }
	
	//회원가입 컨트롤
	@PostMapping("/postregi") //노테이션의 값으로 주소 지정
    public String insertUser(UserDto user) throws Exception
	{
		//templates 폴더 아래있는 /boardList.html을 의미함. Thymeleaf와 같은 템플릿엔진을 사용할 경우 스프링 부트의 자동 설정 기능으로 '.html'과 같은 접미사 생략 가능
        System.out.println("/regi");
        userService.insertUser(user);
        //게시글 목록을 조회하기 위해 ServiceImpl 클래스의 selectBoardList 메서드 호출

        return "/login";
    }
	
	//비밀번호 변경 컨트롤
	@PutMapping("/postPw")
	public String changePw(UserDto user) throws Exception
	{
		System.out.println("/postPw");
		userService.changePw(user);
		System.out.println("changed");
		return "/login";
	}
	
	//유저 제거 컨트롤
	@DeleteMapping("/postdelete")
	public String deleteUser(UserDto user) throws Exception
	{
		System.out.println("/postdelete");
		userService.deleteUser(user);
		System.out.println("deleted");
		return "/login";
	}
	
	// 아이디 찾기 컨트롤
	@PostMapping("/findId.do")
	public ModelAndView findId(UserDto user) throws Exception
	{
		//templates 폴더 아래있는 /boardList.html을 의미함. Thymeleaf와 같은 템플릿엔진을 사용할 경우 스프링 부트의 자동 설정 기능으로 '.html'과 같은 접미사 생략 가능
		System.out.println("/findId.do");
    	ModelAndView mv = new ModelAndView("/test"); 
    	System.out.println(userService.select());
        //게시글 목록을 조회하기 위해 ServiceImpl 클래스의 selectBoardList 메서드 호출
        List<UserDto> list = userService.selectUserList(user);  
        mv.addObject("list", list);
		System.out.println("found");
		
		return mv;
	}
	
	@GetMapping("/findId.do")		//찾은아이디 결과 화면 호출
    public String findId() throws Exception{
		System.out.println("/found");
    	return "/test";
    }
}
