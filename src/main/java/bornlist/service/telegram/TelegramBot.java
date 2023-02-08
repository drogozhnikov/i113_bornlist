package bornlist.service.telegram;

import bornlist.model.TelegramUnit;
import bornlist.service.TelegramService;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.util.ArrayList;
import java.util.List;

@Component
@NoArgsConstructor
public class TelegramBot extends TelegramLongPollingBot {

    @Value("${botname}")
    private String BOT_NAME;
    @Value("${bottoken}")
    private String BOT_TOKEN;

    @Autowired
    private TelegramService service;

    @Override
    public String getBotUsername() {
        return BOT_NAME;
    }

    @Override
    public String getBotToken() {
        return BOT_TOKEN;
    }

    @Override
    public void onRegister() {

    }

    @Override
    public void onUpdateReceived(Update update) {
        if (update.hasMessage() && update.getMessage().hasText()) {
            TelegramUnit unit = fillUnit(update.getMessage());
            service.getResponce(unit);
            sendMessage(unit);
        }
    }

    public void sendMessage(TelegramUnit outputUnit) {
        SendMessage outMess = new SendMessage();
        setKeys(outMess);
        outMess.setChatId(outputUnit.getChatId());
        outMess.setText(outputUnit.getMessage());
        try {
            execute(outMess);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }

    private void setKeys(SendMessage outMess) {
        outMess.enableMarkdown(true);
        // Создаем клавиатуру
        ReplyKeyboardMarkup replyKeyboardMarkup = new
                ReplyKeyboardMarkup();
        outMess.setReplyMarkup(replyKeyboardMarkup);
        replyKeyboardMarkup.setSelective(true);
        replyKeyboardMarkup.setResizeKeyboard(true);
        replyKeyboardMarkup.setOneTimeKeyboard(false);

        // Создаем список строк клавиатуры
        List<KeyboardRow> keyboard = new ArrayList<>();

        // Первая строчка клавиатуры
        KeyboardRow keyboardFirstRow = new KeyboardRow();
        // Добавляем кнопки в первую строчку клавиатуры
        keyboardFirstRow.add("/get");
        keyboardFirstRow.add("/list");
        keyboardFirstRow.add("/help");

        // Добавляем все строчки клавиатуры в список
        keyboard.add(keyboardFirstRow);
        // и устанавливаем этот список нашей клавиатуре
        replyKeyboardMarkup.setKeyboard(keyboard);
    }

    private TelegramUnit fillUnit(Message inMess) {
        return TelegramUnit.builder()
                .firstName(inMess.getFrom().getFirstName())
                .lastName(inMess.getFrom().getLastName())
                .message(inMess.getText())
                .chatId(inMess.getChatId().toString())
                .userName(inMess.getFrom().getUserName())
                .build();
    }

}
