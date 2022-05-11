package com.test.mexmonxona.Repository;

import com.test.mexmonxona.Model.Xona;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface XonaRepository extends JpaRepository<Xona, Integer> {
    List<Xona> findAllByMexmonxonaId(Integer id);
    boolean existsByNomiAndMexmonxonaId(String nomi, Integer id);
}