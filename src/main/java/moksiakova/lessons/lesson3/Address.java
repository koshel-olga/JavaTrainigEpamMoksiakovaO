package main.java.moksiakova.lessons.lesson3;

import java.util.Objects;

/** Human address. */
public class Address {
    private final String city;
    private final String street;
    private final String numberHouse;
    private final Integer flat;

    /** Constructor. */
    public Address(String city, String street, String numberHouse, Integer flat) {
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
}
