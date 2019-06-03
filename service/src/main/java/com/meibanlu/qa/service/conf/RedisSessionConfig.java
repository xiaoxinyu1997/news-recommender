package com.meibanlu.qa.service.conf;

import org.springframework.context.annotation.Configuration;
import org.springframework.session.data.redis.config.annotation.web.http.EnableRedisHttpSession;

/**
 * Created by hdj on 2019/4/26.
 */
@Configuration
@EnableRedisHttpSession
public class RedisSessionConfig {
}