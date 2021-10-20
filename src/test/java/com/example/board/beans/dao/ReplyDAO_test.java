package com.example.board.beans.dao;

import com.example.board.beans.vo.Criteria;
import com.example.board.beans.vo.ReplyVO;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.stream.IntStream;

@SpringBootTest
@Slf4j
public class ReplyDAO_test {

    private Long[] arBno = {8388648L, 8388647L, 8388646L, 8388645L, 8388644L};

    @Setter(onMethod_ = @Autowired)
    private ReplyDAO replyDAO;

    @Test
    public void testDAO(){
        log.info(replyDAO.toString());
    }

//    5칸 배열 만들기(게시글 번호 투입)
//    5개의 게시글에 2개씩 댓글 달기
    @Test
    public void testInsert(){
        IntStream.rangeClosed(1, 10).forEach(i -> {
            ReplyVO reply = new ReplyVO();
            reply.setBno(arBno[i % 5]);
            reply.setReply("댓글 테스트" + i);
            reply.setReplier("replier" + i);
            replyDAO.register(reply);
        });
    }

    @Test
    public void testRead(){
        log.info(replyDAO.get(16L).toString());
    }

    @Test
    public void testDelete(){
        log.info("DELETE COUNT : " + replyDAO.remove(15L));
    }

    @Test
    public void testUpdate(){
        ReplyVO replyVO = replyDAO.get(16L);
        replyVO.setReply("UPDATE TEST");
        log.info("UPDATE COUNT : " + replyDAO.modify(replyVO));
    }

    @Test
    public void testGetListWithPaging(){
        Criteria criteria = new Criteria();
        replyDAO.getList(arBno[0], criteria).forEach(reply -> log.info(reply.toString()));
    }

}
