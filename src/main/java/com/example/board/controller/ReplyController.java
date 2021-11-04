package com.example.board.controller;

import com.example.board.beans.vo.Criteria;
import com.example.board.beans.vo.ReplyPageDTO;
import com.example.board.beans.vo.ReplyVO;
import com.example.board.services.ReplyService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.UnsupportedEncodingException;
import java.util.List;

@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/replies/*")
public class ReplyController {

    private final ReplyService replyService;

//    댓글 등록
//    브라우저에서 JSON타입으로 데이터를 전송하고 서버에서는 댓글의 처리 결과에 따라 문자열로 결과를 리턴한다.
//    consumes : Ajax를 통해 전달받은 데이터의 타입
//    produces : Ajax의 success:function(result)에 있는 result로 전달할 데이터 타입
//    @ResponseBody : @Controller에서 REST API를 구현하기 위해서 사용된다.

//    문자열을 전달할 때 한글이 깨지지 않게 하기 위해서는 text/plain; charset=utf-8을 작성한다.
//    ResponseEntity : 서버의 상태 코드, 응답 메세지 등을 담을 수 있는 타입이다.
    @PostMapping(value = "/new", consumes = "application/json", produces = "text/plain; charset=utf-8")
    public ResponseEntity<String> create(@RequestBody ReplyVO replyVO) throws UnsupportedEncodingException {

        int replyCount = replyService.register(replyVO);
        log.info("ReplyVO : " + replyVO);
        log.info("REPLY INSERT COUNT : " + replyCount);
        return replyCount == 1 ?
                new ResponseEntity<>(new String("댓글 등록 성공".getBytes(), "UTF-8"), HttpStatus.OK) :
                new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }

//    게시글 댓글 전체 조회
    @GetMapping("pages/{bno}/{page}")
    public ReplyPageDTO getList(@PathVariable("bno") Long bno, @PathVariable("page") int page){
        log.info("getList............");
        Criteria criteria = new Criteria(page, 10);
        log.info(criteria.toString());
        return replyService.getList(bno, criteria);
    }

//    댓글 조회
//    URI에 댓글 번호만 작성한다.
//    전달받은 rno를 JSON으로 리턴한다.
    @GetMapping("{rno}")
    public ReplyVO get(@PathVariable("rno") Long rno){
        log.info("get............");
        return replyService.get(rno);
    }

//    댓글 수정
//    PUT : 자원의 전체 수정, 자원 내 모든 필드를 전달해야 함, 일부만 전달할 경우 오류
//    PATCH : 자원의 일부 수정, 수정할 필드만 전송(자동 주입이 아닌 부분만 수정하는 쿼리문에서 사용)
//    PATCH가 PUT을 담고 있기 때문에 전체를 전달 받아서 전체를 수정하는 상황, 전체 중 부분만 수정하는 상황 모두 PATCH를 사용하는 것이 좋다.
    @RequestMapping(
            method={RequestMethod.PUT, RequestMethod.PATCH},
            value="{rno}", consumes = "application/json", produces = "text/plain; charset=UTF-8"
    )
    public ResponseEntity<String> modify(@RequestBody ReplyVO replyVO, @PathVariable("rno") Long rno) throws UnsupportedEncodingException{
        log.info("modify...............");
        replyVO.setRno(rno);
        return replyService.modify(replyVO) == 1 ?
                new ResponseEntity<>(new String("댓글 수정 성공".getBytes(), "UTF-8"), HttpStatus.OK) :
                new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }

//    댓글 삭제
//    URI로 댓글 번호를 전달받은 후 성공 시 댓글 삭제 성공 전달
    @DeleteMapping(value="{rno}", produces = "text/plain; charset=utf-8")
    public ResponseEntity<String> remove(@PathVariable("rno") Long rno) throws UnsupportedEncodingException{
        log.info("remove.............");
        return replyService.remove(rno) == 1 ?
                new ResponseEntity<>(new String("댓글 삭제 성공".getBytes(), "UTF-8"), HttpStatus.OK) :
                new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }
}




















