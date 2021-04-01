package main.java.moksiakova.lessons.lesson3;

import org.jetbrains.annotations.NotNull;

import java.util.Objects;

public class Human {
    private String name;
    private Integer age;
    private Address address;

    public Human(@NotNull String name, @NotNull Integer age, Address address) throws Exception {
        if (age < 0 ) { throw new Exception("Age is not correct"); }
        this.name = name;
        this.age = age;
        this.address = address;
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
}
