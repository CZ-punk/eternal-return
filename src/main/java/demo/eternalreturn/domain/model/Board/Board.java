package demo.eternalreturn.domain.model.Board;

import demo.eternalreturn.domain.model.BaseEntity;
import demo.eternalreturn.domain.model.Member.Member;
import demo.eternalreturn.domain.model.comment.Comment;
import demo.eternalreturn.presentation.dto.request.board.ReqPostBoardDto;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Builder
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor
@Entity
@ToString
@Table(name = "board")
public class Board extends BaseEntity {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String title;
    @Column(nullable = false)
    private String contents;
    @Column(nullable = false)
    private String author;

    @Column(nullable = false)
    private Integer likeCount;
    @Column(nullable = false)
    private Integer commentCount;
    @Column(nullable = false)
    private Integer viewCount;

    @OneToMany(mappedBy = "board", cascade = CascadeType.ALL, orphanRemoval = true)
    @Builder.Default
    private List<Comment> commentList = new ArrayList<>();

    @ElementCollection(targetClass = String.class)
    @CollectionTable(name = "board_image", joinColumns = @JoinColumn(name = "board"))
    @Builder.Default
    private List<String> imageUrlList = new ArrayList<>();

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    public static Board createBoard(ReqPostBoardDto dto, String author, List<String> imageUrlList) {
        return  Board.builder()
                .title(dto.getTitle())
                .contents(dto.getContents())
                .author(author)
                .likeCount(0)
                .commentCount(0)
                .viewCount(0)
                .commentList(null)
                .imageUrlList(imageUrlList)
                .build();
    }

    public void addView() {
        viewCount++;
    }

    public void addLike() {
        likeCount++;
    }

    public void connectionComment(Comment comment) {
        commentList.add(comment);
        commentCount = commentList.size();
    }
}
