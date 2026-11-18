package com.aplication.rest.entity;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.io.Serializable;
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity //indica que esta clase es una entidad de JPA
@Table(name = "libro") //indica el nombre de la tabla en la base de datos
public class Libro implements Serializable {
    //Seriablizable: indica que esta clase puede ser convertida a un flujo de bytes para ser almacenada en la base de datos

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY) //indica que el valor del id se generará automáticamente
    private Long id;
    @Column(name = "titulo", nullable = false, length = 100)
    private String titulo;

    @Column(name = "autor", nullable = false)
    private String autor;

    @Column(name = "descripcion", length = 100)
    private String descripcion;

    @Column(name = "año_publicacion", nullable = false)
    private int anoPublicacion;

    @Column(name = "cantidad_disponible", nullable = false)
    private int cantidadDisponible;
    //enum
    @Enumerated(EnumType.STRING)
    @Column(name = "estado_libro", nullable = false)
    private EstadoLibro estadoLibro;


      /*CREAMOS LA RELACION manyToOne:
     muchos productos van a una categoria y una categoria podra pertenecer
     a muchos productos.
     */
    @ManyToOne //indica que esta clase tiene una relacion de muchos a uno con la clase Categoria
    @JoinColumn(name = "id_categoria", referencedColumnName = "id_categoria")//hace referencia a la columna de la tabla categoria
    private Categoria categoria; //esta es la llave foranea que hace referencia a la tabla categoria



}
