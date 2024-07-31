package com.petgroomP.app.demo.servicio;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.petgroomP.app.demo.modelo.Mascota;
import com.petgroomP.app.demo.modelo.Usuario;
import com.petgroomP.app.demo.repositorio.MascotaRepositorio;
import com.petgroomP.app.demo.repositorio.UsuarioRepositorio;

import java.util.List;

@Service
public class MascotaServicio {

    @Autowired
    private MascotaRepositorio mascotaRepositorio;

    @Autowired
    private UsuarioRepositorio usuarioRepositorio;

    // Método para agregar una mascota
    public void guardarMascota(Mascota mascota) {
        mascotaRepositorio.save(mascota);
    }

    // Método para listar todas las mascotas
    public List<Mascota> listarMascotas() {
        return mascotaRepositorio.findAll();
    }

    // Método para encontrar una mascota por su ID
    public Mascota encontrarMascotaPorId(Long id) {
        return mascotaRepositorio.findById(id)
                .orElseThrow(() -> new RuntimeException("Mascota no encontrada"));
    }

    // Método para mostrar interés en una mascota
    public void interesarseEnMascota(Mascota mascota, Usuario usuario) {
        mascota.getInteresados().add(usuario);
        mascotaRepositorio.save(mascota);
    }

    // Método para obtener la lista de interesados en una mascota
    public List<Usuario> listarInteresadosEnMascota(Long mascotaId) {
        Mascota mascota = encontrarMascotaPorId(mascotaId);
        return List.copyOf(mascota.getInteresados());
    }

    public void borrarMascota(Long id) {
        mascotaRepositorio.deleteById(id);
    }
}
