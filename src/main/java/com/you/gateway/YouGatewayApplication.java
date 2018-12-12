package com.you.gateway;

import com.you.gateway.filter.ElapsedGatewayFilterFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
@EnableDiscoveryClient
public class YouGatewayApplication {

    public static void main(String[] args) {
        SpringApplication.run(YouGatewayApplication.class, args);
    }


    //    @Bean
//    public RouteLocator customerRouteLocator(RouteLocatorBuilder builder) {
//        return builder.routes()
//                .route(r -> r.path("/trace/**")
//                        .filters((f)->
//                             f.stripPrefix(1)
//                                    //.filter(new ElapsedFilter())
//                                    .addResponseHeader("X-Response-Default-Foo", "Default-Bar")
//                        )
//                        .uri("lb://TRACE")
//                        .order(0)
//                        .id("trace_customer")
//                )
//                .build();
//    }
//    @Bean
//    public TokenFilter tokenFilter(){
//        return new TokenFilter();
//    }
    @Bean("elapsedGateway")
    public ElapsedGatewayFilterFactory elapsedGatewayFilterFactory() {
        return new ElapsedGatewayFilterFactory();
    }

}
