package ${package}.core.module.user.service;

import ${package}.db.entity.User;

/**
 * 用户service
 *
 * @author IBIT程序猿
 *
 */
public interface UserService {

    /**
     * 通过用户名查询用户
     *
     * @param username 用户名
     * @return 用户
     */
    User getByUsername(String username);

}
