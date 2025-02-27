package es.upm.dit.apsv.bot;
import org.telegram.telegrambots.ApiContextInitializer;
import org.telegram.telegrambots.TelegramBotsApi;
import org.telegram.telegrambots.exceptions.TelegramApiException;

public class Main {
	
    public static void main(String[] args) {

        // Initialize Api Context
    	ApiContextInitializer.init();

        // Instantiate Telegram Bots API
    	TelegramBotsApi botsApi = new TelegramBotsApi();

        // Register bot
    	try {
            botsApi.registerBot(new MoodleUPM_bot());
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }
}
