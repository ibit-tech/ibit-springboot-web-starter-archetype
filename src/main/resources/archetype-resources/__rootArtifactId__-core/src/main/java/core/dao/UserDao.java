package ${package}.core.dao;

import ${package}.db.entity.User;
import tech.ibit.mybatis.template.dao.SingleIdDao;

/**
 * Dao for user
 *
 * @author IBIT TECH
 */
public interface UserDao extends SingleIdDao<User, Integer> {

    /**
     * 通过用户名查询用户
     *
     * @param username 用户名
     * @return 用户
     */
    User getByUsername(String username);
}
