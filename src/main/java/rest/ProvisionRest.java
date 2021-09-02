package rest;

import domain.Provision;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import service.ProvisionService;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.OptionalInt;
import java.util.stream.IntStream;

@CrossOrigin(maxAge = 36000)
@RestController
@RequestMapping("/api/provision")
@Api(value = "ProvisionRest", description = "Permite gestionar la información relacionada a las órdenes de provisión generadas por la empresa para cumplir con los pedidos de sus clientes")
public class ProvisionRest {

    @Autowired
    ProvisionService provisionService;

    private static final List<Provision> listaProvisiones = new ArrayList<>();
    private static Integer ID_GEN = 1;

    @CrossOrigin(maxAge = 86400)
    @GetMapping
    public ResponseEntity<List<Provision>> todos() {return ResponseEntity.ok(listaProvisiones); }

    @CrossOrigin(maxAge = 86400)
    @GetMapping(path = "/{id}")
    @ApiOperation(value = "Busca una orden de provisión por id")
    public ResponseEntity<Provision> provisionPorId(@PathVariable Integer id){
        Optional<Provision> p = listaProvisiones
                .stream()
                .filter(provision -> provision.getId().equals(id))
                .findFirst();
        return ResponseEntity.of(p);
    }

    @CrossOrigin(maxAge = 86400)
    @PostMapping
    public ResponseEntity<?> crear(@RequestBody Provision nuevo){
        Provision p = null;
        try {
            p = this.provisionService.crear(nuevo);
        } catch (Exception e1) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e1.getMessage());
        }
        return ResponseEntity.ok(p);
    }

    @CrossOrigin(maxAge = 86400)
    @PutMapping(path = "/{id}")
    @ApiOperation(value = "Actualiza una orden de provisión")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Orden de provisión actualizada correctamente"),
            @ApiResponse(code = 401, message = "No autorizado"),
            @ApiResponse(code = 403, message = "Prohibido"),
            @ApiResponse(code = 404, message = "El ID no existe")
    })
    public ResponseEntity<Provision> actualizar(@RequestBody Provision provision, @PathVariable Integer id){
        OptionalInt indexOpt = IntStream.range(0, listaProvisiones.size())
                .filter(i -> listaProvisiones.get(i).getId().equals(id))
                .findFirst();

        if(indexOpt.isPresent()){
            listaProvisiones.set(indexOpt.getAsInt(), provision);
            return ResponseEntity.ok(provision);
        }
        else{
            return ResponseEntity.notFound().build();
        }
    }

    @CrossOrigin(maxAge = 86400)
    @DeleteMapping(path = "/{id}")
    @ApiOperation(value = "Anula una orden de provisión")
    public ResponseEntity<Provision> borrar(@PathVariable Integer id) throws Exception{
        OptionalInt indexOpt = IntStream.range(0, listaProvisiones.size())
                .filter(i -> listaProvisiones.get(i).getId().equals(id))
                .findFirst();

        if(indexOpt.isPresent()){
            Provision provisionParaAnular = listaProvisiones.get(indexOpt.getAsInt());
            if(!provisionParaAnular.isHabilitado()){
                throw new Exception("La orden de provisión de ID " + id + "ya se encuentra anulada");
            }
            provisionParaAnular.setHabilitado(true);
            listaProvisiones.set(indexOpt.getAsInt(), provisionParaAnular);
            return ResponseEntity.ok().build();
        }
        else{
            return ResponseEntity.notFound().build();
        }
    }

}
