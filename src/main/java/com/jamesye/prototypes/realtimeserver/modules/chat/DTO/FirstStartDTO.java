package com.jamesye.prototypes.realtimeserver.modules.chat.DTO;

public class FirstStartDTO {

    private boolean start;

    public FirstStartDTO() {
    }

    public FirstStartDTO(boolean start) {
        this.start = start;
    }

    public boolean isStart() {
        return start;
    }

    public void setStart(boolean start) {
        this.start = start;
    }
}
