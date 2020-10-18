package com.zyj.gateway;

import org.springframework.cloud.gateway.handler.predicate.AbstractRoutePredicateFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;

import java.time.LocalTime;
import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;

/**
 * @author Yujie_Zhao
 * @ClassName TimeBetweenRoutePredicateFactory
 * @Description 自定义的路由为词工厂
 * @Date 2020/10/9  10:58
 * @Version 1.0
 **/
@Component
public class TimeBetweenRoutePredicateFactory
        extends AbstractRoutePredicateFactory<TimeBetweenConfig>{
    public TimeBetweenRoutePredicateFactory() {
        super(TimeBetweenConfig.class);
    }

    @Override
    public Predicate<ServerWebExchange> apply(TimeBetweenConfig config) {
        LocalTime start = config.getStart();
        LocalTime end=config.getEnd();
        return exchange -> {
            LocalTime now = LocalTime.now();
            return now.isAfter(start) && now.isBefore(end);
        };
    }

    /**
     *
     * @return
     */
    @Override
    public List<String> shortcutFieldOrder() {
        //定义参数顺序
        return Arrays.asList("start","end");
    }
}
