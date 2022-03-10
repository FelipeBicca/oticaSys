package com.felipebicca.otica.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.felipebicca.otica.domain.Dioptria;

@Repository
public interface DioptriaRepository extends JpaRepository<Dioptria, Integer> {

}