package service.impl;

import domain.MovimientoStock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import repository.MovimientoStockRepository;
import service.MovimientoStockService;

public class MovimientoStockServiceImpl implements MovimientoStockService {

    @Autowired //Configuration necesaria para guardar en memoria
    MovimientoStockRepository movimientoStockRepository;

    @Override
    public MovimientoStock crear(MovimientoStock nuevo) {
        movimientoStockRepository.save(nuevo);
        return nuevo;
    }
}
