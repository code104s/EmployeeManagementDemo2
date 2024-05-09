package com.offlink.quanlynhanvien.DAO;

import com.offlink.quanlynhanvien.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserRepository extends JpaRepository<User, Long> {

    // find user by username
    User findByUsername(String username);
}
