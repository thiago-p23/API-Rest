package dt2.sistemaoficina.controller;

import dt2.sistemaoficina.model.OrdemServico;
import dt2.sistemaoficina.service.OrdemServicoService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/ordens-servico")
@CrossOrigin(origins = "*")
public class OrdemServicoController {
    private final OrdemServicoService service;

    public OrdemServicoController(OrdemServicoService service) { this.service = service; }

    @GetMapping
    public List<OrdemServico> listar() { return service.listarTodos(); }

    @GetMapping("/{id}")
    public OrdemServico buscar(@PathVariable Long id) { return service.buscarPorId(id); }

    @PostMapping
    public ResponseEntity<OrdemServico> criar(@Valid @RequestBody OrdemServico os) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.salvar(os));
    }

    @PutMapping("/{id}")
    public OrdemServico atualizar(@PathVariable Long id, @Valid @RequestBody OrdemServico os) {
        return service.atualizar(id, os);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluir(@PathVariable Long id) {
        service.excluir(id);
        return ResponseEntity.noContent().build();
    }
}