package ${package}.core.module.user.service;

import ${package}.db.entity.User;

/**
 * 用户service
 *
 * @author IBIT TECH
 *
 */
public interface UserService {

    /**
     * 通过用户id获取用户
     *
     * @param userId 用户id
     * @return 用户
     */
    User get(Integer userId);

}
