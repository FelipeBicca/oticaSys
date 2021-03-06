package com.felipebicca.otica.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.felipebicca.otica.domain.Telefone;

@Repository
public interface TelefoneRepository extends JpaRepository<Telefone, Integer> {

}