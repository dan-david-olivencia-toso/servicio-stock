package service.impl;

import domain.MovimientoStock;
import domain.Provision;
import org.springframework.beans.factory.annotation.Autowired;
import repository.MovimientoStockRepository;
import repository.ProvisionRepository;
import service.ProvisionService;

public class ProvisicionServiceImpl implements ProvisionService {

    @Autowired //Configuration necesaria para guardar en memoria
    ProvisionRepository provisionRepository;

    @Override
    public Provision crear(Provision nuevo) {
        provisionRepository.save(nuevo);
        return nuevo;
    }
}
