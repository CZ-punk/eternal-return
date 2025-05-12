package demo.eternalreturn.domain.model.eternal_return.user;

import demo.eternalreturn.domain.model.eternal_return.user.UserStats;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.BatchSize;

import java.util.Objects;

@Getter
@Setter
@Builder
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor
@Entity
@Table(name = "character_stats")
public class CharacterStats {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private Integer userNum;
    private Integer characterCode;
    private Integer totalGames;
    private Double usages;
    private Integer maxKillings;
    private Double top3;
    private Double wins;
    private Double top3Rate;
    private Double averageRank;

    public void update(int characterCode, int totalGames, double usages, int maxKillings, double top3, double wins, double top3Rate, double averageRank) {
        this.characterCode = characterCode;
        this.totalGames = totalGames;
        this.usages = usages;
        this.maxKillings = maxKillings;
        this.top3 = top3;
        this.wins = wins;
        this.top3Rate = top3Rate;
        this.averageRank = averageRank;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;

        CharacterStats that = (CharacterStats) obj; // 캐스팅

        return characterCode.equals(that.characterCode) && userNum.equals(that.userNum);

    }

    @Override
    public int hashCode() {
        return Objects.hash(characterCode, userNum) ;
    }
}
