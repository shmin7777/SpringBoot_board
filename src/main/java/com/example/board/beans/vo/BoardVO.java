package com.example.board.beans.vo;

import lombok.Data;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Data
public class BoardVO {
    private Long bno;
    private String title;
    private String content;
    private String writer;
    private String regDate;
    private String updateDate;
    //input 태그의 name에
    //attachList[i].fileName...
    //방식으로 submit하면 자동으로 List에 add할 수 있게 된다.
    private List<AttachFileVO> attachList;
}












