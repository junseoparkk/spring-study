package basic.board.controller;

import basic.board.dto.BoardDTO;
import basic.board.entity.Board;
import basic.board.service.BoardService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.hibernate.cache.spi.support.AbstractReadWriteAccess.Item;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

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
    public String findAll(Model model) {
        List<BoardDTO> boards = boardService.findAll();
        model.addAttribute("boards", boards);
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
