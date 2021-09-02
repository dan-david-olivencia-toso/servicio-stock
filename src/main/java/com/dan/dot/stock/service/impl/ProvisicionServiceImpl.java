package com.dan.dot.stock.service.impl;

import com.dan.dot.stock.domain.MovimientoStock;
import com.dan.dot.stock.domain.Provision;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.dan.dot.stock.repository.MovimientoStockRepository;
import com.dan.dot.stock.repository.ProvisionRepository;
import com.dan.dot.stock.service.ProvisionService;

@Service
public class ProvisicionServiceImpl implements ProvisionService {

    @Autowired //Configuration necesaria para guardar en memoria
    ProvisionRepository provisionRepository;

    @Override
    public Provision crear(Provision nuevo) {
        provisionRepository.save(nuevo);
        return nuevo;
    }
}
