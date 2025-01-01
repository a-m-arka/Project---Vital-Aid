package com.vital_aid_crud_api.Entity;

import java.io.Serializable;
import java.time.LocalTime;


import com.fasterxml.jackson.annotation.JsonFormat;
import com.vital_aid_crud_api.Validation.ValidConsultTimes;

import jakarta.persistence.Embeddable;

@Embeddable
@ValidConsultTimes
public class ConsultingTimes implements Serializable{

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "HH:mm")
    private LocalTime startTime;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "HH:mm")
    private LocalTime endTime;

    public ConsultingTimes() {
    }


    public LocalTime getStartTime() {
        return this.startTime;
    }

    public void setStartTime(LocalTime startTime) {
        this.startTime = startTime;
    }

    public LocalTime getEndTime() {
        return this.endTime;
    }

    public void setEndTime(LocalTime endTime) {
        this.endTime = endTime;
    }

}
