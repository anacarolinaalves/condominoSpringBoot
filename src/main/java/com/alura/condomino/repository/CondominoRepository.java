package com.alura.condomino.repository;

import com.alura.condomino.model.Condomino;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CondominoRepository extends JpaRepository<Condomino, Long> {
    Optional<Condomino> findByEmail(String email);
    Optional<Condomino> findByCpf(String cpf);
}