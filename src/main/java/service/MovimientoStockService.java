package service;

import domain.MovimientoStock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public interface MovimientoStockService {

    MovimientoStock crear(MovimientoStock nuevo);


}
