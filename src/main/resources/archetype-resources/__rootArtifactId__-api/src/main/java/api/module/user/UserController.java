package ${package}.api.module.user;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.apache.commons.lang.StringUtils;
import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ${package}.api.interceptor.NeedLogin;
import ${package}.core.exception.UserErrorCode;
import ${package}.core.module.user.dto.UserLoginDto;
import ${package}.core.module.user.param.LoginParam;
import ${package}.core.module.user.service.UserLoginService;
import tech.ibit.web.springboot.response.annotation.CustomResponse;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

/**
 * 用户Controller
 *
 * @author IBIT程序猿
 */
@RestController
@RequestMapping("{version}/api/user")
@Api(tags = "用户相关")
public class UserController {

    @Autowired
    private UserLoginService userLoginService;

    /**
     * 登陆
     *
     * @param request  请求
     * @param username 用户名
     * @param password 密码
     * @return 登陆对象
     */
    @PostMapping("login")
    @CustomResponse
    @NeedLogin(value = false)
    @ApiOperation(value = "登录", produces = APPLICATION_JSON_VALUE)
    @ApiResponses({
            @ApiResponse(code = 200, message = "S_OK", responseContainer = "data", response = UserLoginDto.class),
            @ApiResponse(code = UserErrorCode.UserNameOrPwdError.CODE, message = UserErrorCode.UserNameOrPwdError.MESSAGE)
    })
    public UserLoginDto login(HttpServletRequest request,
                              @RequestParam(defaultValue = "")
                              @NotEmpty(message = UserErrorCode.UserNameOrPwdError.MESSAGE) String username,
                              @RequestParam(defaultValue = "")
                              @NotEmpty(message = UserErrorCode.UserNameOrPwdError.MESSAGE) String password) {
        return userLoginService.login(request, new LoginParam(StringUtils.trimToEmpty(username), StringUtils.trimToEmpty(password)));
    }

    /**
     * 获取登陆信息
     *
     * @param request 请求
     * @return 登陆对象
     */
    @CustomResponse
    @GetMapping("login")
    @ApiOperation(value = "获取登陆信息", produces = APPLICATION_JSON_VALUE)
    @ApiResponses({
            @ApiResponse(code = 200, message = "S_OK", responseContainer = "data", response = UserLoginDto.class),
    })
    public UserLoginDto getLoginInfo(HttpServletRequest request) {
        return userLoginService.getLoginInfo(request);
    }

    /**
     * 注销登陆
     *
     * @param request 请求
     * @return 注销结果
     */
    @CustomResponse
    @DeleteMapping("login")
    @ApiOperation(value = "注销登录", produces = APPLICATION_JSON_VALUE)
    @ApiResponses({
            @ApiResponse(code = 200, message = "S_OK", responseContainer = "data", response = Map.class),
    })
    public void logout(HttpServletRequest request) {
        userLoginService.logout(request);
    }

}
