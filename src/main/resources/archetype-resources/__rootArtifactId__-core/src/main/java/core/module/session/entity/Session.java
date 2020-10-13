package ${package}.core.module.session.entity;

import tech.ibit.common.collection.CollectionUtils;
import ${package}.core.exception.SessionErrorCode;
import tech.ibit.web.springboot.exception.ApiException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;

/**
 * 定义session
 *
 * @author IBIT程序猿
 */
public class Session implements tech.ibit.web.springboot.session.Session {

    /**
     * Http Session
     */
    private HttpSession httpSession;

    public Session(HttpServletRequest request, boolean create) {
        httpSession = request.getSession(create);
    }

    public Session(HttpServletRequest request) {
        this(request, false);
    }

    @Override
    public String getSessionId() {
        checkHttpSession();
        return httpSession.getId();
    }

    @Override
    public Object getAttribute(String attributeKey) {
        checkHttpSession();
        return httpSession.getAttribute(attributeKey);
    }

    @Override
    public Map<String, Object> getAttributes(String... attributeKeys) {
        checkHttpSession();

        Map<String, Object> attributes = new HashMap<>(0);
        for (String attributeKey : attributeKeys) {
            attributes.put(attributeKey, httpSession.getAttribute(attributeKey));
        }
        return attributes;
    }

    @Override
    public void setAttribute(String attributeKey, Object attributeValue) {
        checkHttpSession();
        httpSession.setAttribute(attributeKey, attributeValue);
    }

    @Override
    public void setAttributes(Map<String, Object> attributes) {
        checkHttpSession();
        if (CollectionUtils.isNotEmpty(attributes)) {
            attributes.forEach(httpSession::setAttribute);
        }
    }

    @Override
    public void invalid() {
        if (null != httpSession) {
            httpSession.invalidate();
        }
    }

    @Override
    public boolean isInvalid() {
        return null == httpSession;
    }

    /**
     * 检查HttpSession
     */
    private void checkHttpSession() {
        if (null == httpSession) {
            throw new ApiException(SessionErrorCode.InvalidSession.MESSAGE);
        }
    }
}
