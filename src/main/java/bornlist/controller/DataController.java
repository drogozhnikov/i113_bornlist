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
    public void loadJson(@RequestParam String username, @RequestPart MultipartFile file) {
        unitService.loadJson(username,dataService.readJson(file));
    }

    @PostMapping("/loadAndReplaceJson")
    public void loadAndReplaceJson(@RequestParam String username, @RequestPart MultipartFile file) {
        unitService.loadAndReplaceJson(username,dataService.readJson(file));
    }


}
