package org.example.boardproject.controller;

import lombok.RequiredArgsConstructor;
import org.example.boardproject.domain.Board;
import org.example.boardproject.service.BoardService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.time.LocalDateTime;

@Controller
@RequiredArgsConstructor
@RequestMapping("/view")
public class BoardController {

    private final BoardService service;

/*    @GetMapping()
    public String Home(Model model) {
        Iterable<Board> board = service.findAllBoard();
        model.addAttribute("board", board);

        return "board/list";
    }*/

    @GetMapping()
    public String Home(Model model, @RequestParam(defaultValue = "1") int page, @RequestParam(defaultValue = "5") int size ) {

        Pageable pageable = PageRequest.of(page - 1, size);

        Page<Board> board = (Page<Board>) service.findAllBoard(pageable);

        model.addAttribute("board", board);
        model.addAttribute("currentPage", page);

        return "board/list";
    }


    //글 등록
    @GetMapping("/writeForm")
    public String addForm(Model model) {
        model.addAttribute("board", new Board());

        return "/board/writeForm";
    }

    @PostMapping("/writeForm")
    public String addBoard(@Validated @ModelAttribute Board board, BindingResult result) {

        if (result.hasErrors()) {
            return "/board/writeForm";
        }

        //등록 시간도 (LocalDateTime도 같이) board에 넣어줘야 함
        LocalDateTime now = LocalDateTime.now();
        board.setCreatedAt(now);

        service.addBoard(board);

        return "redirect:/view";
    }

    // 상세보기
    @GetMapping("/{id}")
    public String detail(@PathVariable Long id, Model model) {
        Board board = service.findById(id);
        model.addAttribute("board", board);
        return "/board/detail";
    }

    //게시물 수정
    @GetMapping("/edit/{id}")
    public String editForm(@PathVariable("id") Long id, Model model) {
        Board board = service.findById(id);
        model.addAttribute("board", board);

        return "board/edit";
    }


    @PostMapping("/edit/{id}")
    public String editBoard(@PathVariable Long id, @ModelAttribute Board board, BindingResult result, Model model) {

        Board preBoard = service.findById(id);

        if (!preBoard.getPassword().equals(board.getPassword())) {
            model.addAttribute("board", board);
            model.addAttribute("error", "비밀 번호가 일치하지 않습니다.");
            return "board/edit";
        }

        //수정 날짜
        LocalDateTime now = LocalDateTime.now();
        board.setUpdatedAt(now);

        model.addAttribute("board", board);
        service.save(board);

        return "redirect:/view";
    }


    //게시물 삭제
    @GetMapping("/delete/{id}")
    public String deleteForm(@PathVariable("id") Long id, Model model) {

        Board board = service.findById(id);
        model.addAttribute("board", board);

        return "/board/deleteForm";
    }

    @PostMapping("/delete/{id}")
    public String deleteBoard(@PathVariable("id") Long id, @ModelAttribute Board board, BindingResult result, Model model ) {
        Board preBoard = service.findById(id);

        if (!preBoard.getPassword().equals(board.getPassword())) {
            model.addAttribute("board", board);
            model.addAttribute("error", "비밀번호가 일치하지 않습니다.");
            return "board/deleteForm";
        }
        model.addAttribute("board", board);
        service.deleteBoard(id);
        return "redirect:/view";
    }

    /*@PostMapping("/delete/{id}")
    public String deleteBoard(@PathVariable("id") Long id, @ModelAttribute Board board, Model model) {
        Board preBoard = service.findById(id);
        model.addAttribute("board", board);

        if (preBoard.getPassword().equals(board.getPassword())) {
            service.deleteBoard(id);
            return "redirect:/view";
        } else {
            return "/board/wrongPasswordDelete";
        }

    }*/
}
