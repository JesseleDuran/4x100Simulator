package com.jamesye.prototypes.realtimeserver;

import com.corundumstudio.socketio.Configuration;
import com.corundumstudio.socketio.SocketIOServer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan("com.jamesye.prototypes.realtimeserver")
public class RealTimeServerApplication {

    @Value("${rt.server.host}")
    private String host;

    @Value("${rt.server.port}")
    private Integer port;

    @Bean
    public SocketIOServer socketIOServer() {
        Configuration config = new Configuration();
        config.setHostname(host);
        config.setPort(port);
        return new SocketIOServer(config);
    }

    public static void main(String[] args) {


        SpringApplication.run(RealTimeServerApplication.class, args);
    }
}
