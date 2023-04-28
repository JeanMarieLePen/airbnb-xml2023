package com.xml.mainapp.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.xml.mainapp.model.users.Host;

public interface HostRepository extends JpaRepository<Host, Long>{

}
