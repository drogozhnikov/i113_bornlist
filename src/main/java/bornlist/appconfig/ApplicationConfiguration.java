package bornlist.appconfig;

import bornlist.service.MessageService;
import bornlist.service.telegram.TelegramBot;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;

import javax.annotation.PostConstruct;

@Slf4j
@Configuration
public class ApplicationConfiguration {

    TelegramBot bot;

    @Bean
    public MessageService getMessageService(MessageSource messageSource) {
        return new MessageService(messageSource);
    }

    @Bean
    public ModelMapper getModelMapper(){ return new ModelMapper();}

    @PostConstruct
    public void registerTelegramBot() {
        try {
            TelegramBotsApi telegramBotsApi = new TelegramBotsApi(DefaultBotSession.class);
            telegramBotsApi.registerBot(bot);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }

    @Autowired
    public void setBot(TelegramBot bot) {
        this.bot = bot;
    }
}
