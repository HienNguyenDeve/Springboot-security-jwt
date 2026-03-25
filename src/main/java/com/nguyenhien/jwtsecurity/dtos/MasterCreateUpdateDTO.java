package com.nguyenhien.jwtsecurity.dtos;

import jakarta.validation.constraints.NotNull;

public class MasterCreateUpdateDTO {
    @NotNull(message = "Active is required")
    private boolean isActive;

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean isActive) {
        this.isActive = isActive;
    }
}
