package com.aplication.rest.Repository;

import com.aplication.rest.entity.Categoria;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CategoriaRepository extends JpaRepository<Categoria,Long> {
//categorias es la entidad y Long es el tipo de dato del id de la entidad.

    //buscar categoria por nombre de categoria, devuelve un optional de categoria.
    Optional <Categoria> findByNombreCategoria(String nombreCategoria);
    //optional es una clase contenedora que puede contener un valor o estar vacía, lo que ayuda a evitar errores de referencia nula.

    //verificar si existe una categoria por nombre de categoria, devuelve un booleano.
    boolean existsByNombreCategoria(String nombreCategoria);
}
