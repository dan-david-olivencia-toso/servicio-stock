package rest;

import domain.MovimientoStock;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import service.MovimientoStockService;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.OptionalInt;
import java.util.stream.IntStream;

@CrossOrigin(maxAge = 36000)
@RestController
@RequestMapping("/api/stock")
@Api(value = "StockRest", description = "Permite gestionar la información referida los movimientos de stock de la empresa, producto de la compra y venta de productos")
public class MovimientoStockRest {

    @Autowired
    MovimientoStockService movimientoStockService;

    private static final List<MovimientoStock> listaMovimientos = new ArrayList<>();
    private static Integer ID_GEN = 1;

    @GetMapping
    public ResponseEntity<List<MovimientoStock>> todos() {
        return ResponseEntity.ok(listaMovimientos);
    }

    @GetMapping(path = "/{id}")
    @ApiOperation(value = "Busca un movimiento por id")
    public ResponseEntity<MovimientoStock> movimientoPorId(@PathVariable Integer id){
        Optional<MovimientoStock> m = listaMovimientos
                .stream()
                .filter(producto -> producto.getId().equals(id))
                .findFirst();
        return ResponseEntity.of(m);
    }

    @PostMapping
    @ApiOperation(value = "Agrega un nuevo movimiento de stock")
    public ResponseEntity<?> crear(@RequestBody MovimientoStock nuevo){
        MovimientoStock ms = null;
        try {
            ms = this.movimientoStockService.crear(nuevo);
        } catch (Exception e1) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e1.getMessage());
        }
        return ResponseEntity.ok(ms);
    }

    @PutMapping(path = "/{id}")
    @ApiOperation(value = "Actualiza un movimiento de stock")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Movimiento actualizado correctamente"),
            @ApiResponse(code = 401, message = "No autorizado"),
            @ApiResponse(code = 403, message = "Prohibido"),
            @ApiResponse(code = 404, message = "El ID no existe")
    })
    public ResponseEntity<MovimientoStock> actualizar(@RequestBody MovimientoStock movimiento, @PathVariable Integer id){
        OptionalInt indexOpt = IntStream.range(0, listaMovimientos.size())
                .filter(i -> listaMovimientos.get(i).getId().equals(id))
                .findFirst();

        if(indexOpt.isPresent()){
            listaMovimientos.set(indexOpt.getAsInt(), movimiento);
            return ResponseEntity.ok(movimiento);
        }else{
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping(path = "/{id}")
    @ApiOperation(value = "Anula un movimiento de stock")
    public ResponseEntity<MovimientoStock> borrar(@PathVariable Integer id) throws Exception{
        OptionalInt indexOpt = IntStream.range(0, listaMovimientos.size())
                .filter(i -> listaMovimientos.get(i).getId().equals(id))
                .findFirst();

        if(indexOpt.isPresent()){
            MovimientoStock movimientoParaAnular = listaMovimientos.get(indexOpt.getAsInt());
            if(movimientoParaAnular.isHabilitado()){
                throw new Exception("El movimiento de stock vinculado al ID" + id + "ya ha sido anulado previamente");
            }
            movimientoParaAnular.setHabilitado(true);
            listaMovimientos.set(indexOpt.getAsInt(), movimientoParaAnular);
            return ResponseEntity.ok().build();
        }
        else{
            return ResponseEntity.notFound().build();
        }
    }

}
