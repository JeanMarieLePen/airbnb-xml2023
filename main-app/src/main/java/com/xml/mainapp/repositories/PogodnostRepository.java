package com.xml.mainapp.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.xml.mainapp.model.data.Pogodnost;

public interface PogodnostRepository extends JpaRepository<Pogodnost, Long> {

}
