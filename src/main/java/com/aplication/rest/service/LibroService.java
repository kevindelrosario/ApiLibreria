package com.aplication.rest.service;

import com.aplication.rest.entity.EstadoLibro;
import com.aplication.rest.entity.Libro;

import java.util.List;
import java.util.Optional;

//service es una capa de abstracción que define las operaciones que
// se pueden realizar sobre los libros, como registrar, listar, buscar, actualizar y eliminar libros. Esta interfaz será implementada por una clase concreta (LibroServiceImpl) que contendrá la lógica de negocio para estas operaciones.
public interface LibroService {
    Libro registrarLibro(Long categoriaId, Libro libro); //cambios para agregar la categoria al registrar un libro

    List<Libro> listarLibros();

    Optional<Libro> buscarPorTitulo(String titulo);

    Optional<Libro> buscarPorId(Long id);

    Libro actualizarLibro(Long id, Libro libro);

    void eliminarLibro(Long id);

    Libro cambiarEstadoLibro(Long id, EstadoLibro nuevoEstadoLibro);

    List<Libro> obtenerLibrosPorEstado(EstadoLibro estadoLibro);

}
