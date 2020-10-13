package ${package}.core.exception;

/**
 * 用户错误码
 *
 * @author IBIT程序猿
 */
public interface UserErrorCode extends ErrorCode {

    /**
     * 前缀
     */
    int CP = USER_CODE_PREFIX;

    /**
     * 用户名或者密码错误
     */
    interface UserNameOrPwdError {
        int CODE = CP + 1;
        String MESSAGE = CODE + SPLIT + "用户名或者密码错误";
    }
}
