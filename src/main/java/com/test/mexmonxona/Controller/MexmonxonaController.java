package com.test.mexmonxona.Controller;

import com.test.mexmonxona.Model.Mexmonxona;
import com.test.mexmonxona.Model.Xona;
import com.test.mexmonxona.Repository.MexmonxonaRepository;
import com.test.mexmonxona.Repository.XonaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/mexmonxona")
public class MexmonxonaController {
    @Autowired
    MexmonxonaRepository mexmonxonaRepository;
    @Autowired
    XonaRepository xonaRepository;

    @GetMapping("/{id}")
    public List<Xona> xonaList(@PathVariable Integer id){
        List<Xona> xonaList =xonaRepository.findAllByMexmonxonaId(id);
        return xonaList;
    }
    @PostMapping
    public String MexmonxonaJoylash(@RequestBody Mexmonxona mexmonxona){
        if (mexmonxonaRepository.existsByNomi(mexmonxona.getNomi())){
            return "Bunday ma'lumot mavjud";
        }
        Mexmonxona mexmonxona1 = new Mexmonxona();
        mexmonxona1.setNomi(mexmonxona.getNomi());
        mexmonxonaRepository.save(mexmonxona1);
        return "Ma'lumot joylandi";
    }

    @PutMapping(value = "/{id}")
    public String mexmonxonaYangilash(@PathVariable Integer id , @RequestBody Mexmonxona mexmonxona){
        Optional<Mexmonxona> mexmonxonaOptional = mexmonxonaRepository.findById(id);
        if (!mexmonxonaOptional.isPresent()){
            return "Bunday mexmonxona mavjud emas";
        }
        Mexmonxona mexmonxona1 = mexmonxonaOptional.get();
        mexmonxona1.setNomi(mexmonxona.getNomi());
        mexmonxonaRepository.save(mexmonxona1);
        return "Tahrirlandi";
    }

    @DeleteMapping("/{id}")
    public String mexmonxonaOchirish(@PathVariable Integer id){
        if (mexmonxonaRepository.existsById(id)){
            mexmonxonaRepository.deleteById(id);
            return "Ma'lumot o'chirildi";
        }
        else {
            return "Ma'lumot topilmadi";
        }
    }
}
