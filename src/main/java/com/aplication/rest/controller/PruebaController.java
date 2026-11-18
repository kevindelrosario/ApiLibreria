package com.aplication.rest.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/*
@Rescontroller:
indica que esta clase es un controlador de Spring MVC y que los métodos
de esta clase devolverán datos en formato JSON o XML en lugar de una vista HTML.
si fuera solo @Controller, los métodos de esta clase devolverían vistas HTML en lugar de datos en formato JSON o XML. y seria monolito,
lo que significa que todas las capas de la aplicación (presentación, lógica de negocio y acceso a datos) están integradas en una sola aplicación.

@Restcontroller combina @controller y @restbody, con este no se trabaja con vistas html, sino con datos en formato JSON o XML, lo que lo hace ideal para aplicaciones web modernas que consumen servicios web RESTful.
 */
//@RestController
//@RequestMapping("/api/prueba") //Configura la ruta base para todas las solicitudes HTTP que se manejan en este controlador. En este caso, todas las solicitudes que comiencen con /api/prueba se dirigirán a este controlador.
public class PruebaController {

//@GetMapping
    public String saludar() {
        return "Hola desde el controlador de prueba";
    }

}
