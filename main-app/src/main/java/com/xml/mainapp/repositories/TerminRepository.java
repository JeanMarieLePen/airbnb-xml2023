package com.xml.mainapp.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.xml.mainapp.model.data.Termin;

public interface TerminRepository extends JpaRepository<Termin, Long> {

}
