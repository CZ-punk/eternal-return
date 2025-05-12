package demo.eternalreturn.domain.model.notification;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QNotificationContent is a Querydsl query type for NotificationContent
 */
@Generated("com.querydsl.codegen.DefaultEmbeddableSerializer")
public class QNotificationContent extends BeanPath<NotificationContent> {

    private static final long serialVersionUID = 833318318L;

    public static final QNotificationContent notificationContent = new QNotificationContent("notificationContent");

    public final StringPath content = createString("content");

    public QNotificationContent(String variable) {
        super(NotificationContent.class, forVariable(variable));
    }

    public QNotificationContent(Path<? extends NotificationContent> path) {
        super(path.getType(), path.getMetadata());
    }

    public QNotificationContent(PathMetadata metadata) {
        super(NotificationContent.class, metadata);
    }

}

