package com.meibanlu.qa.analysis.conf;

import com.google.gson.Gson;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * 请求日志记录
 *
 * @author leigang
 * @date 2017/3/23
 */
@Aspect
@Order(1)
@Component
public class RequestLog {

    private final Logger log = LoggerFactory.getLogger(RequestLog.class);

    @Pointcut("execution(public * com.meibanlu.qa.analysis.controller.*.*(..))")
    public void log() {
    }

    @Around(value = "log()")
    public Object around(ProceedingJoinPoint joinPoint) throws Throwable {
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();
        log.info("uri={},m={},ip={},args={},agent={}",
                request.getRequestURI(),
                request.getMethod(),
                ipFromRequest(request),
                mapToString(request.getParameterMap()),
                request.getHeader("User-Agent"));
        Object object = joinPoint.proceed();
        try {
            log.info("return: " + new Gson().toJson(object));
        } catch (Exception ignore) {
        }
        return object;
    }

    private String ipFromRequest(HttpServletRequest request) {
        return request.getHeader("X-Real-IP") == null ? request.getRemoteAddr() : request.getHeader("X-Real-IP");
    }

    /**
     * 请求中的参数写成string
     *
     * @param map 传入map
     * @return 字符串
     */
    private String mapToString(Map<String, String[]> map) {
        StringBuilder r = new StringBuilder();
        r.append("{");
        for (String key : map.keySet()) {
            String[] values = map.get(key);
            if (values == null || values.length == 0) {
                continue;
            }
            if (values.length == 1) {
                r.append(key).append(":").append(values[0]).append(",");
                continue;
            }
            r.append(key).append(":[");
            for (int i = 0; i < values.length; i++) {
                //会有多余的逗号
                r.append(values[i]);
                if (i < values.length - 1) {
                    r.append(",");
                }
            }
            //会有多余的逗号
            r.append("],");
        }
        r.append("}");
        return r.toString();
    }
}
