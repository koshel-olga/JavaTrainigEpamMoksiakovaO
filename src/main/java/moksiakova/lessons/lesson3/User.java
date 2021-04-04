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
        if (userRoleMap.containsKey(userRole)) { this.userRole = userRole; }
        else {
            System.out.println("Invalid role "+userRole+". Set role=\"USER\" to "+name);
            this.userRole = "USER";
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
