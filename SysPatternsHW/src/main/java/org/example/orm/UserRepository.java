package org.example.orm;

import org.example.H2DataBase;

import java.util.Optional;

public class UserRepository {

    private final UserMapper mapper;

    private UnitOfWork unitOfWork;

    public UserRepository() {
        this.mapper = UserMapper.create();
        this.unitOfWork = UnitOfWork.create(mapper);
    }

    public Optional<User> findById(long id) {
        return mapper.findById(id);
    }

    public void beginTransaction() {
        this.unitOfWork = UnitOfWork.create(mapper);
    }

    public void insert(User user) {
        unitOfWork.registerNew(user);
    }

    public void update(User user) {
        unitOfWork.registerUpdate(user);
    }

    public void delete(User user) {
        unitOfWork.registerDelete(user);
    }

    public void commitTransaction() {
        unitOfWork.commit();
    }
}
