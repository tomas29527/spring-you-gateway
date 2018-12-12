package com.you.gateway.filter;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.core.Ordered;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

public class ElapsedFilter implements GatewayFilter, Ordered {
    private static final Log log = LogFactory.getLog(GatewayFilter.class);
    private static final String ELAPSED_TIME_BEGIN = "elapsedTimeBegin";


    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        long t = System.currentTimeMillis();
        exchange.getAttributes().put(ELAPSED_TIME_BEGIN, t);
        log.info("====================t:" + t);
        return chain.filter(exchange).then(Mono.fromRunnable(() -> {
                    Long startTime = exchange.getAttribute(ELAPSED_TIME_BEGIN);
                    log.info("====================startTime:" + startTime);
                    if (startTime != null) {
                        long now = System.currentTimeMillis();
                        log.info("====================now:" + now);
                        log.info(exchange.getRequest().getURI().getRawPath() + ": " + (now - startTime) + "ms");
                    }
                })
        );
    }

    public int getOrder() {
        return Ordered.HIGHEST_PRECEDENCE;
    }
}