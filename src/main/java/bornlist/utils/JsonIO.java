package bornlist.utils;

import bornlist.dto.UnitDto;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.NoArgsConstructor;
import org.apache.http.entity.StringEntity;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
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

    public List<UnitDto> jsonToEntity(MultipartFile inputFile) {
        List<UnitDto> dtoList = null;
        try {
            File file = convertMultiPartToFile(inputFile);
            if (file != null) {
                ObjectMapper mapper = new ObjectMapper();
                mapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
                dtoList = mapper.readValue(file, new TypeReference<List<UnitDto>>() {
                });
            }

        } catch (Exception e) {
            System.out.println("Smth Wrong");
        }
        return dtoList;
    }

    private File convertMultiPartToFile(MultipartFile file) throws IOException {
        if (file.getOriginalFilename() != null) {
            File convFile = new File(file.getOriginalFilename());
            FileOutputStream fos = new FileOutputStream(convFile);
            fos.write(file.getBytes());
            fos.close();
            return convFile;
        }
        return null;
    }


    public List<UnitDto> filterByUserName(String username, List<UnitDto> inputAccounts) {
        List<UnitDto> filteredList = new ArrayList<>();
        for (UnitDto account : inputAccounts) {
            if (account.getUserName() != null && account.getUserName().equals(username)) {
                filteredList.add(account);
            }
        }
        return filteredList;
    }


}
