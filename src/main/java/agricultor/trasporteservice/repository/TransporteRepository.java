package agricultor.trasporteservice.repository;

import agricultor.trasporteservice.model.Transporte;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface TransporteRepository extends JpaRepository<Transporte, Integer> {
    boolean existsByPlaca(String placa);
}
