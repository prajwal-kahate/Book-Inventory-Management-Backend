package com.example.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.entity.PermRole;

@Repository
public interface PermRoleDao extends JpaRepository<PermRole, Integer> {

}
