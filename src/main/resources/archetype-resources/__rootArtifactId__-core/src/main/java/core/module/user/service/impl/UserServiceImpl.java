package ${package}.core.module.user.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ${package}.core.dao.UserDao;
import ${package}.core.module.user.service.UserService;
import ${package}.db.entity.User;

/**
 * 用户service实现
 *
 * @author IBIT TECH
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;

    /**
     * 通过用户id获取用户
     *
     * @param userId 用户id
     * @return 用户
     */
    @Override
    public User get(Integer userId) {
        return userDao.getById(userId);
    }
}
