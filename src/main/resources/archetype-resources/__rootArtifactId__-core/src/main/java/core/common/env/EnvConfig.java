package ${package}.core.common.env;

import lombok.*;

import java.util.HashMap;
import java.util.Map;

/**
 * 环境配置
 *
 * @author IBIT程序猿
 */
@Data
@Builder
public class EnvConfig {

    /**
     * 环境名称
     */
    private String name;

    /**
     * 是否开启debug日志
     */
    private boolean debug;

    /**
     * 异常日志是否格式化输出
     */
    private boolean transStackTrace;

    @Getter(AccessLevel.NONE)
    @Setter(AccessLevel.NONE)
    private static Map<String, EnvConfig> map;

    static {
        map = new HashMap<>();
        EnvConfig envConfig = EnvConfig.builder().name(EnvConstant.ENV_DEV)
                .debug(true)
                .transStackTrace(false)
                .build();
        map.put(EnvConstant.ENV_DEV, envConfig);

        envConfig = EnvConfig.builder().name(EnvConstant.ENV_TEST)
                .debug(true)
                .transStackTrace(false)
                .build();
        map.put(EnvConstant.ENV_TEST, envConfig);

        envConfig = EnvConfig.builder().name(EnvConstant.ENV_DEMO)
                .debug(true)
                .transStackTrace(false)
                .build();
        map.put(EnvConstant.ENV_DEMO, envConfig);

        envConfig = EnvConfig.builder().name(EnvConstant.ENV_PROD)
                .debug(false)
                .transStackTrace(true)
                .build();
        map.put(EnvConstant.ENV_PROD, envConfig);
    }


    /**
     * 通过环境名称获取环境配置
     *
     * @param env 环境名称
     * @return 环境配置
     */
    public static EnvConfig getEnvConfig(String env) {
        EnvConfig envConfig = map.get(env);
        if (envConfig == null) {
            envConfig = map.get(EnvConstant.ENV_DEV);
        }
        return envConfig;
    }
}
