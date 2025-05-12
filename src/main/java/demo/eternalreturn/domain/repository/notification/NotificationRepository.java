package demo.eternalreturn.domain.repository.notification;

import demo.eternalreturn.domain.model.Member.Member;
import demo.eternalreturn.domain.model.notification.Notification;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface NotificationRepository  extends JpaRepository<Notification, Long> {

    List<Notification> findAllByReceiver(Member member);

}
