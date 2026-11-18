package com.aplication.rest.controller;

import com.aplication.rest.entity.Categoria;
import com.aplication.rest.service.CategoriaService;
import lombok.SneakyThrows;
import org.apache.coyote.BadRequestException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/categorias")
public class CategoriaController {

    //requestParam: es una anotación que se utiliza para extraer parámetros de la URL de la solicitud HTTP. Se utiliza para obtener valores de los parámetros de consulta (query parameters) o de los segmentos de la ruta (path variables) en las solicitudes HTTP.
    //pathVariable: es una anotación que se utiliza para extraer valores de los segmentos de la ruta (path variables) en las solicitudes HTTP. Se utiliza cuando se desea capturar un valor específico de la URL que forma parte de la ruta.

    //inyectamos el servicio de categoria
    @Autowired
    private CategoriaService categoriaService;

    /***CREAR CATEGORIA
     * @param categoria
     * @return
     */
    @PostMapping
    public ResponseEntity<Categoria> crearCategoria(@RequestBody Categoria categoria) {
            Categoria nuevaCategoria = categoriaService.crearCategoria(categoria);
            return new ResponseEntity<>(nuevaCategoria, HttpStatus.CREATED);
    }

    /***LISTAR CATEGORIAS
     * @return
     */
    @GetMapping
    public ResponseEntity<List<Categoria>> listarCategorias() {
        List<Categoria> categorias = categoriaService.listarCategorias();
        return new ResponseEntity<>(categorias, HttpStatus.OK);
    }

    /*** OBTENER CATEGORIA POR ID
     * @param idCategoria
     * @return
     * @throws Exception
     */
    @SneakyThrows
    @GetMapping("/{idCategoria}")
    public ResponseEntity<Categoria> obtenerCategoriaPorId(@PathVariable Long idCategoria) throws Exception {
        Optional<Categoria> categoriaOp = categoriaService.obtenerCategoriaPorId(idCategoria);
        if(categoriaOp.isPresent()) {
            return new ResponseEntity<>(categoriaOp.get(), HttpStatus.OK);
        } else {
            throw new Exception("Categoria con ID " + idCategoria + " no encontrada.");
        }
    }

    /***ACTUALIZAR CATEGORIA
     * @param idCategoria
     * @param categoria
     * @return
     */
    @SneakyThrows
    @PutMapping("/{idCategoria}")
    public ResponseEntity<Categoria> actualizarCategoria(@PathVariable Long idCategoria, @RequestBody Categoria categoria) {
      try {
          Categoria categoriaActualizada = categoriaService.actualizarCategoria(idCategoria, categoria);
          if (categoriaActualizada != null) {
              return new ResponseEntity<>(categoriaActualizada, HttpStatus.OK);
          } else {
              throw new Exception("Categoria con ID " + idCategoria + " no encontrada.");
          }
      }catch (Exception e){
         return new ResponseEntity<>(HttpStatus.NOT_FOUND);
      }
    }

    /***ELIMINAR CATEGORIA
     * @param idCategoria
     * @return
     */
    @DeleteMapping("/{idCategoria}")
    public ResponseEntity<Void> eliminarCategoria(@PathVariable Long idCategoria) {
        try {
            categoriaService.eliminarCategoria(idCategoria);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
