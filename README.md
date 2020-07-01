## 快速新建项目

```

$ mvn -X archetype:generate                                  \
    -DarchetypeArtifactId=ibit-springboot-web-starter-archetype        \
    -DarchetypeVersion=1.3 \
    -DarchetypeGroupId=tech.ibit \
    -DarchetypeRepository=https://oss.sonatype.org/content/repositories/snapshots
$ mvn install
```

## 定义web-api规范说明

### 工程结构（以demo为例）

#### module说明

| 模块名称| 说明 |
| --- | --- |
| demo-db | db基础模块（entity、mapper等) |
| demo-core | 核心业务代码（dao、service等） |
| demo-api | Controller、拦截器等 |  
| log-config | logback相关配置，不直接写到api中，用来动态修改日志 |
| demo-code-generator | 代码生成器（entity、mapper、dao生成）|

### 代码结构说明

说明：

* `Gen`指`ibit-mybatis-generator`

#### db模块

```
|-- src 
    |-- main
         |-- java java代码
              |-- tech.ibit.demo.db 
                    |-- entity 实体对象（Gen生成）
                           |-- po 自定义PO（字段是对应entity子集，需要包含主键）
                           |-- property 数据库表对象（Gen生成）
                           |-- type entity中定义的枚举类，需要实现接口`tech.ibit.mybatis.CommonEnum`
                    |-- mapper mapper对象（Gen生成）
         |-- resources 资源目录                        
              |-- tech/ibit/demo/db/mapper mapper对应的xml文件，（Gen生成）  
              |-- db/migrations 数据库变更sql文件（集成flyway）    
``` 

#### core模块

```
|-- src 
    |-- main
         |-- java java代码
              |-- tech.ibit.demo.core 
                    |-- common 公共模块
                           |-- config 配置
                           |-- env 环境
                           |-- log 日志
                           |-- ...
                    |-- dao 构造层   
                    |-- exception 定义公共异常（系统错误码、错误码前缀等）
                    |-- module 业务层分模块
                           |-- enterprise 以企业为例子，企业模块
                                 |-- dto 定义service流转对象 
                                 |-- param service层查询参数（Optional）
                                 |-- service service层
                                        |-- impl service实现
                           |-- session 会话模块 
                           |-- user 用户模块
                           |-- ...         
```

#### api模块

```
|-- src 
    |-- main
         |-- java java代码
              |-- tech.ibit.demo.api 
                    |-- config 配置（如session，cookie等）
                    |-- interceptor 拦截器
                    |-- module Controller模块
                           |-- user 用户模块
                           |-- ...
         |-- resources 资源目录
```

#### code-generator模块

```
按照demo指定生成的数据库信息，生成目录即可生成代码
```

### 返回格式定义

#### 使用注解`@CustomResponse`，不使用则直接返回对象

假设Controller方法返回参数为data，则自动包装为（tech.ibit.web.springboot.response.Response对象）:

```
{
  "code": 返回码,
  "data": data,
  "message": "返回信息描述",
  "requestId": "请求id",
  "timestamp": 系统时间戳,
  "successful": 是否执行成功
}
```

eg：登录

```
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
``` 

返回json:

```
{
  "code": 200,
  "message": "S_OK",
  "data": {
    "userId": 1,
    "username": "ibit-tech",
    "nickName": "IBIT-TECH",
    "gender": 1,
    "mobile": "188",
    "email": "sa@ibit.tech",
    "wechat": "ibit-tech",
    "enterprise": {
      "enterpriseId": 1,
      "name": "IBIT科技"
    }
  },
  "timestamp": 1582785104956,
  "requestId": "5d5ebcdd-2d32-4e86-8082-7d85fe8ec468",
  "successful": true
}
```

#### 使用tech.ibit.web.springboot.response.Response自定义构造完整返回对象

```
    /**
     * 获取实例
     *
     * @param code      编码
     * @param message   消息
     * @param data      数据
     * @param throwable 异常
     * @param <T>       data数据类型
     * @return 实例
     */
    public static <T> Response getInstance(int code, String message, T data, Throwable throwable) {
        return new Response<>(code, message, data, throwable);
    }


    /**
     * 获取实例
     *
     * @return 实例
     */
    public static Response getInstance() {
        return new Response<>();
    }

    /**
     * 获取实例
     *
     * @param data 数据
     * @param <T>  data数据类型
     * @return 实例
     */
    public static <T> Response<T> getInstance(T data) {
        return new Response<>(data);
    }


    /**
     * 获取实例
     *
     * @param code    编码
     * @param message 消息
     * @param data    数据
     * @param <T>     data数据类型
     * @return 实例
     */
    public static <T> Response<T> getInstance(int code, String message, T data) {
        return new Response<>(code, message, data);
    }


    /**
     * 获取实例
     *
     * @param code    编码
     * @param message 消息
     * @param <T>     data数据类型
     * @return 实例
     */
    public static <T> Response<T> getInstance(int code, String message) {
        return new Response<>(code, message);
    }
```

### 异常说明

统一异常对象`tech.ibit.web.springboot.exception.ApiException`

构造函数

```
    /**
     * 构造函数
     *
     * @param errorCodeMsg 错误码与错误信息
     */
    public ApiException(String errorCodeMsg) {
        this(errorCodeMsg, null);
    }

    /**
     * 构造函数
     *
     * @param errorCodeMsg 错误码与错误信息
     * @param data         错误数据
     */
    public ApiException(String errorCodeMsg, Object data) {
        super(errorCodeMsg);
        this.data = data;

        String[] str = errorCodeMsg.split(ErrorCode.SPLIT);
        if (2 == str.length) {
            this.code = Integer.parseInt(str[0]);
            this.msg = str[1].trim();
        } else {
            this.code = ErrorCode.CODE;
            this.msg = ErrorCode.MSG;
        }
    }
```

其中errorCodeMsg格式为

```
返回码 + "__" + 错误信息
```

ps： 错误码定义参考`core`中定义的公共异常


### 其他说明

#### 接口文档，使用swagger2，只会在本地、测试、开发环境启动

```
@Configuration
@EnableSwagger2
@Profile({EnvConstant.ENV_DEV, EnvConstant.ENV_TEST, EnvConstant.ENV_LOCAL})
public class SwaggerConfig implements WebMvcConfigurer {
     // 省略配置
}
```

说明：

```
ibit-springboot-web-starter-archetype版本低于1.3:
文档地址：http://127.0.0.1:8080/swagger-ui.html

ibit-springboot-web-starter-archetype 1.3+（包含1.3）:
文档地址：http://127.0.0.1:8080/doc.html
```



### 用法参考

* [ibit-mybatis](https://github.com/ibit-tech/ibit-mybatis)
* [sql-builder](https://github.com/ibit-tech/sql-builder)
* [structlog4j](https://github.com/ibit-tech/structlog4j)
* [ibit-mybatis-generator](https://github.com/ibit-tech/ibit-mybatis-generator)