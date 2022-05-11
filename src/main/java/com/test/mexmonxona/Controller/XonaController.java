package com.test.mexmonxona.Controller;

import com.test.mexmonxona.DTO.XonaDTO;
import com.test.mexmonxona.Model.Mexmonxona;
import com.test.mexmonxona.Model.Xona;
import com.test.mexmonxona.Repository.MexmonxonaRepository;
import com.test.mexmonxona.Repository.XonaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/xona")
public class XonaController {
    @Autowired
    MexmonxonaRepository mexmonxonaRepository;
    @Autowired
    XonaRepository xonaRepository;

    @GetMapping("/{id}")
    public List<Xona> xonaOqish(@PathVariable Integer id){
        List<Xona> xonaList = xonaRepository.findAllByMexmonxonaId(id);
        return xonaList;
    }

    @PostMapping
    public String xonaQoshish(@RequestBody XonaDTO xonaDTO){
        Optional<Mexmonxona> optionalMexmonxona = mexmonxonaRepository.findById(xonaDTO.getMexmonxonaid());
        if (!optionalMexmonxona.isPresent()){
            return "Bunday mexmonxona mavjud emas";
        }
        Xona xona = new Xona();
        xona.setNomi(xonaDTO.getNomi());
        xona.setQavati(xonaDTO.getQavati());
        xona.setXonalarsoni(xonaDTO.getXonalarsoni());
        xona.setMexmonxona(optionalMexmonxona.get());
        if (xonaRepository.existsByNomiAndMexmonxonaId(xonaDTO.getNomi(), xonaDTO.getMexmonxonaid()))
            return "Bunday xona mavjud";
        xonaRepository.save(xona);
        return "Muvaffaqiyatli saqlandi";
    }

    @PutMapping("/{id}")
    public String xonaYangilash(@PathVariable Integer id, @RequestBody Xona xonaDTO){
        Optional<Xona> optionalXona = xonaRepository.findById(id);
        if (!optionalXona.isPresent()){
            return "Bunday xona mavjud emas!";
        }
        Xona xona = optionalXona.get();
        xona.setNomi(xonaDTO.getNomi());
        xona.setQavati(xonaDTO.getQavati());
        xona.setXonalarsoni(xonaDTO.getXonalarsoni());
        xonaRepository.save(xona);
        return "Muvaffaqiyatli yangilandi!";
    }
    @DeleteMapping("/{id}")
    public String xonaOchirish(@PathVariable Integer id){
        if (xonaRepository.existsById(id)){
            xonaRepository.deleteById(id);
            return "Muvaffaqiyatli o'chirildi";
        }
        return "Bunday xona mavjud emas";
    }
}
