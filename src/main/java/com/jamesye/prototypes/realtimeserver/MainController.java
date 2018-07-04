package com.jamesye.prototypes.realtimeserver;

import daos.JDBCCarreraDAO;
import daos.JDBCCorredorDAO;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.Map;


@Controller
public class MainController {

    @RequestMapping(value = "/client")
    @ResponseBody
    public String client() {
        return "index";
    }

    @RequestMapping(value = "/historial/carrera", method = RequestMethod.GET)
    @ResponseBody
    public List<Map<String, Object>> findAllCarreras() {

        ConfigurableApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");

        JDBCCarreraDAO jdbcCarreraDAO = (JDBCCarreraDAO) context.getBean("jdbcCarreraDAO");

        List<Map<String, Object>> carreras = jdbcCarreraDAO.findAll();
        System.out.println(carreras);

        context.close();

        return carreras;
    }

    @RequestMapping(value = "/corredores", method = RequestMethod.GET)
    @ResponseBody
    public List<Map<String, Object>> findAllCorredores() {

        ConfigurableApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");

        JDBCCorredorDAO jdbcCorredorDAO = (JDBCCorredorDAO) context.getBean("jdbcCorredorDAO");

        List<Map<String, Object>> corredores = jdbcCorredorDAO.findAll();
        System.out.println(corredores);

        context.close();

        return corredores;
    }
}
