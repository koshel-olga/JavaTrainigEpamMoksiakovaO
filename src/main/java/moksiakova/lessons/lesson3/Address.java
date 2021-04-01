package main.java.moksiakova.lessons.lesson3;

import org.jetbrains.annotations.NotNull;

import java.util.Objects;

public class Address {
    private String city;
    private String street;
    private String numberHouse;
    private Integer flat;

    public Address(@NotNull String city,@NotNull String street,@NotNull String numberHouse, Integer flat) {
        this.city = city;
        this.street = street;
        this.numberHouse = numberHouse;
        this.flat = flat;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Address address = (Address) o;
        return city.equals(address.city) && street.equals(address.street)
                && numberHouse.equals(address.numberHouse) && Objects.equals(flat, address.flat);
    }

    @Override
    public int hashCode() {
        return Objects.hash(city, street, numberHouse, flat);
    }

    @Override
    public String toString() {
        String stringAddress = this.city+" "+this.street+" "+this.numberHouse;
        if (this.flat != null) {
            stringAddress += " "+this.flat;
        }
        return stringAddress;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getNumberHouse() {
        return numberHouse;
    }

    public void setNumberHouse(String numberHouse) {
        this.numberHouse = numberHouse;
    }

    public Integer getFlat() {
        return flat;
    }

    public void setFlat(Integer flat) {
        this.flat = flat;
    }
}
