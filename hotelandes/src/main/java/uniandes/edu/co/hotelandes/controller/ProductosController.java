package uniandes.edu.co.hotelandes.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import uniandes.edu.co.hotelandes.modelo.Producto;
import uniandes.edu.co.hotelandes.repositorio.ProductoRepository;

@Controller
public class ProductosController {
    @Autowired
    private ProductoRepository productoRepository;

    @GetMapping("/productos")
    public String productos(Model model){
        model.addAttribute("productos", productoRepository.darProductos());
        return "productos";
    }

    @GetMapping("/productos/new")
    public String productoForm(Model model){
        model.addAttribute("producto", new Producto());
        return "productoNuevo";
    }
    @PostMapping("/productos/new/save")
    public String productoGuardar(@ModelAttribute Producto producto){
        productoRepository.insertarProducto(producto.getNombre() , producto.getCosto());
        return "redirect:/productos";
    }

    @GetMapping("/productos/{id}/edit")
    public String productoEditarForm(@PathVariable("id") int id, Model model){
        Producto producto = productoRepository.darProducto(id);
        if (producto!=null){
            model.addAttribute("producto", producto);
            return "productoEditar";

        } else{
            return "redirect:/productos";
        }
    }

    @PostMapping("/productos/{id}/edit/save")
    public String productoEditarGuardar(@PathVariable("id") int id, @ModelAttribute Producto producto){
        productoRepository.actualizarProducto(id, producto.getNombre(), producto.getCosto());
        return "redirect:/productos";

    }


    @GetMapping("/productos/{id}/delete")
    public String productoEliminar(@PathVariable("id") int id){
        productoRepository.eliminarProducto(id);
        return "redirect:/productos";
    }






}
 