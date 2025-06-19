package com.alura.condomino.controller;

import com.alura.condomino.model.Condomino;
import com.alura.condomino.repository.CondominoRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/condomino")
public class CondominoController {

    @Autowired
    private CondominoRepository repository;

    @PostMapping
    public ResponseEntity<Object> cadastrar(@RequestBody @Valid Condomino novoCondominio) {
        novoCondominio.setId(null);

        if (repository.findByEmail(novoCondominio.getEmail()).isPresent()) {
            return ResponseEntity.unprocessableEntity().body("E-mail já cadastrado");
        }

        if (repository.findByCpf(novoCondominio.getCpf()).isPresent()) {
            return ResponseEntity.unprocessableEntity().body("CPF já cadastrado");
        }

        Condomino condominoSalvo = repository.save(novoCondominio);

        return ResponseEntity.created(null).body(condominoSalvo);
    }

}