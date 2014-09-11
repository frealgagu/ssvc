package co.edu.udistrital.notification.mail;

import co.edu.udistrital.notification.NotificationSender;
import co.edu.udistrital.service.ConfigurationService;
import com.spinn3r.log5j.Logger;
import org.apache.commons.lang.StringEscapeUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

@Service("smsNotificationSender")
public class SMSNotificationSender implements NotificationSender {

    private static final Logger logger = Logger.getLogger();

    private static final String DUMMY_SMS_MODE = "dummy";
    private static final String PRODUCTION_SMS_MODE = "production";

    protected static final String ACTION = "sendsms";
    protected static final String FROM = "ssvc";
    protected static final String ENCODING = "UTF-8";
    protected static final String ENDPOINT = "http://www.smsglobal.com/http-api.php";

    @Value("${sms.mode}")
    private String smsMode;

    @Autowired
    protected ConfigurationService configurationService;

    @Override
    public void sendNotification(String subject, String message, String destination) {
        switch(smsMode) {
            case DUMMY_SMS_MODE:
                try {
                    logger.info("Sending message [" + message + "] to destination: " + destination + ". Encoded message: " + URLEncoder.encode(replaceAccents(message), ENCODING));
                } catch (IOException ex) {
                    logger.error(ex.getMessage());
                }
                break;
            case PRODUCTION_SMS_MODE:
                InputStreamReader inputStreamReader = null;
                BufferedReader bufferedReader = null;
                StringBuilder urlString = new StringBuilder(50);
                try {
                    Map<String, String> parameters = new HashMap<>();
                    parameters.put("action", ACTION);
                    parameters.put("user", configurationService.getSMSSenderUsername());
                    parameters.put("password", configurationService.getSMSSenderPassword());
                    parameters.put("from", FROM);
                    parameters.put("to", destination);
                    parameters.put("text", URLEncoder.encode(replaceAccents(message), ENCODING));

                    //SMS Endpoint
                    urlString.append(ENDPOINT).append('?');
                    for(Map.Entry<String, String> entry : parameters.entrySet()) {
                        urlString.append(entry.getKey()).append('=').append(entry.getValue());
                        urlString.append('&');
                    }
                    if(urlString.toString().endsWith("&")) {
                        urlString.deleteCharAt(urlString.lastIndexOf("&"));
                    }

                    logger.debug("URL: " + urlString);

                    URL url = new URL(urlString.toString());
                    URLConnection urlConnection = url.openConnection();
                    inputStreamReader = new InputStreamReader(urlConnection.getInputStream());
                    bufferedReader = new BufferedReader(inputStreamReader);

                    StringBuilder response = new StringBuilder();
                    String line;
                    while ((line = bufferedReader.readLine()) != null) {
                        response.append(line);
                    }
                    logger.info("Response message -> " + response);
                    if(response.toString().startsWith("ERR")) {
                        logger.error("Response error from provider. " + response);
                    } else {
                        logger.info("Response from SMS provider: " + response.toString());
                        return ;
                    }
                } catch (IOException ex) {
                    logger.error(ex.getMessage() + " " + urlString);
                } finally {
                    if(inputStreamReader != null) {
                        try {
                            inputStreamReader.close();
                        } catch (IOException ignore) {
                        }
                    }
                    if(bufferedReader != null) {
                        try {
                            bufferedReader.close();
                        } catch (IOException ignore) {
                        }
                    }
                }
                break;
            default:
                break;
        }
    }

    private String replaceAccents(String text) {
        String[] accents = new String[] {
                "\u00E1",
                "\u00E9",
                "\u00ED",
                "\u00F3",
                "\u00FA",
                "\u00F1",
                "\u00C1",
                "\u00C9",
                "\u00CD",
                "\u00D3",
                "\u00DA",
                "\u00D1"
        };
        String[] replacements = new String[] {
                "a",
                "e",
                "i",
                "o",
                "u",
                "n",
                "A",
                "E",
                "I",
                "O",
                "U",
                "N"
        };
        return StringUtils.replaceEach(text, accents, replacements);
    }
}
