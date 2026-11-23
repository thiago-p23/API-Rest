package dt2.sistemaoficina.repository;
import dt2.sistemaoficina.model.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;
public interface ClienteRepository extends JpaRepository<Cliente, Long> {}