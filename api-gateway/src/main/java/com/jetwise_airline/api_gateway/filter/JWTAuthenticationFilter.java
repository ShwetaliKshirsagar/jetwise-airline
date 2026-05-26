package com.jetwise_airline.api_gateway.filter;


import com.jetwise_airline.jwt_common.JWTService;
import lombok.RequiredArgsConstructor;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

    @Component
    @RequiredArgsConstructor
    public class JWTAuthenticationFilter implements GlobalFilter {

        private final JWTService jwtService;

        @Override
        public Mono<Void> filter(ServerWebExchange exchange,
                                 GatewayFilterChain chain) {

            String path = exchange.getRequest().getURI().getPath();

            // Public endpoints
            if (path.contains("/login")
                    || path.contains("/register")
                    || path.contains("/swagger-ui")
                    || path.contains("/swagger-config")
                    || path.contains("/webjars")
                    || path.contains("/v3/api-docs")){

                return chain.filter(exchange);
            }

            String authHeader =
                    exchange.getRequest().getHeaders()
                            .getFirst(HttpHeaders.AUTHORIZATION);

            if (authHeader == null || !authHeader.startsWith("Bearer ")) {

                exchange.getResponse()
                        .setStatusCode(HttpStatus.UNAUTHORIZED);

                return exchange.getResponse().setComplete();
            }

            String token = authHeader.substring(7);
            System.out.println("token: "+ token);
            try {

                if (!jwtService.isTokenValid(token)){
                    System.out.println("istokenValid: "+ jwtService.isTokenValid(token));
                    exchange.getResponse()
                            .setStatusCode(HttpStatus.UNAUTHORIZED);

                    return exchange.getResponse().setComplete();
                }

            } catch (Exception ex) {

                exchange.getResponse()
                        .setStatusCode(HttpStatus.UNAUTHORIZED);

                return exchange.getResponse().setComplete();
            }

            return chain.filter(exchange);
        }

    }