package com.jamesye.prototypes.realtimeserver.modules.chat.DTO;

import java.util.List;
import java.util.Map;

public class listEquiposDTO {

    private List<Map<String, Object>> equipos;

    public listEquiposDTO() {
    }

    public listEquiposDTO(List<Map<String, Object>> equipos) {
        this.equipos = equipos;
    }

    public List<Map<String, Object>> getEquipos() {
        return equipos;
    }

    public void setEquipos(List<Map<String, Object>> equipos) {
        this.equipos = equipos;
    }
}
