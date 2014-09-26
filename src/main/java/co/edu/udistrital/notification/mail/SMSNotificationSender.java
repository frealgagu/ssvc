package co.edu.udistrital.notification.mail;

import co.edu.udistrital.notification.NotificationSender;
import co.edu.udistrital.service.ConfigurationService;
import com.spinn3r.log5j.Logger;
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
import java.util.LinkedHashMap;
import java.util.Map;

@Service("smsNotificationSender")
public class SMSNotificationSender implements NotificationSender {

    private static final Logger logger = Logger.getLogger();

    protected static final String DUMMY_SMS_MODE = "dummy";
    protected static final String DUMMY_ENCODING = "UTF-8";

    protected static final String SMS_GLOBAL_SMS_MODE = "sms_global";
    protected static final String SMS_GLOBAL_ACTION = "sendsms";
    protected static final String SMS_GLOBAL_FROM = "ssvc";
    protected static final String SMS_GLOBAL_ENCODING = "UTF-8";
    protected static final String SMS_GLOBAL_ENDPOINT = "http://www.smsglobal.com/http-api.php";

    protected static final String RED_OXYGEN_SMS_MODE = "red_oxygen";
    protected static final String RED_OXYGEN_ACTION = "SendSMS";
    protected static final String RED_OXYGEN_ENCODING = "UTF-8";
    protected static final String RED_OXYGEN_ENDPOINT = "http://www.redoxygen.net/sms.dll";

    protected static final String BULK_SMS_SMS_MODE = "bulk_sms";
    protected static final String BULK_SMS_FROM = "ssvc";
    protected static final String BULK_SMS_ENCODING = "UTF-8";
    protected static final String BULK_SMS_ENDPOINT = "http://bulksms.vsms.net:5567/eapi/submission/send_sms/2/2.0";

    @Value("${sms.mode}")
    private String smsMode;

    @Autowired
    protected ConfigurationService configurationService;

    @Override
    public void sendNotification(String subject, String message, String destination) {
        switch(smsMode) {
            case DUMMY_SMS_MODE:
                try {
                    logger.info("Sending message [" + message + "] to destination: " + destination + ". Encoded message: " + URLEncoder.encode(replaceAccents(message), DUMMY_ENCODING));
                } catch (IOException ex) {
                    logger.error(ex.getMessage());
                }
                break;
            case SMS_GLOBAL_SMS_MODE:
                InputStreamReader smsGlobalISR = null;
                BufferedReader smsGlobalBR = null;
                StringBuilder smsGlobalSB = new StringBuilder(50);
                try {
                    Map<String, String> parameters = new LinkedHashMap<>();
                    parameters.put("action", SMS_GLOBAL_ACTION);
                    parameters.put("user", configurationService.getSMSSenderUsername());
                    parameters.put("password", configurationService.getSMSSenderPassword());
                    parameters.put("from", SMS_GLOBAL_FROM);
                    parameters.put("to", destination);
                    parameters.put("text", URLEncoder.encode(replaceAccents(message), SMS_GLOBAL_ENCODING));

                    //SMS Endpoint
                    smsGlobalSB.append(SMS_GLOBAL_ENDPOINT).append('?');
                    for(Map.Entry<String, String> entry : parameters.entrySet()) {
                        smsGlobalSB.append(entry.getKey()).append('=').append(entry.getValue());
                        smsGlobalSB.append('&');
                    }
                    if(smsGlobalSB.toString().endsWith("&")) {
                        smsGlobalSB.deleteCharAt(smsGlobalSB.lastIndexOf("&"));
                    }

                    logger.debug("URL: " + smsGlobalSB);

                    URL url = new URL(smsGlobalSB.toString());
                    URLConnection urlConnection = url.openConnection();
                    smsGlobalISR = new InputStreamReader(urlConnection.getInputStream());
                    smsGlobalBR = new BufferedReader(smsGlobalISR);

                    StringBuilder response = new StringBuilder();
                    String line;
                    while ((line = smsGlobalBR.readLine()) != null) {
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
                    logger.error(ex.getMessage() + " " + smsGlobalSB);
                } finally {
                    if(smsGlobalISR != null) {
                        try {
                            smsGlobalISR.close();
                        } catch (IOException ignore) {
                        }
                    }
                    if(smsGlobalBR != null) {
                        try {
                            smsGlobalBR.close();
                        } catch (IOException ignore) {
                        }
                    }
                }
                break;
            case RED_OXYGEN_SMS_MODE:
                InputStreamReader redOxygenISR = null;
                BufferedReader redOxygenBR = null;
                StringBuilder redOxygenSB = new StringBuilder(50);
                try {
                    Map<String, String> parameters = new LinkedHashMap<>();
                    parameters.put("Action", RED_OXYGEN_ACTION);
                    parameters.put("AccountId", configurationService.getSMSSenderUsername());
                    parameters.put("Email", configurationService.getEmailSenderFrom());
                    parameters.put("Password", configurationService.getSMSSenderPassword());
                    parameters.put("Recipient", destination);
                    parameters.put("Message", URLEncoder.encode(replaceAccents(message), RED_OXYGEN_ENCODING));

                    //SMS Endpoint
                    redOxygenSB.append(RED_OXYGEN_ENDPOINT).append('?');
                    for(Map.Entry<String, String> entry : parameters.entrySet()) {
                        redOxygenSB.append(entry.getKey()).append('=').append(entry.getValue());
                        redOxygenSB.append('&');
                    }
                    if(redOxygenSB.toString().endsWith("&")) {
                        redOxygenSB.deleteCharAt(redOxygenSB.lastIndexOf("&"));
                    }

                    logger.debug("URL: " + redOxygenSB);

                    URL url = new URL(redOxygenSB.toString());
                    URLConnection urlConnection = url.openConnection();
                    redOxygenISR = new InputStreamReader(urlConnection.getInputStream());
                    redOxygenBR = new BufferedReader(redOxygenISR);

                    StringBuilder response = new StringBuilder();
                    String line;
                    while ((line = redOxygenBR.readLine()) != null) {
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
                    logger.error(ex.getMessage() + " " + redOxygenSB);
                } finally {
                    if(redOxygenISR != null) {
                        try {
                            redOxygenISR.close();
                        } catch (IOException ignore) {
                        }
                    }
                    if(redOxygenBR != null) {
                        try {
                            redOxygenBR.close();
                        } catch (IOException ignore) {
                        }
                    }
                }
                break;
            case BULK_SMS_SMS_MODE:
                InputStreamReader bulkSmsISR = null;
                BufferedReader bulkSmsBR = null;
                StringBuilder bulkSmsSB = new StringBuilder(50);
                try {
                    Map<String, String> parameters = new LinkedHashMap<>();
                    parameters.put("username", configurationService.getSMSSenderUsername());
                    parameters.put("password", configurationService.getSMSSenderPassword());
                    parameters.put("msisdn", destination);
                    parameters.put("sender", BULK_SMS_FROM);
                    parameters.put("repliable", "0");
                    parameters.put("message", URLEncoder.encode(replaceAccents(message), BULK_SMS_ENCODING));
                    //parameters.put("test_always_succeed", "1");

                    //SMS Endpoint
                    bulkSmsSB.append(BULK_SMS_ENDPOINT).append('?');
                    for(Map.Entry<String, String> entry : parameters.entrySet()) {
                        bulkSmsSB.append(entry.getKey()).append('=').append(entry.getValue());
                        bulkSmsSB.append('&');
                    }
                    if(bulkSmsSB.toString().endsWith("&")) {
                        bulkSmsSB.deleteCharAt(bulkSmsSB.lastIndexOf("&"));
                    }

                    logger.debug("URL: " + bulkSmsSB);

                    URL url = new URL(bulkSmsSB.toString());
                    URLConnection urlConnection = url.openConnection();
                    bulkSmsISR = new InputStreamReader(urlConnection.getInputStream());
                    bulkSmsBR = new BufferedReader(bulkSmsISR);

                    StringBuilder response = new StringBuilder();
                    String line;
                    while ((line = bulkSmsBR.readLine()) != null) {
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
                    logger.error(ex.getMessage() + " " + bulkSmsSB);
                } finally {
                    if(bulkSmsISR != null) {
                        try {
                            bulkSmsISR.close();
                        } catch (IOException ignore) {
                        }
                    }
                    if(bulkSmsBR != null) {
                        try {
                            bulkSmsBR.close();
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
