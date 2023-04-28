package bornlist.controller;

import bornlist.service.DataService;
import bornlist.service.UnitService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
//@CrossOrigin //needed to Vue
@RequestMapping("/api/i113/bornlist/data/")
@AllArgsConstructor
public class DataController {

    private DataService dataService;
    private UnitService unitService;

    @PostMapping("/loadJson")
    public void loadJson2(@RequestPart MultipartFile file) {
        unitService.createMultiple(dataService.readJson(file));
    }
}
