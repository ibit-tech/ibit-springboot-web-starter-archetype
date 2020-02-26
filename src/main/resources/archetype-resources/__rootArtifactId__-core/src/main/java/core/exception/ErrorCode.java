package ${package}.core.exception;

/**
 * 定义错误码（分割符、类型前缀）
 *
 * @author IBIT TECH
 *
 */
public interface ErrorCode extends tech.ibit.web.springboot.exception.ErrorCode {

    /**
     * 微信用户相关错误码前缀
     */
    int USER_CODE_PREFIX = 1001_0000;

    /**
     * Session相关错误码前缀
     */
    int SESSION_CODE_PREFIX = 1002_0000;


}
