package dt2.sistemaoficina.service;

import dt2.sistemaoficina.model.Veiculo;
import dt2.sistemaoficina.repository.ClienteRepository;
import dt2.sistemaoficina.repository.VeiculoRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import java.util.List;

@Service
public class VeiculoService {
    private final VeiculoRepository repository;
    private final ClienteRepository clienteRepository;

    public VeiculoService(VeiculoRepository repository, ClienteRepository clienteRepository) {
        this.repository = repository;
        this.clienteRepository = clienteRepository;
    }

    public List<Veiculo> listarTodos() { return repository.findAll(); }

    public Veiculo buscarPorId(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Veículo não encontrado"));
    }

    private void validarCliente(Long clienteId) {
        if (!clienteRepository.existsById(clienteId)) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Cliente informado não existe");
        }
    }

    public Veiculo salvar(Veiculo veiculo) {
        validarCliente(veiculo.getClienteId());
        veiculo.setId(null);
        return repository.save(veiculo);
    }

    public Veiculo atualizar(Long id, Veiculo dados) {
        Veiculo existente = buscarPorId(id);
        validarCliente(dados.getClienteId());
        existente.setPlaca(dados.getPlaca());
        existente.setModelo(dados.getModelo());
        existente.setAno(dados.getAno());
        existente.setClienteId(dados.getClienteId());
        return repository.save(existente);
    }

    public void excluir(Long id) {
        Veiculo existente = buscarPorId(id);
        repository.delete(existente);
    }
}