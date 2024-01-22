package basic.board.controller;

import basic.board.dto.BoardDTO;
import basic.board.entity.Board;
import basic.board.service.BoardService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
@RequestMapping("/board")
public class BoardController {
    private final BoardService boardService;

    @GetMapping("/save")
    public String saveForm() {
        return "/board/save";
    }

    @PostMapping("/save")
    public String save(BoardDTO boardDTO) {
        boardService.save(boardDTO);
        return "redirect:/board/list";
    }

    @GetMapping("/list")
    public String findAll(@PageableDefault(page = 1) Pageable pageable, Model model) {
        Page<BoardDTO> boards = boardService.paging(pageable);
        int blockLimit = 5;
        int startPage = (((int)(Math.ceil((double)pageable.getPageNumber() / blockLimit))) - 1) * blockLimit + 1;
        int endPage = Math.min((startPage + blockLimit - 1), boards.getTotalPages());

        model.addAttribute("boards", boards);
        model.addAttribute("startPage", startPage);

        if (endPage == 0) {
            model.addAttribute("endPage", startPage);
        } else {
            model.addAttribute("endPage", endPage);
        }
        return "/board/list";
    }

    @GetMapping("/{id}")
    public String findOne(@PathVariable(name = "id") Long id, Model model) {
        Board board = boardService.findById(id);
        System.out.println(board.toString());
        model.addAttribute("board", board);
        return "/board/detail";
    }

    @GetMapping("/{id}/delete")
    public String delete(@PathVariable(name = "id") Long id) {
        boardService.delete(id);
        return "redirect:/board/list";
    }

    @GetMapping("/{id}/update")
    public String updateForm(@PathVariable(name = "id") Long id, Model model) {
        Board board = boardService.findOnlyById(id);
        model.addAttribute(board);
        return "/board/update";
    }

    @PostMapping("/{id}/update")
    public String update(@PathVariable(name = "id") Long id, Board updatedBoard) {
        boardService.update(id, updatedBoard);
        Board board = boardService.findOnlyById(id);
        return "redirect:/board/{id}";
    }
}
