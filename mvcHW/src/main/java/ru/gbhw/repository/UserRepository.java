package ru.gbhw.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.gbhw.entity.User;

public interface UserRepository extends JpaRepository<User, Long> {
}
