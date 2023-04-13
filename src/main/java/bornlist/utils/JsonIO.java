package bornlist.utils;

import bornlist.dto.UnitDto;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.NoArgsConstructor;
import org.apache.http.entity.StringEntity;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;

import java.io.File;
import java.nio.charset.StandardCharsets;
import java.util.List;

@Component
@NoArgsConstructor
public class JsonIO {

    public List<UnitDto> jsonToEntity(String fileName) {
        List<UnitDto> dtoList = null;
        try {
            File file = new ClassPathResource(fileName).getFile();
            ObjectMapper mapper = new ObjectMapper();
            mapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
            dtoList = mapper.readValue(file, new TypeReference<List<UnitDto>>() {
            });
        } catch (Exception e) {
            System.out.println("Smth Wrong" + e);
        }
        return dtoList;
    }

}
