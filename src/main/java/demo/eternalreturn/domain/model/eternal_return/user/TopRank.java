package demo.eternalreturn.domain.model.eternal_return.user;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor
@Entity
@ToString   // 테스트 후 삭제
@Table(name = "top_rank")
public class TopRank {

    @Id
    private Integer userNum;
    private String nickname;
    @Column(name = "`rank`", nullable = false)
    private Integer rank;
    private Integer mmr;

}
