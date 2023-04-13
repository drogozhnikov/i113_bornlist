package bornlist.controller;

import bornlist.service.DataService;
import bornlist.service.UnitService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin //needed to Vue
@RequestMapping("/api/i113/bornlist/data/")
@AllArgsConstructor
public class DataController {

    private DataService dataService;
    private UnitService unitService;

    @GetMapping("/loadJson")
    public void loadJson() {
        unitService.createMultiple(dataService.readJson());
    }
}
