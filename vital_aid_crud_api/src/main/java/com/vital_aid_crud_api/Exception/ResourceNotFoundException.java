package com.vital_aid_crud_api.Exception;

import com.vital_aid_crud_api.EmbeddedClass.CallsAmbulanceId;

public class ResourceNotFoundException extends RuntimeException{
    String resourceName;
    String fieldName;
    Long fieldValue;
    String fieldValueString;
    CallsAmbulanceId callsAmbulanceId;


    public ResourceNotFoundException(String resourceName, String fieldName, Long fieldValue) {
        super(String.format("%s not found with %s : '%s'", resourceName, fieldName, fieldValue));
        this.resourceName = resourceName;
        this.fieldName = fieldName;
        this.fieldValue = fieldValue;
    }
    public ResourceNotFoundException(String resourceName, String fieldName, String fieldValueString) {
        super(String.format("%s not found with %s : '%s'", resourceName, fieldName, fieldValueString));
        this.resourceName = resourceName;
        this.fieldName = fieldName;
        this.fieldValueString = fieldValueString;
    }

    public ResourceNotFoundException(String resourceName, String fieldName, CallsAmbulanceId callsAmbulanceId) {
        super(String.format("%s not found with %s : '%s'", resourceName, fieldName, callsAmbulanceId));
        this.resourceName = resourceName;
        this.fieldName = fieldName;
        this.callsAmbulanceId = callsAmbulanceId;
    }


    public String getResourceName() {
        return this.resourceName;
    }

    public void setResourceName(String resourceName) {
        this.resourceName = resourceName;
    }

    public String getFieldName() {
        return this.fieldName;
    }

    public void setFieldName(String fieldName) {
        this.fieldName = fieldName;
    }

    public Long getFieldValue() {
        return this.fieldValue;
    }

    public void setFieldValue(Long fieldValue) {
        this.fieldValue = fieldValue;
    }

    

}
