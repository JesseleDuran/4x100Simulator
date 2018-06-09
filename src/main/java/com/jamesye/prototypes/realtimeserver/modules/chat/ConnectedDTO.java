package com.jamesye.prototypes.realtimeserver.modules.chat;

import java.util.ArrayList;
import java.util.UUID;

public class ConnectedDTO {

    private int conectados; //Cantidad de personas conectadas
    private ArrayList<UUID> id_conectados; // Sesion de los conectados

    public ConnectedDTO() {
    }

    public ConnectedDTO(int conectados, ArrayList<UUID> id_conectados) {
        this.conectados = conectados;
        this.id_conectados = id_conectados;
    }

    public int getConectados() {
        return conectados;
    }

    public void setConectados(int conectados) {
        this.conectados = conectados;
    }

    public ArrayList<UUID> getId_conectados() {
        return id_conectados;
    }

    public void setId_conectados(ArrayList<UUID> id_conectados) {
        this.id_conectados = id_conectados;
    }

    @Override
    public String toString() {
        return "ConnectedDTO{" +
                "conectados=" + conectados +
                '}';
    }

    public void addConectados() {
        this.conectados++;
    }

    public void deleteConectados() {
        this.conectados--;
    }
}
