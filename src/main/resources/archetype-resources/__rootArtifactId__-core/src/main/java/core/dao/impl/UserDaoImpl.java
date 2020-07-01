package ${package}.core.dao.impl;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ${package}.core.dao.UserDao;
import ${package}.db.entity.User;
import ${package}.db.entity.property.UserProperties;
import ${package}.db.mapper.UserMapper;

/**
 * Dao for user
 *
 * @author IBIT TECH
 */
@Repository
public class UserDaoImpl implements UserDao {

    @Autowired
    private UserMapper mapper;

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
        return mapper
                .createQuery()
                .columnPo(User.class)
                .from(UserProperties.TABLE)
                .andWhere(UserProperties.username.eq(username))
                .limit(1)
                .executeQueryOne();
    }

}
