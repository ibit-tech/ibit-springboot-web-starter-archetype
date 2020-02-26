package ${package}.core.exception;

/**
 * Session相关错误码
 *
 * @author IBIT TECH
 */
public interface SessionErrorCode extends ErrorCode {

    /**
     * 前缀
     */
    int CP = SESSION_CODE_PREFIX;

    /**
     * Session无效
     */
    interface InvalidSession {
        int CODE = CP + 1;
        String MESSAGE = CODE + SPLIT + "Session无效";
    }
}
