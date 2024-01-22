package basic.board.repository;

import basic.board.entity.Board;
import java.util.List;

public interface BoardRepository {
    Board save(Board board);
    Board findById(Long id);
    Board findOnlyById(Long id);
    List<Board> findAll();
    Board update(Long id, Board updatedBoard);
    void delete(Long id);
}
