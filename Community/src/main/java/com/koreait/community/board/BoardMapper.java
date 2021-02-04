package com.koreait.community.board;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.koreait.community.model.BoardDTO;
import com.koreait.community.model.BoardEntity;

@Mapper
public interface BoardMapper {
	int insBoard(BoardEntity p);
	List<BoardDomain> selBoardList(BoardDTO p);
	BoardDomain selBoard(BoardDTO  p);
	//select는 DTO를 사용 쓸데없는 용량 잡아먹지않기위해
	int updBoard(BoardEntity p);
	
}
