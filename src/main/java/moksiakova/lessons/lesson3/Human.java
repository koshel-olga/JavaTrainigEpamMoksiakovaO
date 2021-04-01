package main.java.moksiakova.lessons.lesson3;

import java.util.Objects;

/** Human class. */
public class Human {
    private String name;
    private Integer age;
    private Address address;

    public Human() {
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Human)) return false;
        Human human = (Human) o;
        return name.equals(human.name) && age.equals(human.age) && Objects.equals(address, human.address);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, age, address);
    }

    @Override
    public String toString() {
        return this.name+" "+this.age+" "+this.address.toString();
    }

    public String getName() {
        return name;
    }

    public Integer getAge() {
        return age;
    }

    public Address getAddress() {
        return address;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAge(Integer age) {
        if (age < 0) {
            System.out.printf("Invalid age: %d", age);
            return;
        }
        this.age = age;
    }

    public void setAddress(Address address) {
        this.address = address;
    }
}
