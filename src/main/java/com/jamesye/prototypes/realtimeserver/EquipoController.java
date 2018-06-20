package controllers;

import daos.JDBCEquipoDAO;
import models.Equipo;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/equipo")
public class EquipoController {

    @RequestMapping(value = "/all", method = RequestMethod.GET)
    @ResponseBody
    public List<Equipo> findAll() {

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

        return equipos;
    }
}
