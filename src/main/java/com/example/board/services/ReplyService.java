package com.example.board.services;

import com.example.board.beans.dao.ReplyDAO;
import com.example.board.beans.vo.Criteria;
import com.example.board.beans.vo.ReplyPageDTO;
import com.example.board.beans.vo.ReplyVO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class ReplyService {
    private final ReplyDAO replyDAO;

    public int register(ReplyVO replyVO){
        log.info("register..............");
        return replyDAO.register(replyVO);
    }

    public ReplyVO get(Long rno){
        log.info("get..............");
        return replyDAO.get(rno);
    }

    public int remove(Long rno){
        log.info("remove..............");
        return replyDAO.remove(rno);
    }

    public int modify(ReplyVO replyVO){
        log.info("modify..............");
        return replyDAO.modify(replyVO);
    }

    public ReplyPageDTO getList(Long bno, Criteria criteria){
        log.info("getList..............");
        return new ReplyPageDTO(replyDAO.getTotal(bno), replyDAO.getList(bno, criteria));
    }
}
