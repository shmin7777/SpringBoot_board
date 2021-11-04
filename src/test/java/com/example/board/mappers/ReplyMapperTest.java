package com.example.board.mappers;

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
public class ReplyMapperTest {

    private Long[] arBno = {8388648L, 8388647L, 8388646L, 8388645L, 8388644L};

    @Setter(onMethod_ = @Autowired)
    private ReplyMapper replyMapper;

    @Test
    public void testMapper(){
        log.info(replyMapper.toString());
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
            replyMapper.insert(reply);
        });
    }

    @Test
    public void testRead(){
        log.info(replyMapper.read(16L).toString());
    }

    @Test
    public void testDelete(){
        log.info("DELETE COUNT : " + replyMapper.delete(15L));
    }

    @Test
    public void testUpdate(){
        ReplyVO replyVO = replyMapper.read(16L);
        replyVO.setReply("UPDATE TEST");
        log.info("UPDATE COUNT : " + replyMapper.update(replyVO));
    }

    @Test
    public void testGetListWithPaging(){
        Criteria criteria = new Criteria();
        replyMapper.getListWithPaging(arBno[0], criteria).forEach(reply -> log.info(reply.toString()));
    }

}
