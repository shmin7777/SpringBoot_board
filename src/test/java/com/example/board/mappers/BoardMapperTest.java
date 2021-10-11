package com.example.board.mappers;

import com.example.board.beans.vo.BoardVO;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@Slf4j
public class BoardMapperTest {

    @Autowired
    private BoardMapper mapper;

    @Test
    public void testGetList(){
        mapper.getList().forEach(board -> log.info(board.toString()));
    }

    @Test
    public void testInsert(){
        BoardVO board = new BoardVO();
        board.setTitle("새로 작성한 글 제목");
        board.setContent("새로 작성한 글 내용");
        board.setWriter("user01");
        mapper.insert(board);
    }

    @Test
    public void testInsertSelectKey_bno(){
        BoardVO board = new BoardVO();
        board.setTitle("새로 작성한 글 제목2");
        board.setContent("새로 작성한 글 내용2");
        board.setWriter("user02");
        mapper.insertSelectKey_bno(board);
    }

    @Test
    public void testRead(){
        log.info(mapper.read(4L).toString());
    }

    @Test
    public void testUpdate(){
        if(mapper.read(100L) == null){
            log.info("***********NO SUCH BOARD***********");
        }else{
            BoardVO board = new BoardVO();
            board.setBno(100L);
            board.setTitle("수정된 글 제목");
            board.setContent("수정된 글 내용");
            log.info("UPDATE COUNT : " + mapper.update(board));
        }
    }

    @Test
    public void testDelete(){
        if(mapper.read(4L) == null){
            log.info("***********NO SUCH BOARD***********");
        }else{
            log.info("DELETE COUNT : " + mapper.delete(4L));
        }
    }
}







