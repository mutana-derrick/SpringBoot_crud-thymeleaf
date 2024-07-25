package com.springboot.CRUD.services;

import com.springboot.CRUD.models.Computers;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ComputerRepository extends JpaRepository<Computers,Integer>{
}
