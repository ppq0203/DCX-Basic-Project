package project.shop.controller;

import java.util.List;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import project.shop.dto.UserDto;
import project.shop.function.CodeGeneration;
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
	
	@PostMapping("/sendConfirm")
	@ResponseBody
	public String sendConfirm(@RequestParam("mail") String mail) throws Exception {
		System.out.println("sendConfirm!");
		String recipient = mail;
        String code = CodeGeneration.createKey();
        
        final String user = "pqp0203@gmail.com";
        final String password = "fdngqxsodqrpvxwu";
        
        Properties prop = new Properties();
        prop.put("mail.smtp.host", "smtp.gmail.com");
        prop.put("mail.smtp.port", 465);
        prop.put("mail.smtp.auth", "true");
        prop.put("mail.smtp.ssl.enable", "true");
        prop.put("mail.smtp.ssl.trust", "smtp.gmail.com");
        
        Session session = Session.getInstance(prop, new javax.mail.Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(user, password);
            }
        });
        
        MimeMessage message = new MimeMessage(session);
        try {
            message.setFrom(new InternetAddress(user));
 
            // 수신자 메일 주소
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(recipient));
 
            // Subject
            message.setSubject("PLAYDDIT verification code");
 
            // Text
            message.setText("Welcome to playddit. your code is ["+code+"]");
 
            Transport.send(message);    // send message
 
 
        } catch (AddressException e) {
            e.printStackTrace();
        } catch (MessagingException e) {
            e.printStackTrace();
        }
        
        return code;
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
	
	//유저정보 변경 컨트롤
		@PutMapping("/postUser")
		public String changeUser(UserDto user) throws Exception
		{
			System.out.println("/postPw");
			userService.changeUser(user);
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
	
	//아이디 찾기 페이지
	@GetMapping("/idFind")
	public String idFind() throws Exception
	{
		System.out.println("/idFind");
		return "/idFind";
	}
	
	//비밀번호 찾기 페이지
	@GetMapping("/passwordFind")
	public String passwordFind() throws Exception
	{
		System.out.println("/passwordFind");
		return "/passwordFind";
	}
	
	// 아이디 찾기 컨트롤
	@PostMapping("/findId.do")
	public ModelAndView findId(UserDto user) throws Exception
	{
		System.out.println("/findId.do");
    	ModelAndView mv = new ModelAndView("/test"); 
    	System.out.println(userService.select());
        List<UserDto> list = userService.selectUserList(user);  
        mv.addObject("list", list);
		System.out.println("found");
		
		return mv;
	}
	
	//유저 정보 변
	
	
	
}
