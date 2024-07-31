package com.petgroomP.app.demo.controlador;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.petgroomP.app.demo.modelo.Usuario;
import com.petgroomP.app.demo.servicio.UsuarioServicio;

@Controller
@RequestMapping("/usuario")
public class UsuarioControlador {

    @Autowired
    private UsuarioServicio usuarioServicio;

    @GetMapping("/perfil")
    public String verPerfilUsuario(Model model, @AuthenticationPrincipal UserDetails userDetails) {
        Usuario usuario = usuarioServicio.findByEmail(userDetails.getUsername());
        model.addAttribute("usuario", usuario);
        model.addAttribute("mascotasPropias", usuario.getMascotas());
        model.addAttribute("mascotasInteresadas", usuario.getMascotasInteresadas());
        return "Perfil_usuario";
    }
}
