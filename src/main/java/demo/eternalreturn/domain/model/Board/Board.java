package demo.eternalreturn.domain.model.Board;

import demo.eternalreturn.domain.model.BaseEntity;
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
@Table(name = "board")
public class Board extends BaseEntity {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;
    private String contents;

    @ElementCollection(targetClass = String.class)
    @CollectionTable(name = "board_image", joinColumns = @JoinColumn(name = "board"))
    @Builder.Default
    private List<String> imageUrlList = new ArrayList<>();

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

}
