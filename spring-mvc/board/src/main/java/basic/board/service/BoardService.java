package basic.board.service;

import basic.board.dto.BoardDTO;
import basic.board.entity.Board;
import java.util.List;
import org.springframework.stereotype.Service;

public interface BoardService {
    Board save(BoardDTO boardDTO);
    Board findById(Long id);
    Board findOnlyById(Long id);
    List<BoardDTO> findAll();
    Board update(Long id, Board updatedBoard);
    void delete(Long id);
}
