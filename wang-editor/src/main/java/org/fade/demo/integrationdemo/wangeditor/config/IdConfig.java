package org.fade.demo.integrationdemo.wangeditor.config;

import cn.hutool.core.lang.Snowflake;
import cn.hutool.core.util.IdUtil;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * id配置
 *
 * @author fade
 * @date 2021/11/02
 */
@Configuration
public class IdConfig {

    @Bean
    public Snowflake snowflake() {
        return IdUtil.getSnowflake();
    }

}
