package com.a.board1.domain;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/board")
@RequiredArgsConstructor
public class BoardController {

    private final BoardService boardService;

    // 목록 페이지
    @GetMapping("/list")
    public String list(Model model) {
        model.addAttribute("list", boardService.getBoardList());
        return "list";
    }

    // 글쓰기 페이지 이동
    @GetMapping("/write")
    public String writeForm() {
        return "write";
    }

    // 글쓰기 처리
    @PostMapping("/write")
    public String write(Board board) {
        boardService.saveBoard(board);
        return "redirect:/board/list";
    }

    // 상세 페이지
    @GetMapping("/view/{id}")
    public String view(@PathVariable Long id, Model model) {
        model.addAttribute("board", boardService.getBoard(id));
        return "view";
    }

    // 수정 페이지 이동
    @GetMapping("/edit/{id}")
    public String editForm(@PathVariable Long id, Model model) {
        model.addAttribute("board", boardService.getBoard(id));
        return "edit";
    }

    // 수정 처리
    @PostMapping("/update/{id}")
    public String update(@PathVariable Long id, Board board) {
        boardService.updateBoard(id, board);
        return "redirect:/board/view/" + id;
    }

    // 삭제 처리
    @PostMapping("/delete/{id}")
    public String delete(@PathVariable Long id) {
        boardService.deleteBoard(id);
        return "redirect:/board/list";
    }
}
