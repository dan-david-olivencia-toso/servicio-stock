package com.dan.dot.stock.rest;

import com.dan.dot.stock.domain.Producto;
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

@CrossOrigin(maxAge = 36000)
@RestController
@RequestMapping("/api/producto")
@Api(value = "ProductoRest", description = "Permite gestionar la información referida a los productos que ofrece la empresa para la venta")
public class ProductoRest {

    private static final List<Producto> listaProductos = new ArrayList<>();
    private static Integer ID_GEN = 1;

    @CrossOrigin(maxAge = 86400)
    @GetMapping
    public ResponseEntity<List<Producto>> todos() {
        return ResponseEntity.ok(listaProductos);
    }

    @CrossOrigin(maxAge = 86400)
    @GetMapping(path = "/{id}")
    @ApiOperation(value = "Busca un producto por id")
    public ResponseEntity<Producto> productoPorId(@PathVariable Integer id) {
        Optional<Producto> p = listaProductos
                .stream()
                .filter(producto -> producto.getId().equals(id))
                .findFirst();
        return ResponseEntity.of(p);
    }

    @CrossOrigin(maxAge = 86400)
    @PostMapping
    public ResponseEntity<Producto> crear(@RequestBody Producto nuevo){
        nuevo.setId(ID_GEN++);
        listaProductos.add(nuevo);
        return ResponseEntity.ok(nuevo);
    }

    @CrossOrigin(maxAge = 86400)
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

    @CrossOrigin(maxAge = 86400)
    @DeleteMapping(path = "/{id}")
    @ApiOperation(value = "Borra lógicamente un producto")
    public ResponseEntity<Producto> borrar(@PathVariable Integer id) throws Exception{
        OptionalInt indexOpt = IntStream.range(0, listaProductos.size())
                .filter(i -> listaProductos.get(i).getId().equals(id))
                .findFirst();

        if(indexOpt.isPresent()){
            Producto productoParaEliminar = listaProductos.get(indexOpt.getAsInt());
            if(!productoParaEliminar.isHabilitado()){
                throw new Exception("El producto vinculado al ID " + id + "ya ha sido eliminado");
            }
            productoParaEliminar.setHabilitado(false);
            listaProductos.set(indexOpt.getAsInt(), productoParaEliminar);
            return ResponseEntity.ok().build();


        }
        else{
            return ResponseEntity.notFound().build();
        }
    }


}
