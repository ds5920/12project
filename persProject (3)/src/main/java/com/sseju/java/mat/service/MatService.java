package com.sseju.java.mat.service;

import java.util.List;
import java.util.Map;

public interface MatService {

	//material 기본자재리스트
	public List<MatVO> material();

	//발주 전체 조회
	public List<MatVO> matordList();
	//발주 단건 조회
	public MatVO selectMatbuyInfo(MatVO MatVO);
	//발주할 주문 조회
	public List<MatVO> prtOrdList();
	//발주할 주문 단건 조회
	public List<MatVO> infoModal(MatVO MatVO);
	//발주 자재량 조회
	public List<MatVO> matOrdModal(MatVO MatVO);
	//발주 등록
	public int insertMatbuy(MatVO MatVO);
	//발주 수정
	public int updateMatbuy(MatVO MatVO);
	//발주 수정
	public int updateordYn(MatVO MatVO);
	//발주 삭제
	public int deleteMatbuy(List<String> matOrdNo);
	public int selectDelete(List<String> list);
	
	//입고 전체 조회
	public List<MatVO> matInList();
	//입고 단건 조회
	public MatVO selectMatIn(MatVO MatVO);
	//ordno 조회
	public MatVO selectMatordno(MatVO MatVO);
	
	//입고 등록
	public int insertMat(MatVO MatVO);
	//입고 자동 등록(발주 수정시)
	public int insertMatord(MatVO MatVO);
	//입고 수정
	public int updateMat(MatVO MatVO);
	public int selectDeleteMatIn(MatVO MatVO);
	
	
	//미입고 조회
	public List<MatVO> matNotinList();
	//미입고 수정
	public int updateMatnotIn(MatVO MatVo);
	//미입고 => LOT 입고
	public int insertLot(MatVO MatVO);
	//미입고 =>chk테이블
	public int updateChk(MatVO MatVO);
	//미입고 => 발주Yn => 입고로 수정
	public int updateMoy(MatVO MatVO);
	//미입고 => 입고시 불량
	public int insertErr(MatVO MatVO);
	//미입고 삭제
	public int deleteMat(List<String> matOrdNo);
	
	
	//출고 전체 조회
	public List<MatVO> matOutList();
	//출고 단건 조회
	public MatVO selectMatOut(MatVO MatVO);
	//출고 등록
	public int insertMatOut(MatVO MatVO);
	//출고 수정
	public int updateMatOut(MatVO MatVO);
	//출고 삭제
	public int deleteMatOut(MatVO MatVO);
	public int selectDeleteMatOut(MatVO MatVO);
	
	//재고 전체 조회
	public List<MatVO> matStockList();
	//재고 단건 조회
	public MatVO selectMatStock(MatVO MatVO);
	//재고 등록
	public int insertMatStock(MatVO MatVO);
	//재고 수정
	public int updateMatStock(MatVO MatVO);
	//재고 삭제
	public int deleteMatStock(MatVO MatVO);
	public int selectDeleteMatStock(List<String> list);
	
	//검수 내역 전체 조회
	public List<MatVO> matCheckList();
	//검수 단건 조회
	public MatVO selectMatCheck(MatVO MatVO);
	//검수 등록
	public int insertMatCheck(MatVO MatVO);
	//검수 수정
	public int updateMatCheck(MatVO MatVO);
	//검수 삭제
	public int deleteMatCheck(MatVO MatVO);
	public int selectDeleteMatCheck(List<String> list);
	
	//내역 내용 등록
	public int docUpdate(MatVO MatVO);
	//에러 코드 조회
	public List<MatVO> errCode();
}
