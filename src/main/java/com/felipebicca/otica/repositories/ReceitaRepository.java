package com.felipebicca.otica.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.felipebicca.otica.domain.Receita;

@Repository
public interface ReceitaRepository extends JpaRepository<Receita, Integer> {

}