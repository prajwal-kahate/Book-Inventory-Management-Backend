package com.example.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.entity.Publisher;

public interface PublisherDao extends JpaRepository<Publisher, Integer> {

}
