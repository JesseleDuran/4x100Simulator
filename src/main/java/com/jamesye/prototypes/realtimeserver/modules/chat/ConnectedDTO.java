package com.jamesye.prototypes.realtimeserver.modules.chat;

public class ConnectedDTO {

    private int conectados;

    public ConnectedDTO() {
    }

    public ConnectedDTO(int conectados) {
        this.conectados = conectados;
    }

    public int getConectados() {
        return conectados;
    }

    public void setConectados(int conectados) {
        this.conectados = conectados;
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
