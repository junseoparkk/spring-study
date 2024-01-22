package basic.board.service;

import basic.board.dto.BoardDTO;
import basic.board.entity.Board;
import basic.board.repository.BoardRepository;
import basic.board.utils.parser.TimeParser;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
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

    public Page<BoardDTO> paging(final Pageable pageable) {
        int page = pageable.getPageNumber() - 1;
        int pageLimit = 3;

        // 한페이지당 3개씩 글을 보여주고 정렬 기준은 id 기준으로 내림차순 정렬
        // page 위치에 있는 값은 0부터 시작
        Page<Board> boards = boardRepository.findAllByPaging(PageRequest.of(page, pageLimit, Sort.by(Direction.DESC, "id")));

        // 목록: id, writer, title, hits, createdTime
        return boards.map(board ->
                new BoardDTO(
                        board.getId(),
                        board.getWriter(),
                        board.getTitle(),
                        board.getContent(),
                        board.getHits(),
                        TimeParser.convertToString(board.getCreatedTime())));
    }
}
