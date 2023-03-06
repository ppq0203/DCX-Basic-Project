package project.shop.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import project.shop.dto.BoardDto;
import project.shop.service.BoardService;

@Controller // 컨트롤러라고 선언함
public class BoardController {

    @Autowired
    private BoardService boardService; //서비스와 연결

    @GetMapping("/board/openBoardList.do") //노테이션의 값으로 주소 지정
    public ModelAndView openBoardList() throws Exception{
    	//templates 폴더 아래있는 /boardList.html을 의미함. Thymeleaf와 같은 템플릿엔진을 사용할 경우 스프링 부트의 자동 설정 기능으로 '.html'과 같은 접미사 생략 가능
    	System.out.println("/board/openBoardList.do");
    	ModelAndView mv = new ModelAndView("/boardList"); 
    	System.out.println(boardService.select());
        //게시글 목록을 조회하기 위해 ServiceImpl 클래스의 selectBoardList 메서드 호출
        List<BoardDto> list = boardService.selectBoardList();  
        mv.addObject("list", list);

        return mv;
    }
    
    @GetMapping("/board/openBoardWrite.do")		//게시글 작성 화면 호출
    public String openBoardWrite() throws Exception{
    	return "/boardWrite";
    }
    
    @PostMapping("/board/insertBoard.do")		//작성된 게시글 등록 기능 메소드, html의 form 태그 action에서 입력한 주소
    public String insertBoard( BoardDto board) throws Exception{
    	System.out.println("insertBoard::"+board);
    	boardService.insertBoard(board);
    	return "redirect:/board/openBoardList.do";	//게시글 리스트로 이동
    }
}