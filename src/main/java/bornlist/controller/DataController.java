package bornlist.controller;

import bornlist.service.DataService;
import bornlist.service.UnitService;
import lombok.AllArgsConstructor;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

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

    @GetMapping("/template")
    public ResponseEntity<Object> downloadTemplate() throws IOException {
        File file = dataService.getTemplateFile();
        InputStreamResource resource = new InputStreamResource(new FileInputStream(file));

        HttpHeaders headers = new HttpHeaders();

        headers.add("Content-Disposition", String.format("attachment; filename=\"%s\"", file.getName()));
        headers.add("Cache-Control", "no-cache, no-store, must-revalidate");
        headers.add("Pragma", "no-cache");
        headers.add("Expires", "0");

        ResponseEntity<Object>
                responseEntity = ResponseEntity.ok().headers(headers).contentLength(file.length()).contentType(
                MediaType.parseMediaType("application/txt")).body(resource);

        return responseEntity;
    }

}
