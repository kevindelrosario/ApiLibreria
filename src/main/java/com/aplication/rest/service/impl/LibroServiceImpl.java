package com.aplication.rest.service.impl;

import com.aplication.rest.Repository.CategoriaRepository;
import com.aplication.rest.Repository.LibroRepository;
import com.aplication.rest.entity.Categoria;
import com.aplication.rest.entity.EstadoLibro;
import com.aplication.rest.entity.Libro;
import com.aplication.rest.service.LibroService;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class LibroServiceImpl implements LibroService {


    @Autowired
    private LibroRepository libroRepository; //inyectamos el repositoru.
    @Autowired //este metodo tambien lo agregamos para poder buscar la categoria al registrar un libro
    private CategoriaRepository categoriaRepository; //inyectamos el repositorio de categoria para poder buscar la categoria al registrar un libro


    /*******************Registrar un libro *********************/
//   @Override //antes de agregar la categoria al registrar un libro
 //   public Libro registrarLibro(Libro libro) {
 //      return libroRepository.save(libro);
 //   }
//aqui esta la version modificada del metodo registrarLibro para agregar la categoria al registrar un libro
    @Override //metodo agregado para registrar un libro con categoria
    @SneakyThrows
    public Libro registrarLibro(Long categoriaId, Libro libro) {
        //antes de registrar un libro verificamos si la categoria existe, si no existe lanzamos una excepcion
        Categoria categoria = categoriaRepository.findById(categoriaId)
                .orElseThrow(() -> new Exception("Categoria con ID " + categoriaId + " no encontrada."));

        //si la categoria existe, asignamos la categoria al libro y lo registramos
        libro.setCategoria(categoria); //seteamos la categoria al libro

        return libroRepository.save(libro); //guardamos el libro en la base de datos
    }

    /*****************Listar todos los libros********************/
    @Override
    public List<Libro> listarLibros() {
        return libroRepository.findAll();
    }

    /*****************Buscar libro por titulo********************/
    @Override
    public Optional<Libro> buscarPorTitulo(String titulo) {
        return libroRepository.findBytitulo(titulo);
    }

    /*****************Buscar libro por id********************/
    @Override
    public Optional<Libro> buscarPorId(Long id) {
        return libroRepository.findById(id);
    }

    /*****************Actualizar libro********************/
    @Override
    @SneakyThrows //esta anotacion se quitara luego.
    public Libro actualizarLibro(Long id, Libro libro) {
        //En caso de que el LIBRO NO EXISTA:
       Libro libroExistente =  libroRepository.findById(id)
               .orElseThrow(() -> new Exception("Libro con ID "+ id + " no encontrado."));
       //En CASO DE QUE SI EXISTA lo actualizara:
        libroExistente.setTitulo(libro.getTitulo());
        libroExistente.setAutor(libro.getAutor());
        libroExistente.setAnoPublicacion(libro.getAnoPublicacion());
        libroExistente.setDescripcion(libro.getDescripcion());
        libroExistente.setEstadoLibro(libro.getEstadoLibro());
        libroExistente.setCantidadDisponible(libro.getCantidadDisponible());

        //comprobamos si la categoria existe, si no existe lanzamos una excepcion
        if(libro.getCategoria() != null && libro.getCategoria().getIdCategoria() != null) { //comprobamos que si o si exista la categoria, si no existe lanzamos una excepcion
            Categoria categoria = categoriaRepository.findById(libro.getCategoria().getIdCategoria())
                    .orElseThrow(() -> new Exception("Categoria con ID " + libro.getCategoria().getIdCategoria() + " no encontrada."));
            libroExistente.setCategoria(libro.getCategoria());
        }//si la categoria existe, asignamos la categoria al libro y lo actualizamos

        return libroRepository.save(libroExistente);

    }

    /*****************Eliminar libro********************/
    @Override
    @SneakyThrows
    public void eliminarLibro(Long id) {
      //verificamos si existe
        libroRepository.findById(id)
                .orElseThrow(() -> new Exception("Libro con ID "+ id + " no encontrado."));
        //si existe lo elimina
        libroRepository.deleteById(id);
    }

    /*****************Cambiar estado de libro********************/
    @Override
    @SneakyThrows
    public Libro cambiarEstadoLibro(Long id, EstadoLibro nuevoEstadoLibro) {
        //verificamos si existe
        Libro libroExistente =  libroRepository.findById(id)
                .orElseThrow(() -> new Exception("Libro con ID "+ id + " no encontrado."));
        libroExistente.setEstadoLibro(nuevoEstadoLibro);

        //si existe lo retornamos:
        return libroRepository.save(libroExistente);

    }

    /*****************Obtener libro por estado********************/
    @Override
    public List<Libro> obtenerLibrosPorEstado(EstadoLibro estadoLibro) {
        return libroRepository.findByEstadoLibro(estadoLibro);
    }
}
