package pidev.elbey.Services;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pidev.elbey.Entities.BillToSeen;
import pidev.elbey.Entities.Delivery;
import pidev.elbey.Entities.Orders;
import pidev.elbey.Repositories.*;

import java.util.List;
@Service
@Slf4j

public class BillService implements IBillService{
    @Autowired
    UserRepo userRepo;
    @Autowired
    RolesRepo rolesRepo;
    @Autowired
    DeliveryRepo deliveryRepo;
    @Autowired
    BillToSeenRepo billToSeenRepo;
    @Autowired
    OrderRepo orderRepo;

    /*public BillToSeen getBillToSeen(long id) {
        return billToSeenRepo.findById(id).get();
    }*/

    public float getTotalAmountForBill(BillToSeen bill) {
        float totalHT = bill.getTotalHT();
        float TVA = bill.getTva();
        float totalTTC = totalHT + (totalHT * TVA / 100);
        return totalTTC;
    }
    public float getTotalAmountForBills(List<BillToSeen> bills) {
        double totalAmount = 0.0;
        for (BillToSeen bill : bills) {
            totalAmount += getTotalAmountForBill(bill);
        }
        return (float) totalAmount;
    }
    @Override
    public BillToSeen addBillToSeen(BillToSeen b) {

       b.setTotalTTC(b.getTotalHT()+(b.getTotalHT()* b.getTva()/100));

        return billToSeenRepo.save(b);
    }
    public BillToSeen assignDeliveryToBill(Long deliveryId, Long billId) {
        // Get the delivery and bill entities from their IDs
        Delivery delivery = deliveryRepo.findById(deliveryId).get();
        BillToSeen bill = billToSeenRepo.findById(billId).get();

        // Set the delivery for the bill and save the updated entities
        bill.setDelivery(delivery);
        return billToSeenRepo.save(bill);

    }
    @Override
    public List<BillToSeen> getBills() {
        return billToSeenRepo.findAll();
    }

    @Override
    public BillToSeen updateBillToSeen(BillToSeen billToSeen) {
        return billToSeenRepo.save(billToSeen);
    }

    @Override
    public void deleteBillToSeen(Long id) {
        billToSeenRepo.deleteById(id);
    }

}
