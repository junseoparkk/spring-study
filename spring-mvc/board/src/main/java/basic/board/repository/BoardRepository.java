package basic.board.repository;

import basic.board.entity.Board;
import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

public interface BoardRepository {
    Board save(Board board);
    Board findById(Long id);
    Board findOnlyById(Long id);
    List<Board> findAll();
    Page<Board> findAllByPaging(PageRequest request);
    Board update(Long id, Board updatedBoard);
    void delete(Long id);
}
