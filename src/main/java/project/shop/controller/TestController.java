package project.shop.controller;

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
import org.springframework.web.bind.annotation.GetMapping;

import project.shop.dto.UserDto;
import project.shop.service.UserService;

@Controller
public class TestController {
	@Autowired
    private UserService userService; //서비스와 연결
	
//	@GetMapping("/test") //노테이션의 값으로 주소 지정
//    public String openBoardList() throws Exception{
//    	//templates 폴더 아래있는 /boardList.html을 의미함. Thymeleaf와 같은 템플릿엔진을 사용할 경우 스프링 부트의 자동 설정 기능으로 '.html'과 같은 접미사 생략 가능
//    	System.out.println("/test");
//    	System.out.println(userService.selectUserList());
//        //게시글 목록을 조회하기 위해 ServiceImpl 클래스의 selectBoardList 메서드 호출
//        return "/test";
//    }
	
	@GetMapping("/test")
	public String test(HttpSession session, UserDto userDto)
	{
		System.out.println(session.getAttribute("session"));
		return "/test/test";
	}
	
	@GetMapping("/insertTest")
	public String insertTest(UserDto userDto)
	{
		
		System.out.println(userDto);
		return "/test/test";
	}
	
	@GetMapping("/mailTest")
	public String mailTest()
	{
		System.out.println("mailtest!");
		String recipient = "ppq23@naver.com";
        String code = "abc";
        
        final String user = "pqp0203@gmail.com";
        final String password = "fdngqxsodqrpvxwu";
        
        Properties prop = new Properties();
        prop.put("mail.smtp.host", "smtp.gmail.com");
        prop.put("mail.smtp.port", 465);
        prop.put("mail.smtp.auth", "true");
        prop.put("mail.smtp.ssl.enable", "true");
        prop.put("mail.smtp.ssl.trust", "smtp.gmail.com");
        
        Session session = Session.getDefaultInstance(prop, new javax.mail.Authenticator() {
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
        
        return "/test/test";
	}

	//css작업을 위한 경로설정
	@GetMapping("/deleteUser.css")
	public String deleteUserCSS() {
		return "/deleteUser";
	}
	
	@GetMapping("/footer.css")
	public String footerCSS() {
		return "/footer";
	}
	
	@GetMapping("/headear.css")
	public String headearCSS() {
		return "/headear";
	}
	
	@GetMapping("/idFind.css")
	public String idFindCSS() {
		return "/idFind";
	}
	
	@GetMapping("/idFindSuccess.css")
	public String idFindSuccessCSS() {
		return "/idFindSuccess";
	}
	
	@GetMapping("/joinUser.css")
	public String joinUserCSS() {
		return "/joinUser";
	}
	
	@GetMapping("/login.css")
	public String loginCSS() {
		return "/login";
	}
	
	@GetMapping("/mainPage.css")
	public String mainPageCSS() {
		return "/mainPage";
	}
	
	@GetMapping("/myPage.css")
	public String myPageCSS() {
		return "/myPage";
	}
	
	@GetMapping("/myPagePwdChange.css")
	public String myPagePwdChangeCSS() {
		return "/myPagePwdChange";
	}
	
	@GetMapping("/passwordChange.css")
	public String passwordChangeCSS() {
		return "/passwordChange";
	}
	
	@GetMapping("/passwordFind.css")
	public String passwordFindCSS() {
		return "/passwordFind";
	}
	
	@GetMapping("/paymentPage.css")
	public String paymentPageCSS() {
		return "/paymentPage";
	}
	
	@GetMapping("/productCate.css")
	public String productCateCSS() {
		return "/productCate";
	}
	
	@GetMapping("/productSearch.css")
	public String productSearchCSS() {
		return "/productSearch";
	}
	
	@GetMapping("/productShow.css")
	public String productShowCSS() {
		return "/productShow";
	}
	
	@GetMapping("/productWrite.css")
	public String productWriteCSS() {
		return "/productWrite";
	}
	
	@GetMapping("/reviewWrite.css")
	public String reviewWriteCSS() {
		return "/reviewWrite";
	}
	
	@GetMapping("/shoppingBasket.css")
	public String shoppingBasketCSS() {
		return "/shoppingBasket";
	}
	
	@GetMapping("/userInfoChange.css")
	public String userInfoChangeCSS() {
		return "/userInfoChange";
	}
}

