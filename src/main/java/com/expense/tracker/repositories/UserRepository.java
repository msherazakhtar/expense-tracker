package com.expense.tracker.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.expense.tracker.models.UsersORM;

public interface UserRepository extends JpaRepository<UsersORM, Long> {

}
