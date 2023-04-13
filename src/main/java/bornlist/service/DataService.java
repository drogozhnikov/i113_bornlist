package bornlist.service;

import bornlist.dto.UnitDto;
import bornlist.utils.JsonIO;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class DataService {

    private final JsonIO jsonIO;

    @Value("${json.path}")
    private String fileName;

    public DataService(JsonIO jsonIO) {
        this.jsonIO = jsonIO;
    }

    public List<UnitDto> readJson() {
        return jsonIO.jsonToEntity(fileName);
    }
}
