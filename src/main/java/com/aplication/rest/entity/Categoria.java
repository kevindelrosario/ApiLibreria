package com.aplication.rest.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Categoria {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)//auto incrementable
    @Column(name = "id_categoria")
    private Long idCategoria;

    @Column(name = "nombre_categoria", nullable = false, length = 50)
    private  String nombreCategoria;


  //LA RELACION LA CREAMOS EN LA CLASE QUE LLEVARA EL ID DE LA RELACION.
    /* a la hora de crear relaciones entre tablas, el lado que tiene la llave foranea es el que se considera el dueño de la relacion.
     Libro tiene el id de categoria, por lo tanto es el dueño de la relacion y es el que tiene la anotacion @ManyToOne.
     * */


}
