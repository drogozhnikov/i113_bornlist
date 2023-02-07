package bornlist.service.telegram;

import bornlist.model.TelegramUnit;
import bornlist.service.TelegramService;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

@Component
@NoArgsConstructor
public class TelegramBot extends TelegramLongPollingBot {

    @Autowired
    private TelegramService service;

    @Override
    public String getBotUsername() {
        return "i113_assistant_bot";
    }

    @Override
    public String getBotToken() {
        return "6130769269:AAHfY8rS5tB2kSEo0uGwoCgG6SKba797uc8";
    }

    @Override
    public void onRegister() {

    }

    @Override
    public void onUpdateReceived(Update update) {
        if(update.hasMessage() && update.getMessage().hasText()){
            TelegramUnit unit = fillUnit(update.getMessage());
            service.getResponce(unit);
            sendMessage(unit);
        }
    }

    public void sendMessage(TelegramUnit outputUnit){
            SendMessage outMess = new SendMessage();
            outMess.setChatId(outputUnit.getChatId());
            outMess.setText(outputUnit.getMessage());
            try {
                execute(outMess);
            } catch (TelegramApiException e) {
                e.printStackTrace();
            }
    }

    private TelegramUnit fillUnit(Message inMess){
        return TelegramUnit.builder()
                .firstName(inMess.getFrom().getFirstName())
                .lastName(inMess.getFrom().getLastName())
                .message(inMess.getText())
                .chatId(inMess.getChatId().toString())
                .userName(inMess.getFrom().getUserName())
                .build();
    }

}
