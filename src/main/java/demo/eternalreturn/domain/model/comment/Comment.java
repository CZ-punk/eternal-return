package demo.eternalreturn.domain.model.comment;

import demo.eternalreturn.domain.model.BaseEntity;
import demo.eternalreturn.domain.model.Board.Board;
import demo.eternalreturn.domain.model.Member.Member;
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
@Table(name = "comment")
public class Comment extends BaseEntity {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String contents;

    @Column(nullable = false)
    @Builder.Default
    private Integer likeCount = 0;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "parent_id")
    private Comment parent;

//    @Column(nullable = false)
//    private String author;

    ///  query 한번 더 날리면 추가하는 것으로 하자....

    @OneToMany(mappedBy = "parent", cascade = CascadeType.ALL, orphanRemoval = true)
    @Builder.Default
    private List<Comment> replies = new ArrayList<>();

    @Column(nullable = false)
    @Builder.Default
    private Integer replyCount = 0;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "board_id")
    private Board board;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    public static Comment createComment(String contents) {
        return Comment.builder()
                .contents(contents)

                .likeCount(0)
                .replyCount(0)
                .build();
    }

    public void connectionMember(Member member) {
        this.member = member;
        member.connectionComment(this);
    }

    public void connectionBoard(Board board) {
        this.board = board;
        board.connectionComment(this);
    }

    public void connectionRecomment(Comment comment) {
        comment.setParent(this);
        this.replies.add(comment);
        this.replyCount = this.replies.size();
    }

    public void updateComment(String contents) {
        this.contents = contents;
    }
}
