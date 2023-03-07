package project.shop.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import project.shop.dto.UserDto;
import project.shop.function.CodeGeneration;
import project.shop.function.SendMail;
import project.shop.service.UserService;


@Controller
public class UserController {
	@Autowired
    private UserService userService; //서비스와 연결
	
	//로그인 페이지
	@GetMapping("/login")
	public String loginPage(HttpSession session)
	{
		System.out.println(session.getAttribute("userDto"));	//로그인 세션이 남아있는지 확인
		return "/login";
	}
	//로그인 입력시 확인
	@PostMapping("/postLogin")		//작성된 게시글 등록 기능 메소드, html의 form 태그 action에서 입력한 주소
    public String postLogin(HttpSession session, UserDto user) throws Exception{
//    	System.out.println("postLogin::"+user);
    	String src = null;
    	UserDto getUserDto = userService.findUser(user);	//ID 정보로 유저정보 확인
//        System.out.println("post" + getUserDto);	//유저정보 제대로 받아왔는지 확인
    	if (getUserDto != null && user.getUserPw().equals(getUserDto.getUserPw()))	//해당id정보있는지 확인후 비밀번호 비교
    	{
    		session.setAttribute("userDto", getUserDto);	//세션에 유저정보 저장
    		src = "redirect:/main"; 		//메인 창으로 이동
    	}
    	else
    	{
    		src = "redirect:/login.fail"; 	//로그인 창으로 이동
    	}
//    	System.out.println(session.getAttribute("userDto"));	//세션에 저장되었는지 확인
    	return src;
    }
	//로그인 실패시 나올 페이지
	@GetMapping("/login.fail")	// 아이디 비밀번호를 잘못 입력했을때.
	public ModelAndView loginFail()
	{
		ModelAndView mv = new ModelAndView("/login");	//login.html을 view로 이용
		mv.addObject("alertOption", 1);	//	html에서 javascript를 이용해 알람을 해주기 위해 전달
		mv.addObject("message", "아이디 비밀번호를 확인하세요");	// 알람에 작성할 메시지
		return mv;
	}
	
	//회원가입 페이지
	@GetMapping("/joinUser")
	public String regiPage()
	{
		return "/joinUser";
	}
	//회원가입 컨트롤
	@PostMapping("/postregi") //노테이션의 값으로 주소 지정
    public String insertUser(UserDto user) throws Exception
	{
        System.out.println("/regi");
        System.out.println(user);
        
        userService.insertUser(user);
        //게시글 목록을 조회하기 위해 ServiceImpl 클래스의 selectBoardList 메서드 호출

        return "/login";
    }	
	//아이디 중복체크
	@PostMapping("/idCheck")
	@ResponseBody
	public int idCheck(@RequestParam("id") String id) throws Exception {
		UserDto user = new UserDto();	//userDto 생성
		user.setUserId(id);	//userDto의 id에 id값저장
		UserDto getUserDto = userService.findUser(user);
		int cnt = 0;	//존재하는 id가 없으면 0 return
		if(getUserDto != null)	//존재하는 id가 있으면 1 return
			cnt = 1;
		return cnt;
	}
	//이메일 중복체크
	@PostMapping("/mailCheck")
	@ResponseBody
	public int mailCheck(@RequestParam("mail") String mail) throws Exception {
		System.out.println(mail);
		UserDto user = new UserDto();	//userDto 생성
		user.setUserEmail(mail);	//userDto의 email에 mail값저장
		UserDto getUserDto = userService.findUser(user);
		System.out.println("user : " + user);
		System.out.println("get user : " + getUserDto);
		
		int cnt = 0;	//존재하는 mail가 없으면 0 return
		if(getUserDto != null)	//존재하는 mail이 있으면 1 return
			cnt = 1;
		return cnt;
	}
	//이메일 전송
	@PostMapping("/sendConfirm")
	@ResponseBody
	public String sendConfirm(@RequestParam("mail") String mail) throws Exception {
		System.out.println("sendConfirm!");
        String code = CodeGeneration.createKey();
		SendMail.sendMessage(mail, code);
        return code;
	}
		
	//아이디 찾기 페이지
	@GetMapping("/idFind")
	public String idFind() throws Exception
	{
		System.out.println("/idFind");
		return "/idFind";
	}
	//이메일 인증후 아이디 찾기 진행시
	@PostMapping("/postIdFind")
	public ModelAndView postIdFind(UserDto user) throws Exception
	{
		ModelAndView mv;
		UserDto getUserDto = userService.findUser(user);	//해당 이메일로 존재하는 아이디 정보 받기
		if(getUserDto == null)	//해당 이메일로 존재하는 아이디가 없으면
		{
			mv = new ModelAndView("redirect:/idFind.fail");	//아이디찾기 실패
		}
		else
		{
			mv = new ModelAndView("/idFindSuccess");	//아이디찾기 성공
			mv.addObject("ID", getUserDto.getUserId());	//아이디정보 전달
		}
		return mv;
	}
	//아이디 찾기 실패시
	@GetMapping("/idFind.fail")	// 해당이메일로 존재하는 아이디가 없을 때
	public ModelAndView idFindFail()
	{
		ModelAndView mv = new ModelAndView("/idFind");	//idFind.html을 view로 이용
		mv.addObject("alertOption", 1);	//	html에서 javascript를 이용해 알람을 해주기 위해 전달
		mv.addObject("message", "해당 이메일로 존재하는 아이디가 없습니다");	// 알람에 작성할 메시지
		return mv;
	}
	
	//비밀번호 찾기 페이지
	@GetMapping("/passwordFind")
	public String passwordFind() throws Exception
	{
		System.out.println("/passwordFind");
		return "/passwordFind";
	}
	//비밀번호 찾기 전송
	@PostMapping("/postPwFind")
	public String postPwFind(HttpSession session ,UserDto user) throws Exception
	{
		System.out.println("/postPwFind");
		String src;
		UserDto getUserDto = userService.findUser(user);	//해당 아이디로 존재하는 유저 정보 받기
		System.out.println(user);
		System.out.println(getUserDto);
		if(getUserDto == null 
				|| !( 
						user.getUserId().equals(getUserDto.getUserId())
						&& user.getUserEmail().equals(getUserDto.getUserEmail()) 
					) 
			)	//검색한 아이디와 입력한 정보가 다르면
		{
			src = "redirect:/passwordFind.fail";	//비밀번호찾기 실패
		}
		else
		{
			session.setAttribute("userNo", getUserDto.getUserNo());	//세션에 유저넘버 저장
			src= "/passwordChange";	//비밀번호 변경페이지 이동
		}
		return src;
	}
	//비밀번호 찾기 실패시
	@GetMapping("/passwordFind.fail")	// 해당이메일로 존재하는 아이디가 없을 때
	public ModelAndView pwFindFail()
	{
		ModelAndView mv = new ModelAndView("/passwordFind");	//idFind.html을 view로 이용
		mv.addObject("alertOption", 1);	//	html에서 javascript를 이용해 알람을 해주기 위해 전달
		mv.addObject("message", "해당 정보에 해당하는 유저정보가 없습니다.");	// 알람에 작성할 메시지
		return mv;
	}
	//비밀번호 변경 컨트롤
	@PostMapping("/pwChange.control")
	public String PwChange(HttpSession session, UserDto user) throws Exception
	{
		System.out.println("/postPw");
		user.setUserNo((int)session.getAttribute("userNo"));
		System.out.println(user);
//		userService.changePw(user);
		System.out.println("changed");
		session.removeAttribute("userNo");	//세션에 저장된 유저넘버 삭제
		
		Object userO = session.getAttribute("userDto");
		if(userO != null)
			return "redirect:/myPage";
		System.out.println("bug?");
		return "redirect:/login";
	}
	//비밀번호 변경 페이지
	@GetMapping("/myPagePwdChange")
	public String myPagePwdChange(HttpSession session)
	{
		Object userO = session.getAttribute("userDto");
		if(userO == null)
			return "/login";
		
		return "/myPagePwdChange";
	}
	//비밀번호 변경페이지 비밀번호 입력시
	@PostMapping("/myPagePwdChange")
	public ModelAndView PostMyPagePwdChange(HttpSession session, UserDto userDto)
	{
		ModelAndView mv;
		Object userO = session.getAttribute("userDto");
		if(userO == null)
			return new ModelAndView("redirect:/login");
		UserDto user = (UserDto)userO;
		if(user.getUserPw().equals(userDto.getUserPw()))
		{
			session.setAttribute("userNo", user.getUserNo());
			mv = new ModelAndView("/passwordChange");	//비밀번호 변경페이지 이동
		}
		else
		{
			mv = new ModelAndView("/myPagePwdChange");
			mv.addObject("alertOption", 1);	//	html에서 javascript를 이용해 알람을 해주기 위해 전달
			mv.addObject("message", "비밀번호가 잘못되었습니다.");	// 알람에 작성할 메시지
			
		}
		return mv;
	}

	//유저 제거 컨트롤
	@GetMapping("/myPage")
	public String myPage(HttpSession session) throws Exception
	{
		System.out.println("myPage");
		Object userO = session.getAttribute("userDto");	//세션에 저장된 유저정보 불러옮
		String src = "";
		if(userO == null)	//세션에 정보가 없으면
			return "/login";	//로그인 페이지로 이동

		src = "/myPage";		//마이 페이지로 이동
		return src;
	}
	//유저 정보 변경 페이지
	@GetMapping("/userInfoChange")
	public ModelAndView userInfoChange(HttpSession session) throws Exception
	{
		ModelAndView mv;
		System.out.println("/userInfoChange");
		Object user = session.getAttribute("userDto");	//세션에 저장된 유저정보 불러옮
		if(user == null)	//세션에 정보가 없으면
			return new ModelAndView("/login");			//로그인 페이지로 이동
	
		mv = new ModelAndView("/userInfoChange");	//마이페지로 이동
		mv.addObject("phone", ((UserDto)user).getUserPhone() );
		mv.addObject("address", ((UserDto)user).getUserAddress() );

		System.out.println(user);
		return mv;
	}
	//유저 정보 변경 전송
	@PostMapping("/userInfoChange")
	public String PostUserInfoChange(HttpSession session, UserDto userDto) throws Exception
	{
		System.out.println("post/userInfoChange");
		Object userO = session.getAttribute("userDto");	//세션에 저장된 유저정보 불러옮
		if(userO == null)	//세션에 정보가 없으면
			return "redirect:/login";		//로그인 페이지로 이동
		
		UserDto user = (UserDto)userO;
		System.out.println(userDto);
		System.out.println(user);
		user.setUserPhone(userDto.getUserPhone());
		user.setUserAddress(userDto.getUserAddress());
		System.out.println(user);
//		userService.changeUser(user);	//유저정보 갱신
		return "redirect:/myPage";
	}
	
	//회원 탈퇴 페이지
	@GetMapping("/deleteUser")
	public String deleteUser(HttpSession session) throws Exception
	{
		System.out.println("/deleteUser");
		String src = "";
		Object userO = session.getAttribute("userDto");	//세션에 저장된 유저정보 불러옮
		if(userO == null)	//세션에 정보가 없으면
			return "/login";			//로그인 페이지로 이동

		src = "/deleteUser";	//회원탈퇴 페이지로 이동
		return src;
	}
	//회원 탈퇴 전송
	@PostMapping("/deleteUser")
	public ModelAndView postDeleteUser(HttpSession session, UserDto userDto) throws Exception
	{
		ModelAndView mv;
		System.out.println("/postDeleteUser");
		Object user = session.getAttribute("userDto");	//세션에 저장된 유저정보 불러옮
		if(user == null)	//세션에 정보가 없으면
			return new ModelAndView("redirect:/login");			//로그인 페이지로 이동
		
		if(((UserDto)user).getUserPw().equals(userDto.getUserPw()))	//입력된 비밀번호가 회원정보와 일치하면
		{
			System.out.println("delete");
			System.out.println(user);
//			userService.deleteUser((UserDto)user);	//회원정보 제거
			mv = new ModelAndView("redirect:/main");	//
		}
		else
		{
			System.out.println("deleteFail");
			mv = new ModelAndView("/deleteUser");
			mv.addObject("alertOption", 1);	//	html에서 javascript를 이용해 알람을 해주기 위해 전달
			mv.addObject("message", "잘못된 비밀번호 입니다.");	// 알람에 작성할 메시지
		}
		return mv;
	}
/*
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
   */
}
