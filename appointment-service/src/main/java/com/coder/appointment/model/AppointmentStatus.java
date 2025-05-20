package com.coder.appointment.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

public enum AppointmentStatus {
    PENDING,
    CONFIRMED,
    REJECTED,
    COMPLETED;

    @JsonCreator
    public static AppointmentStatus fromValue(String value)  {
        if (value == null || value.isBlank()) {
            throw new RuntimeException("Status field is required");
        }
        for (AppointmentStatus status : AppointmentStatus.values()) {
            if (status.name().equalsIgnoreCase(value)) {
                return status;
            }
        }
        throw new RuntimeException("Invalid appointment status value: " + value);
    }

    /**
     * Converts the AppointmentStatus enum to its string representation.
     *
     * @return the string value of the status.
     */
    @JsonValue
    public String toValue() {
        return this.name();
    }
}
