package service;

import domain.MovimientoStock;
import domain.Provision;
import org.springframework.stereotype.Service;

@Service
public interface ProvisionService {

    Provision crear(Provision nuevo);

}
