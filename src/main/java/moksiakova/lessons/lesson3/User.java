package main.java.moksiakova.lessons.lesson3;

import java.util.Collections;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Класс пользователей с ролями. */
public class User {
    /**
     * ФИО пользователя. */
    private final String name;
    /**
     * Роль пользователя. */
    private String userRole;

    private static final Map<String, String> userRoleMap;

    static {
        Map<String, String> map = new ConcurrentHashMap<>();
        map.put("ADMIN","Администратор системы. Может делать все, что захочет.");
        map.put("USER","Пользователь. Обычный пользователь.");
        map.put("MODERATOR","Модератор. Проверяет материал на соответствие требованиям.");
        userRoleMap = Collections.unmodifiableMap(map);
    }

    public User(String name, String userRole) {
        this.name = name;
        try {
            this.userRole = userRoleMap.get(userRole);
        } catch (Exception e) {
            System.out.printf("Invalid role %d", userRole);
        }
    }

    public String getName() {
        return name;
    }

    public String getUserRole() {
        return userRole;
    }

    public String getUserRoleDescription() {
        return userRoleMap.get(this.userRole);
    }

}
