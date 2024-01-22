package basic.board.dto;

import basic.board.entity.Board;
import basic.board.utils.parser.TimeParser;
import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
public class BoardDTO {
    private Long id;
    private String writer;
    private String password;
    private String title;
    private String content;
    private int hits;
    private String createdTime;

    @Override
    public String toString() {
        return "BoardDTO{" +
                ", writer='" + writer + '\'' +
                ", password='" + password + '\'' +
                ", title='" + title + '\'' +
                ", content='" + content + '\'' +
                ", hits=" + hits +
                '}';
    }

    public static BoardDTO from(final Board board) {
        Long id = board.getId();
        String writer = board.getWriter();
        String password = board.getPassword();
        String title = board.getTitle();
        String content = board.getContent();
        int hits = board.getHits();
        String createdTime = TimeParser.convertToString(board.getCreatedTime());
        return new BoardDTO(id, writer, password, title, content, hits, createdTime);
    }
}
