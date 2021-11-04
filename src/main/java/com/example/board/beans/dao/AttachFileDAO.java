package com.example.board.beans.dao;

import com.example.board.beans.vo.AttachFileVO;
import com.example.board.mappers.AttachFileMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class AttachFileDAO {
    @Autowired
    private AttachFileMapper attachFileMapper;

    public void insert(AttachFileVO attachFileVO){
        attachFileMapper.insert(attachFileVO);
    }

    public void delete(String uuid){
        attachFileMapper.delete(uuid);
    }

    public List<AttachFileVO> findByBno(Long bno){
        return attachFileMapper.findByBno(bno);
    }

    public void deleteAll(Long bno){attachFileMapper.deleteAll(bno);}

    public List<AttachFileVO> getOldFiles() {return attachFileMapper.getOldFiles();}
}


















