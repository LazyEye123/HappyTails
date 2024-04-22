package com.happytails.springserver.repository;

import com.happytails.springserver.models.Users;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsersRepository extends JpaRepository<Users, Long> {
    // поиск пользователя по имени в БД авторизации
    Users findByUsername(String userName);
}
