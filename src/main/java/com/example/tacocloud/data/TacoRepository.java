package com.example.tacocloud.data;

import com.example.tacocloud.Ingredient;

import com.example.tacocloud.Taco;
import org.springframework.data.jpa.repository.JpaRepository;


public interface TacoRepository extends JpaRepository<Taco, Long> {
}
