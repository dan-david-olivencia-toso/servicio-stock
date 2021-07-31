package repository;

import domain.MovimientoStock;
import frsf.isi.dan.InMemoryRepository;
import org.springframework.stereotype.Repository;

@Repository
public class MovimientoStockRepository extends InMemoryRepository<MovimientoStock> {

    @Override
    public Integer getId(MovimientoStock movimientoStock) {
        return movimientoStock.getId();
    }

    @Override
    public void setId(MovimientoStock movimientoStock, Integer integer) {
        movimientoStock.setId(integer);
    }
}
