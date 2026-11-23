package dt2.sistemaoficina.controller;

import dt2.sistemaoficina.model.Veiculo;
import dt2.sistemaoficina.service.VeiculoService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/veiculos")
@CrossOrigin(origins = "*")
public class VeiculoController {
    private final VeiculoService service;

    public VeiculoController(VeiculoService service) { this.service = service; }

    @GetMapping
    public List<Veiculo> listar() { return service.listarTodos(); }

    @GetMapping("/{id}")
    public Veiculo buscar(@PathVariable Long id) { return service.buscarPorId(id); }

    @PostMapping
    public ResponseEntity<Veiculo> criar(@Valid @RequestBody Veiculo veiculo) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.salvar(veiculo));
    }

    @PutMapping("/{id}")
    public Veiculo atualizar(@PathVariable Long id, @Valid @RequestBody Veiculo veiculo) {
        return service.atualizar(id, veiculo);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluir(@PathVariable Long id) {
        service.excluir(id);
        return ResponseEntity.noContent().build();
    }
}