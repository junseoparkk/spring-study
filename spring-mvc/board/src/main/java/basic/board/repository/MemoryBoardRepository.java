package basic.board.repository;

import basic.board.entity.Board;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Repository;

@Repository
public class MemoryBoardRepository implements BoardRepository {
    private Long sequence = 0L;
    private final Map<Long, Board> boards = new ConcurrentHashMap<>();

    @Override
    public Board save(final Board board) {
        board.setId(++sequence);
        boards.put(board.getId(), board);
        return board;
    }

    @Override
    public Board findById(final Long id) {
        Board findBoard = boards.get(id);
        findBoard.updateHits();
        boards.replace(findBoard.getId(), findBoard);
        return boards.get(id);
    }

    @Override
    public Board findOnlyById(final Long id) {
        return boards.get(id);
    }

    @Override
    public List<Board> findAll() {
        return new ArrayList<>(boards.values());
    }

    @Override
    public Page<Board> findAllByPaging(PageRequest request) {
        int offset = request.getPageNumber() * request.getPageSize();
        int limit = request.getPageSize();

        List<Board> boards = findAll();
        Collections.reverse(boards);
        int totalBoards = boards.size();

        List<Board> pagedBoards = boards.subList(offset, Math.min(offset + limit, totalBoards));
        return new PageImpl<>(pagedBoards, request, totalBoards);
    }

    @Override
    public Board update(final Long id, final Board updatedBoard) {
        boards.get(id).setContent(updatedBoard.getContent());
        return boards.get(id);
    }

    @Override
    public void delete(final Long id) {
        boards.remove(id);
    }
}
