package ${package}.api.interceptor;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;
import ${package}.core.exception.SessionErrorCode;
import ${package}.core.module.session.constant.SessionKey;
import ${package}.core.module.session.service.SessionService;
import tech.ibit.web.springboot.context.WebContext;
import tech.ibit.web.springboot.exception.ApiException;
import tech.ibit.web.springboot.session.Session;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Method;
import java.util.UUID;

/**
 * 登陆拦截器
 *
 * @author IBIT TECH
 */
@Slf4j
public class LoginInterceptor extends HandlerInterceptorAdapter {

    private SessionService sessionService;

    public LoginInterceptor(SessionService sessionService) {
        this.sessionService = sessionService;
    }

    @Override
    public boolean preHandle(HttpServletRequest request
            , HttpServletResponse response, Object handler) {

        // 初始化
        WebContext.init();

        // 设置开始时间
        WebContext.setBeginTime(System.currentTimeMillis());

        // 设置请求ID
        WebContext.setRequestId(UUID.randomUUID().toString());

        // 会话
        Session session = sessionService.getSession(request);

        if (handler.getClass().isAssignableFrom(HandlerMethod.class)) {
            Method method = ((HandlerMethod) handler).getMethod();

            NeedLogin needLogin = method.getAnnotation(NeedLogin.class);
            //不需要登录
            if (null != needLogin && !needLogin.value()) {
                return true;
            }

            if (session.isInvalid() || null == session.getAttribute(SessionKey.userId)) {
                throw new ApiException(SessionErrorCode.InvalidSession.MESSAGE);
            }
        }
        return true;
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response
            , Object handler, Exception ex) throws Exception {
        super.afterCompletion(request, response, handler, ex);
        try {
            WebContext.destroy();
        } catch (Exception e) {
            log.warn(e.getMessage(), e);
            //ignore
        }
    }
}
