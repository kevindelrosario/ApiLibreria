package com.aplication.rest.controller;

import com.aplication.rest.entity.EstadoLibro;
import com.aplication.rest.entity.Libro;
import com.aplication.rest.service.LibroService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/libros")
public class LibroController {

    @Autowired
    private LibroService libroService;

    /************************ REGISTRAR *************************/
    @PostMapping("/registrar/{id_categoria}")
   public ResponseEntity<?> registrarLibro(@PathVariable Long id_categoria ,@RequestBody Libro libro){
        Libro nuevoLibro = libroService.registrarLibro(id_categoria,libro);
       return ResponseEntity.status(HttpStatus.CREATED).body(nuevoLibro);
   }

    /************************ LISTAR *************************/
    @GetMapping
    public ResponseEntity<List<Libro>> listarLibros(){
        List<Libro> libros = libroService.listarLibros();
        return ResponseEntity.ok(libros);
    }

    /********************* Buscar por titulo ***********************/
    @GetMapping("/buscar/titulo/{titulo}")
    public ResponseEntity<?> buscarPorTitulo(@PathVariable String titulo){
        Optional<Libro> libro = libroService.buscarPorTitulo(titulo);
        //verificamos si existe y si no retorna lo contrario
        return libro.isPresent()? ResponseEntity.ok(libro.get())
                : ResponseEntity.status(HttpStatus.NOT_FOUND).body("Libro no encontrado");

    }

    /**************************** BUSCAR POR ID ********************************/
    @GetMapping("/buscar/id/{id}")
    public ResponseEntity<?> buscarPorID(@PathVariable Long id){
        Optional<Libro> libro = libroService.buscarPorId(id);
        //verificamos si existe y si no retorna lo contrario
        return libro.isPresent()? ResponseEntity.ok(libro.get())
                : ResponseEntity.status(HttpStatus.NOT_FOUND).body("Libro no encontrado");

    }

    /**************************** ACTUALIZAR PRODUCTO ********************************/
    @PutMapping("/actualizar/{id}")
    public ResponseEntity<?> actualizarLibro(@PathVariable Long id, @RequestBody Libro libro){
    try{
        //tomamos los datos del libro a actualizar
        Libro libroActualizado = new Libro();
        libroActualizado.setId(id);
        libroActualizado.setTitulo(libro.getTitulo());
        libroActualizado.setAutor(libro.getAutor());
        libroActualizado.setDescripcion(libro.getDescripcion());
        libroActualizado.setAnoPublicacion(libro.getAnoPublicacion());
        libroActualizado.setCantidadDisponible(libro.getCantidadDisponible());
        libroActualizado.setEstadoLibro(libro.getEstadoLibro());

        //guardamos los nuevos valores en la base de datos
        Libro libroBBDD = libroService.actualizarLibro(id,libroActualizado);
        return ResponseEntity.ok(libroActualizado);
    }catch (Exception exception){
      return ResponseEntity.status(HttpStatus.NOT_FOUND).body(exception.getMessage());
    }
    }

    /********************* ELIMINAR LIBRO*****************************/
    @DeleteMapping("/{id}")
    public ResponseEntity<?> eliminarLibro(@PathVariable Long id){
        try{
            libroService.eliminarLibro(id);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();//solo retorna que no hay contenido porque estaria borrado.
        }catch (Exception exception){
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(exception.getMessage());
    }
    }

    /********************** CAMBIAR ESTADO DEL LIBRO****************************/
    @PutMapping("/estado/{id}")
    public ResponseEntity<?> cambiarEstadoProducto(@PathVariable Long id, @RequestBody EstadoLibro estadoLibro){
        try{
         Libro libroActualizado = libroService.cambiarEstadoLibro(id, estadoLibro);
         return ResponseEntity.ok(libroActualizado);
        }catch (Exception exception){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(exception.getMessage());
        }
    }

    /********************** LISTAR LIBROS POR ESTADOS **************************/
    @GetMapping("/estado/{estado}")
    public ResponseEntity<List<Libro>> listarLibrosPorEstado(@PathVariable EstadoLibro estado){
    List<Libro> libros = libroService.obtenerLibrosPorEstado(estado);
    return ResponseEntity.ok(libros);
    }


}
