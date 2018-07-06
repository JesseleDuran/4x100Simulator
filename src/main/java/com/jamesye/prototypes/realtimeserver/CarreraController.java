package com.jamesye.prototypes.realtimeserver;

import daos.JDBCCarreraDAO;
import models.Carrera;
import models.CarreraEquipo;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/carrera")
public class CarreraController {

    @RequestMapping(path = "/create", method = RequestMethod.POST)
    @ResponseBody
    public int create(@RequestBody Carrera carrera) {

        ConfigurableApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
        JDBCCarreraDAO jdbcCarreraDAO = (JDBCCarreraDAO) context.getBean("jdbcCarreraDAO");

        int id = jdbcCarreraDAO.insertReturningId(carrera);

        context.close();

        return id;
    }

    @RequestMapping(path = "/create/carrera_equipo", method = RequestMethod.POST)
    @ResponseBody
    public boolean create(@RequestBody List<CarreraEquipo> carreraEquipos) {
        try
        {
                ConfigurableApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
                JDBCCarreraDAO jdbcCarreraDAO = (JDBCCarreraDAO) context.getBean("jdbcCarreraDAO");

                jdbcCarreraDAO.insertBatchCarreraEquipo(carreraEquipos);

                context.close();
                return true;
        }
        catch (Exception e) {
            System.out.println(e.getMessage());
            return false;
        }
    }
}
