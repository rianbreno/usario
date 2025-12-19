package com.rianbreno.apredendospring.infrastructure.repository;

import com.rianbreno.apredendospring.infrastructure.entity.Telefone;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TelefoneRepository extends JpaRepository <Telefone, Long> {
}
