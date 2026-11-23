package dt2.sistemaoficina.service;

import dt2.sistemaoficina.model.Mecanico;
import dt2.sistemaoficina.repository.MecanicoRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import java.util.List;

@Service
public class MecanicoService {
    private final MecanicoRepository repository;

    public MecanicoService(MecanicoRepository repository) {
        this.repository = repository;
    }

    public List<Mecanico> listarTodos() { return repository.findAll(); }

    public Mecanico buscarPorId(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Mecânico não encontrado"));
    }

    public Mecanico salvar(Mecanico mecanico) {
        mecanico.setId(null);
        return repository.save(mecanico);
    }

    public Mecanico atualizar(Long id, Mecanico dados) {
        Mecanico existente = buscarPorId(id);
        existente.setNome(dados.getNome());
        existente.setEspecialidade(dados.getEspecialidade());
        return repository.save(existente);
    }

    public void excluir(Long id) {
        Mecanico existente = buscarPorId(id);
        repository.delete(existente);
    }
}