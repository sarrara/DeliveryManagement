package pidev.elbey.Services;
import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import pidev.elbey.Entities.Delivery;
import pidev.elbey.Entities.DeliveryStatus;
import pidev.elbey.Entities.User;

@Service
public class twilioService {

    @Value("${twilio.accountsid}")
    private String accountSid;

    @Value("${twilio.authToken}")
    private String authToken;

    @Value("${twilio.trialnumber}")
    private String twilioPhoneNumber;

    public void sendSms(String toPhoneNumber, String message) {
        Twilio.init(accountSid, authToken);
        Message.creator(
                new PhoneNumber(toPhoneNumber),
                new PhoneNumber(twilioPhoneNumber),
                message
        ).create();
    }





}




