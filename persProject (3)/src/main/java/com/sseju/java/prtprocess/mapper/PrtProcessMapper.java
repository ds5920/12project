package com.sseju.java.prtprocess.mapper;

import java.util.List;

import com.sseju.java.prtprocess.service.PrtProcessVO;
//사용X 생산공정관리 페이지용
public interface PrtProcessMapper {
		//생산 공정 관리 전체 조회
		public List<PrtProcessVO> getPrtProcessList();
		//생산 공정 단건 조회
		public PrtProcessVO getSearchPrtProcess();
		//생산 공정 등록
		public int insertPrtProcessVO(PrtProcessVO prptVO);
		//생산 공정 수정(업데이트)
		public int updatePrtProcessVO(PrtProcessVO prptVO);
		//생산 공정 삭제
		public int deletePrtProcessVO(PrtProcessVO prptVO);
}
