package dt2.sistemaoficina.controller;

import dt2.sistemaoficina.model.Mecanico;
import dt2.sistemaoficina.service.MecanicoService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/mecanicos")
@CrossOrigin(origins = "*")
public class MecanicoController {
    private final MecanicoService service;

    public MecanicoController(MecanicoService service) { this.service = service; }

    @GetMapping
    public List<Mecanico> listar() { return service.listarTodos(); }

    @GetMapping("/{id}")
    public Mecanico buscar(@PathVariable Long id) { return service.buscarPorId(id); }

    @PostMapping
    public ResponseEntity<Mecanico> criar(@Valid @RequestBody Mecanico mecanico) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.salvar(mecanico));
    }

    @PutMapping("/{id}")
    public Mecanico atualizar(@PathVariable Long id, @Valid @RequestBody Mecanico mecanico) {
        return service.atualizar(id, mecanico);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluir(@PathVariable Long id) {
        service.excluir(id);
        return ResponseEntity.noContent().build();
    }
}