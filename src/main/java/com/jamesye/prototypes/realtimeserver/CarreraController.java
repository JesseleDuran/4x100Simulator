package com.jamesye.prototypes.realtimeserver;

import daos.JDBCCarreraDAO;
import models.Carrera;
import models.CarreraCorredor;
import models.CarreraEquipo;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/carrera")
public class CarreraController {

    @RequestMapping(path = "/create", method = RequestMethod.POST,
            consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE,
            produces = {MediaType.APPLICATION_ATOM_XML_VALUE, MediaType.APPLICATION_JSON_VALUE})

    public @ResponseBody int create(Carrera carrera) {

        ConfigurableApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
        JDBCCarreraDAO jdbcCarreraDAO = (JDBCCarreraDAO) context.getBean("jdbcCarreraDAO");

        int id = jdbcCarreraDAO.insertReturningId(carrera);

        context.close();

        return id;
    }

    @RequestMapping(path = "/create/carrera_equipo", method = RequestMethod.POST,
            consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE,
            produces = {MediaType.APPLICATION_ATOM_XML_VALUE, MediaType.APPLICATION_JSON_VALUE})
    public @ResponseBody boolean createCarreraEquipo(CarreraEquipo carreraEquipo) {
        try
        {
            ConfigurableApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
            JDBCCarreraDAO jdbcCarreraDAO = (JDBCCarreraDAO) context.getBean("jdbcCarreraDAO");

            jdbcCarreraDAO.insertCarreraEquipo(carreraEquipo);

            context.close();
            return true;
        }
        catch (Exception e) {
            System.out.println(e.getMessage());
            return false;
        }
    }

    @RequestMapping(path = "/create/carrera_corredor", method = RequestMethod.POST,
            consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE,
            produces = {MediaType.APPLICATION_ATOM_XML_VALUE, MediaType.APPLICATION_JSON_VALUE})
    @ResponseBody
    public boolean createCarreraCorredor(CarreraCorredor carreraCorredor) {
        try
        {
            ConfigurableApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
            JDBCCarreraDAO jdbcCarreraDAO = (JDBCCarreraDAO) context.getBean("jdbcCarreraDAO");

            jdbcCarreraDAO.insertCarreraCorredor(carreraCorredor);

            context.close();
            return true;
        }
        catch (Exception e) {
            System.out.println(e.getMessage());
            return false;
        }
    }
}
