package com.alura.condomino.controller;
import com.alura.condomino.exception.RecursoNaoEncontrado;
import com.alura.condomino.exception.RegraDeNegocioException;
import com.alura.condomino.model.Condomino;
import com.alura.condomino.repository.CondominoRepository;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/condomino/v2")
@Tag(name = "Condôminos", description = "API para gerenciamento de condôminos")
public class CondominoV2Controller {

    @Autowired
    private CondominoRepository repository;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(summary = "Cadastrar novo condômino", description = "Cadastra um novo condômino no sistema")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Condômino cadastrado com sucesso",
                    content = @Content(schema = @Schema(implementation = Condomino.class))),
            @ApiResponse(responseCode = "422", description = "Dados inválidos ou duplicados",
                    content = @Content)
    })
    public Condomino cadastrar(@RequestBody @Valid Condomino novoCondomino) {
        novoCondomino.setId(null);

        if (repository.findByEmail(novoCondomino.getEmail()).isPresent()) {
            throw new RegraDeNegocioException("E-mail já cadastrado");
        }

        if (repository.findByCpf(novoCondomino.getCpf()).isPresent()) {
            throw new RegraDeNegocioException("CPF já cadastrado");
        }

        Condomino condominoSalvo = repository.save(novoCondomino);

        return condominoSalvo;
    }

    @GetMapping("/{id}")
    public Condomino consultarPorId(@PathVariable Long id) {
        Optional<Condomino> condomino = repository.findById(id);

        if (condomino.isEmpty()) {
            throw new RecursoNaoEncontrado("Condômino não encontrado");
        }

        return condomino.get();

        //return repository.findById(id).orElseThrow(() -> new RecursoNaoEncontrado("Condômino não encontrado"));
    }

    @GetMapping
    public List<Condomino> consultarTodos() {
        return repository.findAll();
    }

    @GetMapping("/consultaPorCPF")
    public Condomino consultarComFiltro(@RequestParam("cpf") String cpf) {
        Optional<Condomino> condomino = repository.findByCpf(cpf);

        if (condomino.isEmpty()) {
            throw new RecursoNaoEncontrado("Condômino não encontrado");
        }

        return condomino.get();

        //return repository.findByCpf(cpf).orElseThrow(() -> new RecursoNaoEncontrado("Condômino não encontrado"));
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void excluir(@PathVariable("id") Long id) {
        repository.deleteById(id);
    }

    @PutMapping("/{id}")
    public Condomino atualizar(@PathVariable("id") Long id,
                               @RequestBody Condomino condomino) {
        condomino.setId(id);

        Optional<Condomino> condominoEmail = repository.findByEmail(condomino.getEmail());

        if (condominoEmail.isPresent() && !condominoEmail.get().getId().equals(id)) {
            throw new RegraDeNegocioException("E-mail já cadastrado");
        }

        Optional<Condomino> condominoCpf = repository.findByCpf(condomino.getCpf());

        if (condominoCpf.isPresent() && !condominoCpf.get().getId().equals(id)) {
            throw new RegraDeNegocioException("CPF já cadastrado");
        }

        return repository.save(condomino);
    }

    @ExceptionHandler(RegraDeNegocioException.class)
    @ResponseStatus(HttpStatus.UNPROCESSABLE_ENTITY)
    public Map<String, String> handleRegraDeNegocioException(RegraDeNegocioException ex) {
        return Map.of("erro", ex.getMessage());
    }

    @ExceptionHandler(RecursoNaoEncontrado.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public Map<String, String> handleRecursoNaoEncontrado(RecursoNaoEncontrado ex) {
        return Map.of("erro", ex.getMessage());
    }

}