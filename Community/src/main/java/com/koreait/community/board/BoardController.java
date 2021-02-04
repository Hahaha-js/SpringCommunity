package com.koreait.community.board;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.koreait.community.Const;
import com.koreait.community.SecurityUtils;
import com.koreait.community.model.BoardDTO;
import com.koreait.community.model.BoardEntity;

@Controller
@RequestMapping("/board")
public class BoardController {

	@Autowired
	private BoardService service;
	
	@Autowired
	private SecurityUtils sUtils;
	
	@GetMapping("/home")
	public void home() {
		
	}
	
	@GetMapping("/list")
	public void list(BoardDTO p, Model model) {
		model.addAttribute(Const.KEY_LIST,service.selBoardList(p));
		
		//보내주는 친구
		//session model 빼고는 값을 클라이언트값은 받기 위한 값(BoardDTO p, BoardEntity p 등등)
		//ajax 겟방식은 쿼리스트링에서 가져오는 것 이기때문에 @Reqeustbody 안적어도 됨
	}
	
	//write 
	@GetMapping("/write") // 화면 뿌리는 용도
	public String write() {
		return "board/writeEdit";
	}
	
	
	
	@PostMapping("/write")
	public String write(BoardEntity p, HttpSession hs) {
		p.setUserPk(sUtils.getLoginUserPk(hs)); // PK 값이 들어가 있음
		int result = service.insBoard(p); //ajax 는 결과값만 리턴해주면 된다
		return "redirect:/board/detail?boardPk=" + p.getBoardPk();
	}
	
	@GetMapping("/detail")
	public void detail(BoardDTO p, Model model, HttpSession hs) {
		model.addAttribute(Const.KEY_DATA, service.selBoard(p, hs));
		//select값을 넣어줘야 하기때문에 DTO를 사용 
		
	}
	
	
	
	
}
