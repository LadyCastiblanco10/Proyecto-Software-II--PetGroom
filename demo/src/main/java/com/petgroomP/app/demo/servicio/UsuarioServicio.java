package com.petgroomP.app.demo.servicio;

import java.util.List;

import org.springframework.security.core.userdetails.UserDetailsService;

import com.petgroomP.app.demo.controlador.dto.UsuarioRegistroDTO;
import com.petgroomP.app.demo.modelo.Usuario;



public interface UsuarioServicio extends UserDetailsService{

	public Usuario guardar(UsuarioRegistroDTO registroDTO);
	
	public List<Usuario> listarUsuarios();

	public Usuario findByEmail(String username);
	
	
}
