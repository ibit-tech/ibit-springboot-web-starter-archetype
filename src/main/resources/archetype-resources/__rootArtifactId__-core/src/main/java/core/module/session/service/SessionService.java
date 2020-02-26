package ${package}.core.module.session.service;


import tech.ibit.web.springboot.session.Session;

import javax.servlet.http.HttpServletRequest;

/**
 * Session Service
 *
 * @author IBIT TECH
 */
public interface SessionService {

    /**
     * 获取session
     *
     * @param request 请求
     * @return session
     */
    Session getSession(HttpServletRequest request);

    /**
     * 获取session
     *
     * @param request 请求
     * @param create  不存在则创建
     * @return session
     */
    Session getSession(HttpServletRequest request, boolean create);
}
