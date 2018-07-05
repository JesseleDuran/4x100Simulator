package com.jamesye.prototypes.realtimeserver;

import daos.JDBCCarreraDAO;
import models.Carrera;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/carrera")
public class CarreraController {

    @RequestMapping(path = "/create", method = RequestMethod.POST)
    @ResponseBody
    public Carrera create(@RequestBody Carrera carrera) {

        ConfigurableApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
        JDBCCarreraDAO jdbcCarreraDAO = (JDBCCarreraDAO) context.getBean("jdbcCarreraDAO");

        jdbcCarreraDAO.insert(carrera);

        context.close();

        return carrera;
    }
}
