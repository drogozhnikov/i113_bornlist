package bornlist.service;

import bornlist.dto.MessageDto;
import bornlist.dto.UnitDto;
import lombok.AllArgsConstructor;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Locale;

@Service
@AllArgsConstructor
public class MessageService {

    private final MessageSource messageSource;

    public String getMessage(String code) {
        return messageSource.getMessage(code, null, Locale.getDefault());
    }

    public void sendMessageToTelegram(MessageDto messageDto){
        RestTemplate restTemplate = new RestTemplate();
        String resourceUrl = "http://localhost:8083/api/i113/telegrambot/";
        HttpEntity<MessageDto> request = new HttpEntity<>(messageDto);
        MessageDto foo = restTemplate.postForObject(resourceUrl, request, MessageDto.class);
    }

}
