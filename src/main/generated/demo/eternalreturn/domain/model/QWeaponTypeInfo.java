package demo.eternalreturn.domain.model;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QWeaponTypeInfo is a Querydsl query type for WeaponTypeInfo
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QWeaponTypeInfo extends EntityPathBase<WeaponTypeInfo> {

    private static final long serialVersionUID = -1302449561L;

    public static final QWeaponTypeInfo weaponTypeInfo = new QWeaponTypeInfo("weaponTypeInfo");

    public final NumberPath<Double> attackRange = createNumber("attackRange", Double.class);

    public final NumberPath<Double> attackSpeed = createNumber("attackSpeed", Double.class);

    public final NumberPath<Double> shopFilter = createNumber("shopFilter", Double.class);

    public final NumberPath<Double> summonObjectHitDamage = createNumber("summonObjectHitDamage", Double.class);

    public final EnumPath<demo.eternalreturn.domain.model.constant.WeaponType> type = createEnum("type", demo.eternalreturn.domain.model.constant.WeaponType.class);

    public QWeaponTypeInfo(String variable) {
        super(WeaponTypeInfo.class, forVariable(variable));
    }

    public QWeaponTypeInfo(Path<? extends WeaponTypeInfo> path) {
        super(path.getType(), path.getMetadata());
    }

    public QWeaponTypeInfo(PathMetadata metadata) {
        super(WeaponTypeInfo.class, metadata);
    }

}

