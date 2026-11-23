package dt2.sistemaoficina.service;

import dt2.sistemaoficina.model.Servico;
import dt2.sistemaoficina.repository.ServicoRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import java.util.List;

@Service
public class ServicoService {
    private final ServicoRepository repository;

    public ServicoService(ServicoRepository repository) {
        this.repository = repository;
    }

    public List<Servico> listarTodos() { return repository.findAll(); }

    public Servico buscarPorId(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Serviço não encontrado"));
    }

    public Servico salvar(Servico servico) {
        servico.setId(null);
        return repository.save(servico);
    }

    public Servico atualizar(Long id, Servico dados) {
        Servico existente = buscarPorId(id);
        existente.setDescricao(dados.getDescricao());
        existente.setValor(dados.getValor());
        return repository.save(existente);
    }

    public void excluir(Long id) {
        Servico existente = buscarPorId(id);
        repository.delete(existente);
    }
}