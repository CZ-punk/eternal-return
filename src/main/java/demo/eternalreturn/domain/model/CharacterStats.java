package demo.eternalreturn.domain.model;

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

    private int characterCode;
    private int totalGames;
    private int usages;
    private int maxKillings;
    private int top3;
    private int wins;
    private double top3Rate;
    private double averageRank;

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
