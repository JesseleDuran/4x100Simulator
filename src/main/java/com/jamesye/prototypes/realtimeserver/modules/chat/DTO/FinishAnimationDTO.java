package com.jamesye.prototypes.realtimeserver.modules.chat.DTO;

public class FinishAnimationDTO {

    private String animation;

    public FinishAnimationDTO() {
    }

    public FinishAnimationDTO(String animation) {
        this.animation = animation;
    }

    public String getAnimation() {
        return animation;
    }

    public void setAnimation(String animation) {
        this.animation = animation;
    }
}
