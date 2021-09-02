package repository;

import domain.MovimientoStock;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MovimientoStockRepository extends JpaRepository<MovimientoStock, Integer> {
    public Optional<MovimientoStock> getById(MovimientoStock movimientoStock);
    public boolean existsById(Integer id);
    public MovimientoStock save(MovimientoStock movimientoStock);

}
