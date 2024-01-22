package basic.board.repository;

import basic.board.entity.Board;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import org.springframework.stereotype.Repository;

@Repository
public class MemoryBoardRepository implements BoardRepository {
    private Long sequence = 0L;
    private final Map<Long, Board> boards = new ConcurrentHashMap<>();

    @Override
    public Board save(final Board board) {
        board.setId(++sequence);
        System.out.println("board.getId() = " + board.getId());
        boards.put(board.getId(), board);
        return board;
    }

    @Override
    public Board findById(Long id) {
        Board findBoard = boards.get(id);
        findBoard.updateHits();
        boards.put(findBoard.getId(), findBoard);
        return boards.get(id);
    }

    @Override
    public List<Board> findAll() {
        return new ArrayList<>(boards.values());
    }

    @Override
    public Board update() {
        return null;
    }

    @Override
    public void delete(final Long id) {
        boards.remove(id);
    }
}
