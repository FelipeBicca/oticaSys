package com.felipebicca.otica.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.felipebicca.otica.domain.Consulta;

@Repository
public interface ConsultaRepository extends JpaRepository<Consulta, Integer> {

}