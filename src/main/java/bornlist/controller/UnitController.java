package bornlist.controller;

import bornlist.dto.UnitDto;
import bornlist.service.UnitService;
import bornlist.service.converter.UnitConverter;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin //needed to Vue
@RequestMapping("/api/i113/bornlist/")
public class UnitController {

    private UnitService unitService;
    private UnitConverter converter;

    public UnitController(UnitService unitService, UnitConverter converter) {
        this.unitService = unitService;
        this.converter = converter;
    }

    @GetMapping("/all")
    public List<UnitDto> getAllUnits() {
        return unitService.getAll();
    }

    @PostMapping("/")
    public UnitDto createUnit(@RequestBody UnitDto unitDto) {
        return unitService.create(unitDto);
    }

    @PutMapping("/")
    public UnitDto updateUnit(@RequestBody UnitDto unitDto) {
       return unitService.update(unitDto);
    }

    @DeleteMapping("/{id}")
    public void deleteUnit(@PathVariable("id") int id) {
        unitService.delete(id);
    }
}
