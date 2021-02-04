package com.koreait.community.user;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.koreait.community.model.UserEntity;

@Controller
@RequestMapping("/user")
public class UserController {

	@Autowired
	public UserService service;
	
	@GetMapping("/login")
	public void login(Model model) {}

//	@GetMapping("/login")
//	public void login(Model model,@RequestParam(defaultValue="0")int err) {
////		required=false --- 로그인 들어갈때는 에러가 아니니까 
//		switch(err) {
//		case 2: 
//			model.addAttribute("errMsg", "아이디를 확인해 주세요.");
//			break;
//		case 3:
//			model.addAttribute("errMsg", "비밀번호를 확인해 주세요.");
//			break;
//		}
//	}
	// 주소값 체계가 다르다면 retrun 해줘야한다. 아니면 void만 하면됨
	
	@ResponseBody
	@PostMapping("/login")
	public Map<String, Object> login(@RequestBody UserEntity p, HttpSession hs) {
		System.out.println("id : " + p.getUserId());
		System.out.println("pw : " + p.getUserPw());
	
		Map<String, Object> returnValue = new HashMap<>();
		returnValue.put("result",service.login(p, hs));

		return returnValue;
	}

	@ResponseBody
	@GetMapping("/chkId/{userId}")
	public Map<String, Object> chkId(UserEntity p) {
		System.out.println("userId : "+ p.getUserId());
		Map<String, Object> returnValue = new HashMap<>();
		returnValue.put("result",service.chkId(p));//아이디가 있으면:1 없으면: 0
		
		return returnValue;
	}
	
	@GetMapping("/logout")
	public String logout(HttpSession hs) {
		hs.invalidate();
		return "redirect:/user/login";
	}
	
	
	@GetMapping("/join")
	public void join() {
	}

	@ResponseBody
	@PostMapping("/join") //1:성공  2:실패
	public Map<String, Object> join(@RequestBody UserEntity p) {
		
		Map<String, Object> returnValue = new HashMap<>();
		returnValue.put("result", service.join(p));
		
		return returnValue;
	
	}
	
}
