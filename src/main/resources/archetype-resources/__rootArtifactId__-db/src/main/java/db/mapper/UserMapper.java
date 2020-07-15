package ${package}.db.mapper;

import ${package}.db.entity.User;
import ${package}.db.entity.property.UserProperties;
import tech.ibit.mybatis.SingleIdMapper;
import tech.ibit.mybatis.sqlbuilder.Column;
import tech.ibit.mybatis.sqlbuilder.Table;


/**
 * SingleIdMapper for user
 *
 * @author IBIT程序猿
 */
public interface UserMapper extends SingleIdMapper<User, Integer> {

    /**
     * 获取实体类型
     *
     * @return 实体类型
     */
    @Override
    default Class<User> getPoClazz() {
        return User.class;
    }

    /**
     * 获取默认的表对象
     *
     * @return 表对象
     */
    @Override
    default Table getDefaultTable() {
        return UserProperties.TABLE;
    }

    /**
     * 获取主键列
     *
     * @return 主键列
     */
    @Override
    default Column getId() {
        return UserProperties.userId;
    }
}
