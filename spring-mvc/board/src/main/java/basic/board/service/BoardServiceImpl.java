package basic.board.service;

import basic.board.dto.BoardDTO;
import basic.board.entity.Board;
import basic.board.repository.BoardRepository;
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
        return boardRepository.save(Board.from(boardDTO));
    }

    @Override
    public Board findById(final Long id) {
        return boardRepository.findById(id);
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
    public Board update() {
        return null;
    }

    @Override
    public void delete(final Long id) {
        boardRepository.delete(id);
    }
}
