package br.com.fuzus.model;

import java.util.HashMap;
import java.util.Map;

public class Company {

    private String name;
    private String cnpj;
    private String email;

    private Map<String, String> place = new HashMap<>();

    public Company(){}

    public Company(String name, String cnpj, String email) {
        this.name = name;
        this.cnpj = cnpj;
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCnpj() {
        return cnpj;
    }

    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Map<String, String> getPlace() {
        return place;
    }

}
