package com.example.board.services;

import com.example.board.beans.vo.BoardVO;
import org.springframework.stereotype.Service;

import java.util.List;

//    서비스를 설계할 때, 여러 클래스에 동일한 기능을 구현한다면, 인터페이스로 선언 후 지정하여 개발한다.
//    하지만, 만약 여러 클래스에 동일한 인터페이스를 지정한다면, 인터페이스로 의존성 주입 요청 시 어떤 클래스로
//    주입할 지 Spring은 알 수 없다.
//    따라서 주입 요청 시 등록되어 있는 빈의 이름을 설정하여 원하는 클래스로 주입을 받게 된다.
//    Mapper 인터페이스는 xml의 쿼리 결과로 자동 주입 되기 때문에 Service에 있는 인터페이스와 성격과 목적이 다르다.

@Service
public interface BoardService {
    public void register(BoardVO board);
    public BoardVO get(Long bno);
    public boolean modify(BoardVO board);
    public boolean remove(Long bno);
    public List<BoardVO> getList();
}
