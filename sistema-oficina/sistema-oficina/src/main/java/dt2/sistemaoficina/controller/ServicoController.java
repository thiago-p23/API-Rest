package dt2.sistemaoficina.controller;

import dt2.sistemaoficina.model.Servico;
import dt2.sistemaoficina.service.ServicoService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/servicos")
@CrossOrigin(origins = "*")
public class ServicoController {
    private final ServicoService service;

    public ServicoController(ServicoService service) { this.service = service; }

    @GetMapping
    public List<Servico> listar() { return service.listarTodos(); }

    @GetMapping("/{id}")
    public Servico buscar(@PathVariable Long id) { return service.buscarPorId(id); }

    @PostMapping
    public ResponseEntity<Servico> criar(@Valid @RequestBody Servico servico) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.salvar(servico));
    }

    @PutMapping("/{id}")
    public Servico atualizar(@PathVariable Long id, @Valid @RequestBody Servico servico) {
        return service.atualizar(id, servico);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluir(@PathVariable Long id) {
        service.excluir(id);
        return ResponseEntity.noContent().build();
    }
}