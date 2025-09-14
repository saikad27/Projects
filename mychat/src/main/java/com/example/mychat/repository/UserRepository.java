package com.example.mychat.repository;

import com.example.mychat.model.UserDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;


@Repository
public interface UserRepository extends JpaRepository<UserDetail,Long> {
    public Optional<UserDetail> findByUsername(String username);
}
