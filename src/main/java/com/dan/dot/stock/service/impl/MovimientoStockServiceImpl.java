package com.dan.dot.stock.service.impl;

import com.dan.dot.stock.domain.MovimientoStock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.dan.dot.stock.repository.MovimientoStockRepository;
import com.dan.dot.stock.service.MovimientoStockService;

@Service
public class MovimientoStockServiceImpl implements MovimientoStockService {

    @Autowired //Configuration necesaria para guardar en memoria
    MovimientoStockRepository movimientoStockRepository;

    @Override
    public MovimientoStock crear(MovimientoStock nuevo) {
        movimientoStockRepository.save(nuevo);
        return nuevo;
    }
}
