package com.example.security.repository;

import com.example.security.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository // 생략가능한거 알지?
public interface RoleRepository extends JpaRepository<Role, Long> {

    Role findByName(String name); // select * from role where name='USER'

}
