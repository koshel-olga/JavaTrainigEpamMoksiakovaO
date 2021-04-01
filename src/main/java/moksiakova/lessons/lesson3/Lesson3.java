package main.java.moksiakova.lessons.lesson3;

import java.util.*;
import java.util.stream.Collectors;

/** Class for describe method from homework 3. */
public class Lesson3 {
    /** Constructor. */
    public Lesson3() {}

    /** Method for find duplicate in Collection.
     * @param collection collection of {@link Human}.
     * @return duplicate raw from collection.
     * */
    public Set<Human> findDuplicate(Collection<Human> collection) {
        Set<Human> setHumans = new HashSet<>();
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

    /** Print letter for user.
     * @param user {@link User}.*/
    public void hiUser(User user) {
        System.out.printf(
                "Hello, %s, with role %s - %s\n",
                user.getName(),
                user.getUserRole(),
                user.getUserRoleDescription()
        );
    }

    /** Sort HashMap by keys.
     * @param collection <Integer, {@link User}>.
     * @return sorted collection by keys. */
    public TreeMap<Integer, User> sortByKey(HashMap<Integer, User> collection) {
        TreeMap<Integer, User> treeCollection = new TreeMap<>(collection);
        return treeCollection;
    }

    /** Sort HashMap by values.
     * @param collection <Integer, {@link User}>.
     * @return sorted collection by values. */
    public LinkedHashMap<Integer, User> sortByValue(HashMap<Integer, User> collection) {
        List<User> listValue = new ArrayList<>(collection.values());
        LinkedHashMap<Integer, User> sortedCollection = new LinkedHashMap<>();
        listValue.sort(new UserNameComparator());
        for(User userValue: listValue) {
            for(Map.Entry<Integer,User> entry: collection.entrySet()) {
                if ( userValue.equals(entry.getValue()) ){
                    sortedCollection.put(entry.getKey(), entry.getValue());
                    collection.remove(entry.getKey(), entry.getValue());
                    break;
                }
            }
        }
        return sortedCollection;
    }
}
