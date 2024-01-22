package basic.board.service;

import basic.board.dto.BoardDTO;
import basic.board.entity.Board;
import basic.board.repository.BoardRepository;
import basic.board.utils.parser.TimeParser;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BoardServiceImpl implements BoardService {
    private final BoardRepository boardRepository;

    @Override
    public Board save(final BoardDTO boardDTO) {
        Long id = 0L;
        String writer = boardDTO.getWriter();
        String password = boardDTO.getPassword();
        String title = boardDTO.getTitle();
        String content = boardDTO.getContent();
        Integer hits = boardDTO.getHits();
        LocalDateTime createdTime = TimeParser.convertToTime(boardDTO.getCreatedTime());
        Board board = new Board(id, writer, password, title, content, hits, createdTime);
        return boardRepository.save(board);
    }

    @Override
    public Board findById(final Long id) {
        return boardRepository.findById(id);
    }

    @Override
    public Board findOnlyById(final Long id) {
        return boardRepository.findOnlyById(id);
    }

    @Override
    public List<BoardDTO> findAll() {
        List<Board> findBoards = boardRepository.findAll();
        List<BoardDTO> boardDTOs = new ArrayList<>();
        for (Board board : findBoards) {
            boardDTOs.add(BoardDTO.from(board));
        }
        return boardDTOs;
    }

    @Override
    public Board update(final Long id, final Board updatedBoard) {
        return boardRepository.update(id, updatedBoard);
    }

    @Override
    public void delete(final Long id) {
        boardRepository.delete(id);
    }
}
