package org.example;

import org.example.orm.User;
import org.example.orm.UserRepository;

public class Main {
    public static void main(String[] args) {
        User user1 = new User(1, "user1", "123");
        User user2 = new User(2, "user2", "123");
        User user3 = new User(3, "user3", "123");
        User userChange = new User(3, "UserIsChange", "321");
        UserRepository repository = new UserRepository();

        repository.beginTransaction();
        repository.insert(user1);
        repository.insert(user2);
        repository.insert(user3);
        repository.commitTransaction();

        repository.beginTransaction();
        repository.delete(user2);
        repository.commitTransaction();

        repository.beginTransaction();
        repository.update(userChange);
        repository.commitTransaction();

        repository.beginTransaction();
        repository.delete(user3);
        repository.delete(userChange);
        repository.commitTransaction();
    }
}