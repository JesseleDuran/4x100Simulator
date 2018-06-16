package com.jamesye.prototypes.realtimeserver;

import com.corundumstudio.socketio.Configuration;
import com.corundumstudio.socketio.SocketIOServer;
import daos.JDBCEquipoDAO;
import models.Equipo;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.List;

@SpringBootApplication
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

        ConfigurableApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");

        JDBCEquipoDAO jdbcEquipoDAO = (JDBCEquipoDAO) context.getBean("jdbcEquipoDAO");
        /*Equipo equipo3 = new Equipo("Panama");
        jdbcEquipoDAO.insert(equipo3);*/

        List<Equipo> equipos = jdbcEquipoDAO.findAll();
        System.out.println(equipos);

        String name = jdbcEquipoDAO.findNameById(3);
        System.out.println(name);

        Equipo equipo4 = jdbcEquipoDAO.findById(3);
        System.out.println(equipo4);


        System.out.println(" FindAll : " + jdbcEquipoDAO.findAll());
        jdbcEquipoDAO.insertBatch2("UPDATE equipo SET nombre ='Peru' WHERE id = 3");

        List<Equipo> equipo1 = jdbcEquipoDAO.findAll();
        System.out.println("Updated column nombre of table: " + equipo1);

        System.out.println(" FindAll : " + jdbcEquipoDAO.findAll());

        context.close();

        SpringApplication.run(RealTimeServerApplication.class, args);
    }
}
