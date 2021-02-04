package com.koreait.community;

import org.apache.tiles.Attribute;
import org.apache.tiles.AttributeContext;
import org.apache.tiles.preparer.ViewPreparer;
import org.apache.tiles.request.Request;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component("MenuPreparer")
public class MenuPreparer implements ViewPreparer {
	
	
	@Autowired
	private CommonMapper mapper;
	
	@Override
	public void execute(Request tilesContext, AttributeContext attributeContext) {
		
//		List<BoardManageEntity> list = new ArrayList<>();
//		BoardManageEntity item1 = new BoardManageEntity();
//		item1.setCategory(1);
//		item1.setNm("유머/일반");
//		
//		BoardManageEntity item2 = new BoardManageEntity();
//		item2.setCategory(2);
//		item2.setNm("게임");
//		
//		list.add(item1);
//		list.add(item2);
		
		//const key 메뉴가 null 이면 값을 넣어줘라 // 왜? 위에와 같은 정보를 간단히 하기위해 이렇게 사용한다..
		
		if(Const.menus == null) {
			Const.menus = mapper.selMenuList();
			System.out.println("ddd"); //ddd는 로그인 할때 딱 한번만 찍혀   

		}
		
		attributeContext.putAttribute(Const.KEY_MENULIST, new Attribute(Const.menus), true); //중첩된 .jsp 에서 쓸수입게 하는게 true 아니면 false
		
		
	}

	
}
