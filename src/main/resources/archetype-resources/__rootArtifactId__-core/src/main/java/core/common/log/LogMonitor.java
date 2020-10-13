package ${package}.core.common.log;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;
import tech.ibit.common.http.HttpUtils;
import ${package}.core.module.session.constant.SessionKey;
import tech.ibit.structlog4j.Logger;
import tech.ibit.structlog4j.StructLoggerFactory;
import tech.ibit.web.springboot.context.WebContext;
import tech.ibit.web.springboot.log.AccessLogItem;
import tech.ibit.web.springboot.log.DetailAccessLogItem;
import tech.ibit.web.springboot.response.Response;
import tech.ibit.web.springboot.session.Session;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Arrays;
import java.util.Map;

/**
 * 日志监控
 *
 * @author IBIT程序猿
 */
@Aspect
@Component
public class LogMonitor {

    private Logger logger = StructLoggerFactory.getLogger(LogMonitor.class);

    private static ObjectMapper objectMapper = new ObjectMapper();

    private static final String[] IGNORE_KEYS = new String[]{"password", "oldPassword", "newPassword", "data", "imageData"};

    @AfterReturning(pointcut = "anyRequestMapping() && anyMethod()", returning = "result")
    public void logAfterForResult(Object result) {
        logAfterResult(result);

    }

    @AfterReturning(pointcut = "anyRequestMapping() && anyMethod() && anyVoidMethod()")
    public void logAfterForResult() {
        logAfterResult(null);

    }

    @Pointcut("@annotation(org.springframework.web.bind.annotation.GetMapping) "
            + "|| @annotation(org.springframework.web.bind.annotation.PutMapping) "
            + "|| @annotation(org.springframework.web.bind.annotation.PostMapping) "
            + "|| @annotation(org.springframework.web.bind.annotation.DeleteMapping) "
            + "|| @annotation(org.springframework.web.bind.annotation.RequestMapping)")
    protected void anyRequestMapping() {
    }


    @AfterReturning(pointcut = "anyExceptionHandler()", returning = "result")
    public void logAfterHandlerException(Response result) {
        logAfterResult(result);
    }

    @Pointcut("execution(public tech.ibit.web.springboot.response.Response *(..)) " +
            "&& @annotation(org.springframework.web.bind.annotation.ExceptionHandler)")
    protected void anyExceptionHandler() {
    }


    @Pointcut("within(${package}..*) || within(org.springframework.boot.autoconfigure.web..*)")
    private void anyMethod() {
    }

    @Pointcut("within(${package}..*) && execution(public void *(..))")
    private void anyVoidMethod() {
    }

    private void logAfterResult(Object result) {
        try {
            doOpLog(result);
        } catch (Exception e) {
            //ignore
            e.printStackTrace();
        }
    }


    private void doOpLog(Object result) {
        Response r = result instanceof Response ? (Response) result : Response.getInstance(result);
        Throwable throwable;
        throwable = r.getThrowable();
        AccessLogItem logItem = getLogItem("WEB_API", IGNORE_KEYS, logger.isDebugEnabled(), r);

        if (r.isSuccessful()) {
            logger.info("S_OK", logItem);
        } else {
            if (null != throwable) {
                logger.error("S_ERROR", logItem, throwable);
            } else {
                logger.error("S_ERROR", logItem);
            }
        }
    }

    private AccessLogItem getLogItem(String platform, String[] ignoreKeys, boolean needDetail, Response r) {
        AccessLogItem logItem = new AccessLogItem(platform);

        HttpServletRequest request = WebContext.getRequest();

        if (null != request) {
            Map<String, String> params = HttpUtils.getParams(request);

            logItem.setMethod(request.getMethod());
            logItem.setUri(request.getRequestURI());
            logItem.setIp(HttpUtils.getRealIp(request));
            logItem.setParam(HttpUtils.getQueryString(params, Arrays.asList(ignoreKeys)));

            Session session = WebContext.getSession();
            Integer userId = null == session ? null : (Integer) session.getAttribute(SessionKey.userId);
            logItem.setLoginId(null == userId ? null : String.valueOf(userId));

            logItem.setSid(null == session ? null : session.getSessionId());

            long beginTime = WebContext.getBeginTime();
            logItem.setUsedTime(-1 == beginTime ? -1 : (System.currentTimeMillis() - beginTime));

            String requestId = WebContext.getRequestId();
            logItem.setRequestId(requestId);
        }

        logItem.setCode(r.getCode());
        logItem.setMessage(r.getMessage());

        if (needDetail) {
            DetailAccessLogItem detailLogItem = new DetailAccessLogItem(logItem);
            if (null != request) {
                detailLogItem.setRequestHeaders(HttpUtils.getHeaders(request));
            }

            try {
                detailLogItem.setResult(objectMapper.writeValueAsString(r.getData()));
            } catch (JsonProcessingException e) {
                logger.error("json 序列化异常");
            }

            HttpServletResponse response = WebContext.getResponse();
            if (null != response) {
                detailLogItem.setResponseHeaders(HttpUtils.getHeaders(response));
            }
            logItem = detailLogItem;
        }
        return logItem;
    }
}
