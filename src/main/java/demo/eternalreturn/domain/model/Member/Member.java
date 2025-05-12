package demo.eternalreturn.domain.model.Member;

import demo.eternalreturn.domain.constant.SocialProvider;
import demo.eternalreturn.domain.model.BaseEntity;
import demo.eternalreturn.domain.model.Board.Board;
import demo.eternalreturn.domain.model.comment.Comment;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Getter
@Setter
@Builder
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor
@Entity
@Table(name = "member")
public class Member extends BaseEntity {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String loginId;
    private String loginPw;
    private String username;

    private String profileImageUrl;
    @Enumerated(EnumType.STRING)
    private SocialProvider provider;    // Social Provider ( ex. google, kakao )
    private String providerId;          // Social Provider pk
    private String providerEmail;       // Social Email

    @OneToMany(mappedBy = "member", cascade = CascadeType.ALL, orphanRemoval = true)
    @Builder.Default
    private Set<MemberRole> roles = new HashSet<>();

    @OneToMany(mappedBy = "member", cascade = CascadeType.ALL, orphanRemoval = true)
    @Builder.Default
    private List<Board> boardList = new ArrayList<>();

    @OneToMany(mappedBy = "member", cascade = CascadeType.ALL, orphanRemoval = true)
    @Builder.Default
    private List<Comment> commentList = new ArrayList<>();

    private String refreshToken;

    public void signOut() {
        this.refreshToken = null;
    }

    public void connectionRole(MemberRole role) {
        this.roles.add(role);
        role.setMember(this);
    }

    public void connectionBoard(Board board) {
        this.boardList.add(board);
        board.setMember(this);
    }

    public void connectionComment(Comment comment) {
        this.commentList.add(comment);
    }

    public void updateUsername(String username) {
        this.username = username;
    }
}
