package dt2.sistemaoficina.service;

import dt2.sistemaoficina.model.Cliente;
import dt2.sistemaoficina.repository.ClienteRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import java.util.List;

@Service
public class ClienteService {
    private final ClienteRepository repository;

    public ClienteService(ClienteRepository repository) {
        this.repository = repository;
    }

    public List<Cliente> listarTodos() { return repository.findAll(); }

    public Cliente buscarPorId(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Cliente não encontrado"));
    }

    public Cliente salvar(Cliente cliente) {
        cliente.setId(null);
        return repository.save(cliente);
    }

    public Cliente atualizar(Long id, Cliente dados) {
        Cliente existente = buscarPorId(id);
        existente.setNome(dados.getNome());
        existente.setTelefone(dados.getTelefone());
        existente.setCpf(dados.getCpf());
        return repository.save(existente);
    }

    public void excluir(Long id) {
        Cliente existente = buscarPorId(id);
        repository.delete(existente);
    }
}