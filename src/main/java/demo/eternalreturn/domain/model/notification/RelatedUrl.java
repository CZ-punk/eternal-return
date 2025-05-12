package demo.eternalreturn.domain.model.notification;


import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Embeddable
@NoArgsConstructor
@AllArgsConstructor
public class RelatedUrl {

    @Column(nullable = false)
    private String url;
}
