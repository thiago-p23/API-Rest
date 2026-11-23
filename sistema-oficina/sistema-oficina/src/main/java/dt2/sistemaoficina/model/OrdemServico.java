package dt2.sistemaoficina.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

@Entity
public class OrdemServico {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull(message = "O ID do veículo é obrigatório")
    private Long veiculoId;

    @NotNull(message = "O ID do mecânico é obrigatório")
    private Long mecanicoId;

    @NotNull(message = "O ID do serviço é obrigatório")
    private Long servicoId;

    @NotBlank(message = "O status é obrigatório")
    private String status;

    @NotBlank(message = "A data de abertura é obrigatória")
    private String dataAbertura;

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public Long getVeiculoId() { return veiculoId; }
    public void setVeiculoId(Long veiculoId) { this.veiculoId = veiculoId; }
    public Long getMecanicoId() { return mecanicoId; }
    public void setMecanicoId(Long mecanicoId) { this.mecanicoId = mecanicoId; }
    public Long getServicoId() { return servicoId; }
    public void setServicoId(Long servicoId) { this.servicoId = servicoId; }
    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
    public String getDataAbertura() { return dataAbertura; }
    public void setDataAbertura(String dataAbertura) { this.dataAbertura = dataAbertura; }
}