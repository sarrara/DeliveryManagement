package pidev.elbey.Services;

import pidev.elbey.Entities.*;

import java.time.Month;
import java.util.Date;
import java.util.List;

public interface IDeliveryService {
    Delivery addDelivery(Delivery delivery);
    List<Delivery> getdeliveries();
    Delivery updateDelivery(Delivery delivery);
    void deleteDelivery(Long id);
    List<Delivery> search(String rech);

    int getDeliveryCountForMonth(int year, int month);
    Delivery addOrderToDelivery(Orders o, long idDelivery);
    Delivery acceptOrDenyShipment(Long idDelivery, Long idUser, boolean accepted);
    void SMSDeliveryTracking(long idDelivery, long idUser);
    Delivery assignDeliveryToUser(Long idDelivery, Long idUser);
}
