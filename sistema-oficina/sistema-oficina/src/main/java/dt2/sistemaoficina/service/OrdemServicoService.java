package dt2.sistemaoficina.service;

import dt2.sistemaoficina.model.OrdemServico;
import dt2.sistemaoficina.repository.*;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import java.util.List;

@Service
public class OrdemServicoService {
    private final OrdemServicoRepository repository;
    private final VeiculoRepository veiculoRepo;
    private final MecanicoRepository mecanicoRepo;
    private final ServicoRepository servicoRepo;

    public OrdemServicoService(OrdemServicoRepository repository, VeiculoRepository veiculoRepo,
                               MecanicoRepository mecanicoRepo, ServicoRepository servicoRepo) {
        this.repository = repository;
        this.veiculoRepo = veiculoRepo;
        this.mecanicoRepo = mecanicoRepo;
        this.servicoRepo = servicoRepo;
    }

    public List<OrdemServico> listarTodos() { return repository.findAll(); }

    public OrdemServico buscarPorId(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Ordem de serviço não encontrada"));
    }

    private void validarRelacionamentos(OrdemServico os) {
        if (!veiculoRepo.existsById(os.getVeiculoId()))
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Veículo informado não existe");
        if (!mecanicoRepo.existsById(os.getMecanicoId()))
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Mecânico informado não existe");
        if (!servicoRepo.existsById(os.getServicoId()))
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Serviço informado não existe");
    }

    public OrdemServico salvar(OrdemServico os) {
        validarRelacionamentos(os);
        os.setId(null);
        return repository.save(os);
    }

    public OrdemServico atualizar(Long id, OrdemServico dados) {
        OrdemServico existente = buscarPorId(id);
        validarRelacionamentos(dados);
        existente.setVeiculoId(dados.getVeiculoId());
        existente.setMecanicoId(dados.getMecanicoId());
        existente.setServicoId(dados.getServicoId());
        existente.setStatus(dados.getStatus());
        existente.setDataAbertura(dados.getDataAbertura());
        return repository.save(existente);
    }

    public void excluir(Long id) {
        OrdemServico existente = buscarPorId(id);
        repository.delete(existente);
    }
}