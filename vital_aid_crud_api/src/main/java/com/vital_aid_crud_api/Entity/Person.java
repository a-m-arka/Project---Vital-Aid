package com.vital_aid_crud_api.Entity;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;



@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@Table(name = "person_table", 
    uniqueConstraints = {
        @UniqueConstraint(columnNames = "personEmail"),
        @UniqueConstraint(columnNames = "personPhone")
    }
)
public abstract class Person {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long personId;

    @Column(length = 200, columnDefinition = "VARCHAR(200) DEFAULT ''")
    private String personName;

    @Column(length = 200, columnDefinition = "VARCHAR(200) DEFAULT ''")
    private String personEmail;

    @Column(length = 20, columnDefinition = "VARCHAR(20) DEFAULT ''")
    private String personPhone;

    @Column(length = 300, columnDefinition = "VARCHAR(300) DEFAULT ''")
    private String loginPassword;

    @Column(length = 20, columnDefinition = "VARCHAR(20) DEFAULT ''")
    private String personRole;


    public Person() {
        
    }



    public Long getPersonId() {
        return this.personId;
    }

    public void setPersonId(Long personId) {
        this.personId = personId;
    }

    public String getPersonName() {
        return this.personName;
    }

    public void setPersonName(String personName) {
        this.personName = personName;
    }

    public String getPersonEmail() {
        return this.personEmail;
    }

    public void setPersonEmail(String personEmail) {
        this.personEmail = personEmail;
    }

    public String getPersonPhone() {
        return this.personPhone;
    }

    public void setPersonPhone(String personPhone) {
        this.personPhone = personPhone;
    }

    public String getLoginPassword() {
        return this.loginPassword;
    }

    public void setLoginPassword(String loginPassword) {
        this.loginPassword = loginPassword;
    }

    public String getPersonRole() {
        return this.personRole;
    }

    public void setPersonRole(String personRole) {
        this.personRole = personRole;
    }
   
    
}
