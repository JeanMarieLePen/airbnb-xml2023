package com.xml.mainapp.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.xml.mainapp.model.users.Guest;

public interface GuestRepository extends JpaRepository<Guest, Long>{

}
