package pidev.elbey.Services;

import pidev.elbey.Entities.BillToSeen;
import pidev.elbey.Entities.Delivery;

import java.util.List;

public interface IBillService {
    BillToSeen addBillToSeen(BillToSeen billToSeen);
    List<BillToSeen> getBills();
    BillToSeen updateBillToSeen(BillToSeen billToSeen);
    void deleteBillToSeen(Long id);
    float getTotalAmountForBills(List<BillToSeen> bills);
    BillToSeen assignDeliveryToBill(Long deliveryId, Long billId);

}
