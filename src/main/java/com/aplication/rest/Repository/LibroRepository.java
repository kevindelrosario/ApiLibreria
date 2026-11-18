package com.aplication.rest.Repository;

import com.aplication.rest.entity.EstadoLibro;
import com.aplication.rest.entity.Libro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

//Es una interface
@Repository
public interface LibroRepository extends JpaRepository<Libro,Long> {
//es una interface que extiende de JpaRepository, lo que significa que
// hereda todos los métodos CRUD (Create, Read, Update, Delete) y otros
// métodos útiles para interactuar con la base de datos.

    Optional<Libro> findBytitulo(String tituloLibro);

    Optional<Libro> findById(Long id);

    List<Libro> findByEstadoLibro(EstadoLibro estadoLibro);

}
