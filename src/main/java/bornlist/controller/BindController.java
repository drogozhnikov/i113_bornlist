package bornlist.controller;

import bornlist.dto.UnitDto;
import bornlist.service.BindService;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/bl/bind")
public class BindController {

    private BindService bindService;

    public BindController(BindService bindService) {
        this.bindService = bindService;
    }

    @GetMapping("/all")
    public List<UnitDto> getAllUnits() {
        return bindService.findToday("Geralt_tid");
    }
}
