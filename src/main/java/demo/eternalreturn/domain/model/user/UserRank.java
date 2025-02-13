package demo.eternalreturn.domain.model.user;

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
@Table(name = "user_rank")
public class UserRank {

    @Id
    private Integer userNum;
    private Integer serverCode;

    private Integer mmr;
    private Integer serverRank;
    private String nickname;
    @Column(name = "`rank`")
    private Integer rank;

    public UserRank update(UserRank setEntity) {
        this.serverCode = setEntity.serverCode;
        this.mmr = setEntity.mmr;
        this.serverRank = setEntity.serverRank;
        this.nickname = setEntity.nickname;
        this.rank = setEntity.rank;

        return this;
    }
}
