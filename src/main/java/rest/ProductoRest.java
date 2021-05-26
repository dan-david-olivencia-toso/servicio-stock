package rest;

import domain.Producto;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.OptionalInt;
import java.util.stream.IntStream;

@RestController
@RequestMapping("/api/producto")
@Api(value = "ProductoRest", description = "Permite gestionar la información referida a los productos que ofrece la empresa para la venta")
public class ProductoRest {

    private static final List<Producto> listaProductos = new ArrayList<>();
    private static Integer ID_GEN = 1;

    @GetMapping
    public ResponseEntity<List<Producto>> todos() {
        return ResponseEntity.ok(listaProductos);
    }

    @GetMapping(path = "/{id}")
    @ApiOperation(value = "Busca un producto por id")
    public ResponseEntity<Producto> productoPorId(@PathVariable Integer id) {
        Optional<Producto> p = listaProductos
                .stream()
                .filter(producto -> producto.getId().equals(id))
                .findFirst();
        return ResponseEntity.of(p);
    }

    @PostMapping
    public ResponseEntity<Producto> crear(@RequestBody Producto nuevo){
        nuevo.setId(ID_GEN++);
        listaProductos.add(nuevo);
        return ResponseEntity.ok(nuevo);
    }

    @PutMapping(path = "/{id}")
    @ApiOperation(value = "Actualiza un producto")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Producto actualizado correctamente"),
            @ApiResponse(code = 401, message = "No autorizado"),
            @ApiResponse(code = 403, message = "Prohibido"),
            @ApiResponse(code = 404, message = "El ID no existe")
    })
    public ResponseEntity<Producto> actualizar(@RequestBody Producto nuevo, @PathVariable Integer id){
        OptionalInt indexOpt = IntStream.range(0, listaProductos.size())
                .filter(i -> listaProductos.get(i).getId().equals(id))
                .findFirst();

        if(indexOpt.isPresent()){
            listaProductos.set(indexOpt.getAsInt(), nuevo);
            return ResponseEntity.ok(nuevo);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<Producto> borrar(@PathVariable Integer id) throws Exception{

        boolean exists = false;

        for(Producto producto: listaProductos){
            if(producto.getId().equals(id)){
                if(!producto.isHabilitado()){
                    throw new Exception("El producto vinculado al ID" + id + "ya ha sido eliminado");
                }
                else{
                    producto.setHabilitado(false);
                }
            }
        }

        if(exists){
            return ResponseEntity.ok().build();
        } else{
            return ResponseEntity.notFound().build();
        }
    }


}
