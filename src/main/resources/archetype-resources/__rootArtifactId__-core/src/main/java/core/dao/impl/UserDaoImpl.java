package ${package}.core.dao.impl;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ${package}.core.dao.UserDao;
import ${package}.db.entity.User;
import ${package}.db.entity.property.UserProperties;
import ${package}.db.mapper.UserMapper;
import tech.ibit.mybatis.MapperDaoUtils;
import tech.ibit.mybatis.template.dao.impl.SingleIdDaoImpl;
import tech.ibit.mybatis.template.mapper.Mapper;
import tech.ibit.sqlbuilder.CriteriaItemMaker;
import tech.ibit.sqlbuilder.Sql;

/**
 * Dao for user
 *
 * @author IBIT TECH
 */
@Repository
public class UserDaoImpl extends SingleIdDaoImpl<User, Integer> implements UserDao {

    @Autowired
    private UserMapper mapper;

    @Override
    public Mapper<User> getMapper() {
        return mapper;
    }

    @Override
    public Class<User> getPoClazz() {
        return User.class;
    }


    /**
     * 通过用户名查询用户
     *
     * @param username 用户名
     * @return 用户
     */
    @Override
    public User getByUsername(String username) {
        if (StringUtils.isBlank(username)) {
            return null;
        }
        Sql sql = new Sql()
                .selectPo(getPoClazz()).from(UserProperties.TABLE)
                .andWhere(CriteriaItemMaker.equalsTo(UserProperties.username, username))
                .limit(1);
        return MapperDaoUtils.executeQueryOne(mapper, sql.getSqlParams());
    }

}
