package com.sseju.java.lottrack.service;

import java.util.List;

public interface LotTrackService {
	//로트 조회 페이지용 CRUD
	//C로트 등록
	public int insertLotTrack(LotTrackVO ltVO);
	//R 로트 조회
	public List<LotTrackVO> getLotTrackList();
	//U 로트 수정
	public int updateLotTrack(LotTrackVO ltVO);
	//D 로트 삭제
	public int deleteLotTrack(LotTrackVO ltVO);
}
