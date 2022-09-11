package com.taoyee.cloud;



import cn.hutool.http.HttpStatus;
import com.jayway.jsonpath.internal.Utils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;


@Component
@Slf4j
public class MyGlobalFilter implements GlobalFilter, Ordered {
    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        String username= exchange.getRequest().getQueryParams().getFirst("username");
        if( Utils.isEmpty(username)){
           log.info("用户名不能为空！！！");
           exchange.getResponse().setStatusCode(org.springframework.http.HttpStatus.valueOf(HttpStatus.HTTP_NOT_ACCEPTABLE));
           return exchange.getResponse().setComplete();
        }
        return chain.filter(exchange);
    }
    @Override
    public int getOrder() {
        return 0;
    }
}
