package demo.eternalreturn.domain.model.notification;

import demo.eternalreturn.domain.model.BaseEntity;
import demo.eternalreturn.domain.model.Member.Member;
import jakarta.persistence.*;
import lombok.*;

import javax.xml.crypto.dsig.spec.XSLTTransformParameterSpec;

@Getter
@Setter
@Builder
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor
@Entity
@Table(name = "notification")
public class Notification extends BaseEntity {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Embedded
    private NotificationContent content;

    @Embedded
    private RelatedUrl url;

    private String toName;

    @Column(nullable = false)
    @Builder.Default
    private Boolean isRead = false;

    @Enumerated(EnumType.STRING)
    private NotificationType notificationType;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member receiver;

    public String getContent() {
        return content.getContent();
    }

    public String getUrl() {
        return url.getUrl();
    }

    public void read() {
        isRead = true;
    }

    public static Notification create(Member member, NotificationType notificationType, String content, String url) {
        return Notification.builder()
                .receiver(member)
                .notificationType(notificationType)
                .content(new NotificationContent(content))
                .url(new RelatedUrl(url))
                .build();
    }
}
