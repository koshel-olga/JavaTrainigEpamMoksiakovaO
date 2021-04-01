package main.java.moksiakova.lessons.lesson3;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

public class Lesson3 {

    public Lesson3() {}

    public Set<Human> findDuplicate(Collection<Human> collection) {
        Set<Human> setHumans = new HashSet<Human>();
        return collection.stream()
                .filter(human -> {
                    if (!setHumans.contains(human)) {
                        setHumans.add(human);
                    } else {
                        return true;
                    }
                    return false;
                })
                .collect(Collectors.toSet());
    }

    public void hiUser(User user) {
        System.out.printf(
                "Hello, %s, with role %s - %s\n",
                user.getName(),
                user.getUserRole(),
                user.getUserRoleDescription()
        );
    }
}
