package com.petgroomP.app.demo.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.petgroomP.app.demo.modelo.Mascota;

@Repository
public interface MascotaRepositorio extends JpaRepository<Mascota, Long> {
}
