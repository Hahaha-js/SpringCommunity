package com.koreait.community;

import javax.servlet.http.HttpSession;

import org.mindrot.jbcrypt.BCrypt;
import org.springframework.stereotype.Component;

import com.koreait.community.model.UserEntity;

@Component
public class SecurityUtils {
	
	//로그인한 유저의 pk 값을 가지오는 메소드 , 로그인 하지 않았으면 값이 담겨있지 않는다
	public int getLoginUserPk(HttpSession hs) {
		UserEntity loginUser = getLoginUser(hs);
		return loginUser == null ? -1 :loginUser.getUserPk();
	}
	
	//로그인한 유저, 단순히 로그인 했냐 안했냐만 판단하기 위한 메소드
	public UserEntity getLoginUser(HttpSession hs) {
		return (UserEntity)hs.getAttribute(Const.KEY_LOGINUSER);
	}


	public String getSalt() {
		return BCrypt.gensalt();
	}

	public String getHashPw(String pw, String salt) {
		return BCrypt.hashpw(pw, salt);
	}


}
