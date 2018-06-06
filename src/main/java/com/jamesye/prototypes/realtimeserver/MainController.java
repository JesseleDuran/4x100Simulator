package com.jamesye.prototypes.realtimeserver;

import models.Carrera;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.sql.Time;
import java.util.Date;
import java.util.concurrent.atomic.AtomicLong;


@Controller
public class MainController {

    @RequestMapping(value = "/client")
    @ResponseBody
    public String client() {
        return "index";
    }
}
