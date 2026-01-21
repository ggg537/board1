package com.a.board1.domain;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class BoardService {

    private final BoardRepository boardRepository;

    // 목록 조회
    @Transactional(readOnly = true)
    public List<Board> getBoardList() {
        return boardRepository.findAllByOrderByCreatedDateDesc();
    }

    // 상세 조회
    @Transactional(readOnly = true)
    public Board getBoard(Long id) {
        return boardRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("게시글이 존재하지 않습니다."));
    }

    // 글 쓰기
    public void saveBoard(Board board) {
        boardRepository.save(board);
    }

    // 글 수정
    public void updateBoard(Long id, Board requestBoard) {
        Board board = boardRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("게시글이 존재하지 않습니다."));
        // JPA의 변경 감지(Dirty Checking) 활용
        board.setTitle(requestBoard.getTitle());
        board.setContent(requestBoard.getContent());
        board.setWriter(requestBoard.getWriter());
    }

    // 글 삭제
    public void deleteBoard(Long id) {
        boardRepository.deleteById(id);
    }
}
