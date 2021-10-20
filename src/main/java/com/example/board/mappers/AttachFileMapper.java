package com.example.board.mappers;

import com.example.board.beans.vo.AttachFileVO;

import java.util.List;

public interface AttachFileMapper {
    public void insert(AttachFileVO attachFileVO);
    public void delete(String uuid);
    public List<AttachFileVO> findByBno(Long bno);
    public void deleteAll(Long bno);
    public List<AttachFileVO> getOldFiles();
}
