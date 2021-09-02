package com.dan.dot.stock.service;

import com.dan.dot.stock.domain.Provision;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

//Notación para indicar que es un servicio
@Service
//Asegura que toda la data requerida este segura hasta que la transacción termine
//Recomiendo leer acerca de esta notación (es un mundo completo jeje)
@Transactional
public interface ProvisionService {

    Provision crear(Provision nuevo);

}
