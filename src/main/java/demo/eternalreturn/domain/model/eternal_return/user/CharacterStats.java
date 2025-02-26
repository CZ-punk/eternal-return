package demo.eternalreturn.domain.model.eternal_return.user;

import demo.eternalreturn.domain.model.eternal_return.user.UserStats;
import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor
@Entity
@Table(name = "character_stats")
public class CharacterStats {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Integer characterCode;
    private Integer totalGames;
    private Integer usages;
    private Integer maxKillings;
    private Integer top3;
    private Integer wins;
    private Double top3Rate;
    private Double averageRank;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_num")
    private UserStats userStats;

    public void update(int characterCode, int totalGames, int usages, int maxKillings, int top3, int wins, double top3Rate, double averageRank) {

        this.characterCode = characterCode;
        this.totalGames = totalGames;
        this.usages = usages;
        this.maxKillings = maxKillings;
        this.top3 = top3;
        this.wins = wins;
        this.top3Rate = top3Rate;
        this.averageRank = averageRank;
    }
}
