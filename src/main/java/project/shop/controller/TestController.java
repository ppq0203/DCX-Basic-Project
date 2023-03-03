package project.shop.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import project.shop.dto.UserDto;
import project.shop.service.UserService;

@Controller
public class TestController {
	@Autowired
    private UserService userService; //서비스와 연결
	
	@GetMapping("/test") //노테이션의 값으로 주소 지정
    public String openBoardList() throws Exception{
    	//templates 폴더 아래있는 /boardList.html을 의미함. Thymeleaf와 같은 템플릿엔진을 사용할 경우 스프링 부트의 자동 설정 기능으로 '.html'과 같은 접미사 생략 가능
    	System.out.println("/test");
    	System.out.println(userService.selectUserList());
        //게시글 목록을 조회하기 위해 ServiceImpl 클래스의 selectBoardList 메서드 호출
        return "/test";
    }
	
	@GetMapping("/insertTest")
	public String insertTest(UserDto userDto)
	{
		
		System.out.println(userDto);
		return "/test";
	}
}
