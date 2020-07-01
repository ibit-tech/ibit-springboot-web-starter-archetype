package ${package}.core.dao;

import ${package}.db.entity.User;

/**
 * Dao for user
 *
 * @author IBIT TECH
 */
public interface UserDao {

    /**
     * 通过用户名查询用户
     *
     * @param username 用户名
     * @return 用户
     */
    User getByUsername(String username);

}
