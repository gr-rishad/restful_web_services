package com.rishad.rest.restfulwebservices.user;

import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

@Component
public class UserDaoService {

    private static List<User> users = new ArrayList<>();
    private static Integer userCount = 3;

    static {
        users.add(new User(1, "Adam", new Date()));
        users.add(new User(2, "Eve", new Date()));
        users.add(new User(3, "Jack", new Date()));

    }

    public List<User> findAll() {
        return users;
    }

    public User save(User user) {
        if (user.getId() == null) {
            user.setId(++userCount);
        }
        users.add(user);
        return user;
    }

    public User findOne(int id) {
        for (User user : users) {
            if (user.getId() == id) {
                return user;
            }
        }
        return null;
    }

    public User deleteById(int id) {
        // get the iterator
        Iterator<User> userIterator = users.iterator();
        // hasNext --> return true if the collection has more element to iterate
        while (userIterator.hasNext()) {
            // next() --> returns the next element until hasNext() method returns true
            User user = userIterator.next();
            if (user.getId() == id) {
                // remove() --> remove the current element in the collection
                userIterator.remove();
                return user;
            }
        }
        return null;
    }
}
