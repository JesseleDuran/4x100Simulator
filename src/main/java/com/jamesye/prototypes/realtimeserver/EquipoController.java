package com.jamesye.prototypes.realtimeserver;

import daos.JDBCEquipoDAO;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/equipo")
public class EquipoController {

    @RequestMapping(value = "/all", method = RequestMethod.GET)
    @ResponseBody
    public List<Map<String, Object>> findAll() {

        ConfigurableApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");

        JDBCEquipoDAO jdbcEquipoDAO = (JDBCEquipoDAO) context.getBean("jdbcEquipoDAO");

        List<Map<String, Object>> equipos = jdbcEquipoDAO.findAll();
        System.out.println(equipos);

        context.close();

        return equipos;
    }
}
