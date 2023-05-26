package bornlist.service;

import bornlist.dto.UnitDto;
import bornlist.exception.BlException;
import bornlist.utils.JsonIO;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.HttpStatus;
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

    public List<UnitDto> readJson(String username, MultipartFile file) {
        List<UnitDto> inputList = jsonIO.jsonToEntity(file);
        List<UnitDto> filteredList = jsonIO.filterByUserName(username, inputList);
        if (inputList.size() != filteredList.size()) {
            throw new BlException("Username missmatch found. Check Json file.", HttpStatus.BAD_REQUEST);
        }
        return filteredList;
    }

    public File getTemplateFile() throws IOException {
        File file = new ClassPathResource(fileName).getFile();
        ;
        return file;
    }
}
