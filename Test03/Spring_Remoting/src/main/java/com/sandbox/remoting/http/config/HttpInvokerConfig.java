package com.sandbox.remoting.http.config;

import com.sandbox.remoting.http.services.SingerService;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.remoting.httpinvoker.HttpInvokerServiceExporter;

@Configuration
@AllArgsConstructor
public class HttpInvokerConfig {
    SingerService singerService;

    @Bean(name = "/httpInvoker/singerService")
    public HttpInvokerServiceExporter httpInvokerServiceExporter() {
        // RPC over HTTP and RPC are deprecated, use REST or SOAP based approach instead
        HttpInvokerServiceExporter invokerService = new HttpInvokerServiceExporter();
        invokerService.setService(singerService);
        invokerService.setServiceInterface(SingerService.class);
        return invokerService;
    }

}