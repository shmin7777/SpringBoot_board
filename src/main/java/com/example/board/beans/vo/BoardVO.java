package com.example.board.beans.vo;

import lombok.Data;
import org.springframework.stereotype.Component;

@Component
@Data
public class BoardVO {
    private Long bno;
    private String title;
    private String content;
    private String writer;
    private String regDate;
    private String updateDate;
}












