package br.com.fuzus.model;

public class Place {
    private String publicPlace;
    private String district;
    private String cep;
    private String state;
    private String city;

    public Place(){}

    public Place(String state, String city) {
        this.state = state;
        this.city = city;
    }

    public Place(String publicPlace, String district, String cep, String state, String city) {
        this.publicPlace = publicPlace;
        this.district = district;
        this.cep = cep;
        this.state = state;
        this.city = city;
    }

    public String getPublicPlace() {
        return publicPlace;
    }

    public void setPublicPlace(String publicPlace) {
        this.publicPlace = publicPlace;
    }

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }
}
