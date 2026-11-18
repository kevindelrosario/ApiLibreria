package com.aplication.rest.service.impl;

import com.aplication.rest.Repository.CategoriaRepository;
import com.aplication.rest.entity.Categoria;
import com.aplication.rest.service.CategoriaService;
import lombok.SneakyThrows;
import org.apache.coyote.BadRequestException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoriaServiceImpl implements CategoriaService {

    /**************************************************/
    //ESTO ES LO QUE AGREGAMOS NOSOTROS DESPUES DE IMPLEMENTAR LOS METOODS QUEREQUIERE
    // EL SERVICE IMPL:
    @Autowired
    private CategoriaRepository categoriaRepository;

    /**************************************************/

    /***CREAR CATEGORIA
     * @param categoria
     * @return
     */
    @Override
    @SneakyThrows
    public Categoria crearCategoria(Categoria categoria) {
        //verificamos si la categoria ya existe por nombre de categoria, si existe lanzamos una excepcion.
       if(categoriaRepository.existsByNombreCategoria(categoria.getNombreCategoria())){
           //si existe lanzamos una excepcion
           //badRequestException es una excepcion que indica que la solicitud realizada por el cliente es incorrecta o no puede ser procesada por el servidor, generalmente debido a datos inválidos o faltantes en la solicitud.
         throw new BadRequestException("La categoria con nombre " + categoria.getNombreCategoria() + " ya existe.");
           }
       //si no existe la categoria, la guardamos en la base de datos y la retornamos.
       Categoria nuevaCategoria = categoriaRepository.save(categoria);
         return nuevaCategoria;

    }

    /***LISTAR CATEGORIAS
     * @return
     */
    @Override
    public List<Categoria> listarCategorias() {
        return categoriaRepository.findAll();
    }

    /*** OBTENER CATEGORIA POR ID
     * @param idCategoria
     * @return
     */
    @Override
    public Optional<Categoria> obtenerCategoriaPorId(Long idCategoria) {
         return categoriaRepository.findById(idCategoria);
    }

    /*** ACTUALIZAR CATEGORIA
     */
    @Override
    @SneakyThrows
    public Categoria actualizarCategoria(Long idCategoria, Categoria categoria) {
        //verificamos si la categoria existe por id, si no existe lanzamos una excepcion.
        Categoria categoriaExistente = categoriaRepository.findById(idCategoria)
                .orElseThrow(() -> new Exception("Categoria con ID " + idCategoria + " no encontrada."));

        //si existe la categoria, actualizamos sus datos y la guardamos en la base de datos.
        categoriaExistente.setNombreCategoria(categoria.getNombreCategoria());
        return categoriaRepository.save(categoriaExistente);
    }

    /***  ELIMINAR CATEGORIA
     */
    @Override
    public void eliminarCategoria(Long idCategoria) {
    Optional<Categoria> categoriaExistente = categoriaRepository.findById(idCategoria);
    if(!categoriaExistente.isPresent()) {
        throw new RuntimeException("Categoria con ID " + idCategoria + " no encontrada.");
    }
        categoriaRepository.deleteById(idCategoria);

    }
}
