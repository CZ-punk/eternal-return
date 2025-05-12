package demo.eternalreturn.domain.model.notification;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QRelatedUrl is a Querydsl query type for RelatedUrl
 */
@Generated("com.querydsl.codegen.DefaultEmbeddableSerializer")
public class QRelatedUrl extends BeanPath<RelatedUrl> {

    private static final long serialVersionUID = 33329316L;

    public static final QRelatedUrl relatedUrl = new QRelatedUrl("relatedUrl");

    public final StringPath url = createString("url");

    public QRelatedUrl(String variable) {
        super(RelatedUrl.class, forVariable(variable));
    }

    public QRelatedUrl(Path<? extends RelatedUrl> path) {
        super(path.getType(), path.getMetadata());
    }

    public QRelatedUrl(PathMetadata metadata) {
        super(RelatedUrl.class, metadata);
    }

}

