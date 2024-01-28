package basic.board.entity;

import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Board {
    private Long id;
    private String writer;
    private String password;
    private String title;
    private String content;
    private Integer hits = 0;
    private LocalDateTime createdTime;

    public void setId(final Long id) {
        this.id = id;
    }

    public void setContent(final String content) {
        this.content = content;
    }

    public void updateHits() {
        hits++;
    }
}
