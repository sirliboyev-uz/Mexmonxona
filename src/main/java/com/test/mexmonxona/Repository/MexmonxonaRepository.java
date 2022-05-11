package com.test.mexmonxona.Repository;

import com.test.mexmonxona.Model.Mexmonxona;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MexmonxonaRepository extends JpaRepository<Mexmonxona, Integer> {
    boolean existsByNomi(String nomi);
}
