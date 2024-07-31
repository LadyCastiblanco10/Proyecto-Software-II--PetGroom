package com.petgroomP.app.demo.controlador;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import com.petgroomP.app.demo.modelo.Mascota;
import com.petgroomP.app.demo.modelo.Usuario;
import com.petgroomP.app.demo.servicio.MascotaServicio;
import com.petgroomP.app.demo.servicio.UsuarioServicio;

import java.io.File;
import java.io.IOException;
import java.util.List;

@Controller
@RequestMapping("/mascotas")
public class MascotaControlador {

    @Autowired
    private MascotaServicio mascotaServicio;

    @Autowired
    private UsuarioServicio usuarioServicio;

    @GetMapping
    public String listarMascotas(Model model) {
        List<Mascota> mascotas = mascotaServicio.listarMascotas();
        model.addAttribute("mascotas", mascotas);
        return "modulo_adopciones";
    }

    @GetMapping("/nueva")
    public String mostrarFormularioNuevaMascota(Model model) {
        model.addAttribute("mascota", new Mascota());
        return "modulo_adopciones";
    }

    @PostMapping
public String guardarMascota(@ModelAttribute("mascota") Mascota mascota,
                             @AuthenticationPrincipal UserDetails userDetails) {

    Usuario usuario = usuarioServicio.findByEmail(userDetails.getUsername());
    mascota.setDuenio(usuario);

    mascotaServicio.guardarMascota(mascota);
    return "redirect:/mascotas";
}

    @PostMapping("/{id}/interesarse")
    public String interesarseEnMascota(@PathVariable("id") Long id, @AuthenticationPrincipal UserDetails userDetails) {
        Usuario usuario = usuarioServicio.findByEmail(userDetails.getUsername());
        Mascota mascota = mascotaServicio.encontrarMascotaPorId(id);
        mascotaServicio.interesarseEnMascota(mascota, usuario);
        return "redirect:/mascotas";
    }
    @GetMapping("/{id}/editar")
    public String mostrarFormularioEditarMascota(@PathVariable("id") Long id, Model model, @AuthenticationPrincipal UserDetails userDetails) {
        Mascota mascota = mascotaServicio.encontrarMascotaPorId(id);
        Usuario usuario = usuarioServicio.findByEmail(userDetails.getUsername());

        if (mascota.getDuenio().getId().equals(usuario.getId())) {
            model.addAttribute("mascota", mascota);
            return "EditarMascota";
        } else {
            return "redirect:/mascotas";
        }
    }
    @PostMapping("/{id}/editar")
    public String actualizarMascota(@PathVariable("id") Long id, @ModelAttribute("mascota") Mascota mascotaActualizada,
                                    @AuthenticationPrincipal UserDetails userDetails) {
        Mascota mascota = mascotaServicio.encontrarMascotaPorId(id);
        Usuario usuario = usuarioServicio.findByEmail(userDetails.getUsername());

        if (mascota.getDuenio().getId().equals(usuario.getId())) {
            mascota.setNombre(mascotaActualizada.getNombre());
            mascota.setEspecie(mascotaActualizada.getEspecie());
            mascota.setGenero(mascotaActualizada.getGenero());
            mascota.setEdad(mascotaActualizada.getEdad());
            mascota.setDescripcion(mascotaActualizada.getDescripcion());
            mascotaServicio.guardarMascota(mascota);
        }
        return "redirect:/mascotas";
    }

    @PostMapping("/{id}/borrar")
    public String borrarMascota(@PathVariable("id") Long id, @AuthenticationPrincipal UserDetails userDetails) {
        Mascota mascota = mascotaServicio.encontrarMascotaPorId(id);
        Usuario usuario = usuarioServicio.findByEmail(userDetails.getUsername());

        if (mascota.getDuenio().getId().equals(usuario.getId())) {
            mascotaServicio.borrarMascota(id);
        }
        return "redirect:/mascotas";
    }


             
}
