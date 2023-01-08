package bornlist.service.telegram;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

@Component
@AllArgsConstructor
@NoArgsConstructor
public class TelegramBot extends TelegramLongPollingBot {

//    @Value("botname")
//    private String BOT_TOKEN;
//    @Value("bottoken")
//    private String BOT_NAME;
//    @Value("botrootchatid")
//    private String chatId;

    private String botToken = "5733624811:AAE25__kjWSUG-qrScGNA1hsEUszcZiYOXM";
    private String botName = "i113_assistant_bot";

    private TelegramService service;

    @Override
    public String getBotUsername() {
        return botName;
    }

    @Override
    public String getBotToken() {
        return botToken;
    }

    @Override
    public void onRegister() {

    }

    @Override
    public void onUpdateReceived(Update update) {
        if(update.hasMessage() && update.getMessage().hasText()){
            Message inMess = update.getMessage();
            String chatId = inMess.getChatId().toString();
            String response = service.getResponce(chatId,inMess.getText());
            sendMessage(chatId, response);
        }
    }

    public void sendMessage(String chatId, String input){
        if(!"".equals(chatId)){
            SendMessage outMess = new SendMessage();
            outMess.setChatId(chatId);
            outMess.setText(input);
            try {
                execute(outMess);
            } catch (TelegramApiException e) {
                e.printStackTrace();
            }
        }
    }
}
