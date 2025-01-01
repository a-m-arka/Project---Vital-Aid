package com.vital_aid_crud_api.EmbeddedClass;

import java.io.Serializable;

import jakarta.persistence.Embeddable;

@Embeddable
public class CallsAmbulanceId implements Serializable {
    private Long personId;
    private String ambulanceNumPlate;

    public CallsAmbulanceId() {
    }

    public CallsAmbulanceId(Long personId, String ambulanceNumPlate) {
        this.personId = personId;
        this.ambulanceNumPlate = ambulanceNumPlate;
    }


    public Long getPersonId() {
        return this.personId;
    }

    public void setPersonId(Long personId) {
        this.personId = personId;
    }

    public String getAmbulanceNumPlate() {
        return this.ambulanceNumPlate;
    }

    public void setAmbulanceNumPlate(String ambulanceNumPlate) {
        this.ambulanceNumPlate = ambulanceNumPlate;
    }
    

    // equals and hashCode
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((personId == null) ? 0 : personId.hashCode());
        result = prime * result + ((ambulanceNumPlate == null) ? 0 : ambulanceNumPlate.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        CallsAmbulanceId other = (CallsAmbulanceId) obj;
        if (personId == null) {
            if (other.personId != null)
                return false;
        } else if (!personId.equals(other.personId))
            return false;
        if (ambulanceNumPlate == null) {
            if (other.ambulanceNumPlate != null)
                return false;
        } else if (!ambulanceNumPlate.equals(other.ambulanceNumPlate))
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "CallsAmbulanceId [personId=" + personId + ", ambulanceNumPlate=" + ambulanceNumPlate + "]";
    }
}
