package ${package}.db.mapper;

import ${package}.db.entity.User;
import tech.ibit.mybatis.SingleIdMapper;

/**
 * Mapper for user
 *
 * @author IBIT TECH
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

}
