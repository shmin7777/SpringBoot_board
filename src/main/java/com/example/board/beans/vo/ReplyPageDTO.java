package com.example.board.beans.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Data
@AllArgsConstructor
public class ReplyPageDTO {
    private int replyCnt;
    private List<ReplyVO> list;

    public ReplyPageDTO() {;}
}
