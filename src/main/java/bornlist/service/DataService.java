package bornlist.service;

import bornlist.dto.UnitDto;
import bornlist.utils.JsonIO;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.List;

@Component
public class DataService {

    private final JsonIO jsonIO;

    @Value("${json.template.path}")
    private String fileName;

    public DataService(JsonIO jsonIO) {
        this.jsonIO = jsonIO;
    }

    public List<UnitDto> readJson() {
        return jsonIO.jsonToEntity(fileName);
    }

    public List<UnitDto> readJson(MultipartFile file) {
        return jsonIO.jsonToEntity(file);
    }

    public File getTemplateFile() throws IOException {
        File file = new ClassPathResource(fileName).getFile();;
        return file;
    }
}
