package com.aplication.rest.controller;

import com.aplication.rest.entity.Message;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

//1. marcarlo como controlador @RestController
//2. indicar que es un requestmapping ("/api/NombreApi")
//@RestController
//@RequestMapping("/api/messages")
public class MessageController {
    //creamos una lista
    private List<Message> mensajes = new ArrayList<>();

    public MessageController(){
        mensajes.add(new Message(1,"hola desde la api"));
        mensajes.add(new Message(2,"otro mensaje de relleno"));

    }
/****************Enviar un mensaje***********************/
    //metodo para obtener los mensajes anteriores
   // @GetMapping //metodo GET
    public List<Message> listarMensajes(){
    return mensajes;
    }

/****************obtener un mensaje***********************/
//@GetMapping("/{id}")
public Message obtenerMensajePorId(@PathVariable int id){
    //retornal un valor segun lo que se nos ingrese:
    Optional<Message> message = mensajes.stream()
            .filter(m -> m.getId() == id)
            .findFirst();
    return message.orElse(null);
}

/****************crear un mensaje***********************/
//@PostMapping //indica que se va a crear algo en este metodo
public Message crearMensaje(@RequestBody Message message){
    mensajes.add(message);
    return message;
}

/****************Eliminar un mensaje***********************/
//@DeleteMapping("/{id}") //indicamos el valor que buscara para eliminarlo
public  void eliminarMensaje(@PathVariable int id){
    mensajes.removeIf( m -> m.getId() == id);
}

}
