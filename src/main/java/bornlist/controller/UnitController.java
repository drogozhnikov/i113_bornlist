package bornlist.controller;

import bornlist.dto.UnitDto;
import bornlist.service.UnitService;
import bornlist.service.UserService;
import bornlist.service.converter.UnitConverter;
import bornlist.service.converter.UserConverter;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/bl/unit")
public class UnitController {

    private UnitService unitService;
    private UnitConverter converter;

    public UnitController(UnitService unitService, UnitConverter converter) {
        this.unitService = unitService;
        this.converter = converter;
    }

    @GetMapping("/all")
    public List<UnitDto> getAllUnits() {
        return converter.convertListToDto(unitService.readAll());
    }

    @GetMapping("/{id}")
    public UnitDto getUnitById(@PathVariable("id") int id) {
        return converter.convertToDto(unitService.read(id));
    }

    @PostMapping("/")
    public void createUnit(@RequestBody UnitDto unitDto) {
        unitService.create(converter.convertToEntity(unitDto));
    }

    @PutMapping("/")
    public void updateUnit(@RequestBody UnitDto unitDto) {
        unitService.update(converter.convertToEntity(unitDto), unitDto.getId());
    }

    @DeleteMapping("/{id}")
    public void deleteUnit(@PathVariable("id") int id) {
        unitService.delete(id);
    }
}
