package demo.eternalreturn.presentation.dto.response.notification;

import demo.eternalreturn.domain.model.notification.Notification;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.cglib.core.Local;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ResNotificationDto {

    private Long id;
    private String content;
    private String url;
    private Boolean isRead;
    private LocalDateTime createdAt;

    public ResNotificationDto toDto(Notification notification) {
        return ResNotificationDto.builder()
                .id(notification.getId())
                .content(notification.getContent())
                .url(notification.getUrl())
                .isRead(notification.getIsRead())
                .createdAt(notification.getCreatedAt())
                .build();
    }
}
