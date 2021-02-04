package com.koreait.community.user;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.koreait.community.Const;
import com.koreait.community.SecurityUtils;
import com.koreait.community.model.UserEntity;

@Service
public class UserService {

	@Autowired
	public UserMapper mapper;

	@Autowired
	private SecurityUtils sUtils;

	// 1:회원가입 성공, 0:회원가입실패
	public int join(UserEntity p) {
		if (p.getUserId() == null || p.getUserId().length() < 2) {
			return 0;
		}
		// 비밀번호 암호화
		String salt = sUtils.getSalt();
		String hashPw = sUtils.getHashPw(p.getUserPw(), salt);
		p.setSalt(salt); // 암호화 //얘를 날리면 비밀번호 못찾음
		p.setUserPw(hashPw); // 암호화 된 비밀번호

		return mapper.insUser(p);
	}

	// 1: 로그인 성공 2: 아이디 없음 3:비밀번호가 틀림 0:에러
	public int login(UserEntity p, HttpSession hs) {

		UserEntity dbData = mapper.selUser(p);
		
		if (dbData == null) {
			System.out.println("아이디 없음");
			return 2;
		}
		
		String salt = dbData.getSalt(); // DB에 있는 salt를 가지고오기
		String cryptPw = sUtils.getHashPw(p.getUserPw(), salt);
		// 기존의 userPw값과 가져온 PW값을 비교하기 위해 만듬

		if (!cryptPw.equals(dbData.getUserPw())) { // 그래서 비교
			return 3;
		}
		dbData.setUserPw(null);
		dbData.setSalt(null);
		dbData.setRegDt(null);
		hs.setAttribute(Const.KEY_LOGINUSER, dbData);

		return 1;
	}
	
	
	public int chkId(UserEntity p) {
		
		UserEntity data = mapper.selUser(p);
		
		if(data == null) {
			return 0;
		}
		
		return 1;
	}
}
