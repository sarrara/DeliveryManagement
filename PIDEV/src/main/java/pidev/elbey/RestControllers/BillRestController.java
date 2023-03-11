package pidev.elbey.RestControllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pidev.elbey.Entities.BillToSeen;

import pidev.elbey.Services.IBillService;

import java.util.List;
@RestController
@RequestMapping("/Bill")
public class BillRestController {

    @Autowired
    IBillService billService;

    @PostMapping("/billToSeen/add")
    BillToSeen addBillToSeen(@RequestBody BillToSeen billToSeen) {
        return billService.addBillToSeen(billToSeen);
    }

    @GetMapping("/Bills")
    List<BillToSeen> getBills() {
        return billService.getBills();
    }

    @PutMapping("/BillToSeen/update")
    BillToSeen updateBillToSeen(@RequestBody BillToSeen billToSeen) {
        return billService.updateBillToSeen(billToSeen);
    }

    @DeleteMapping("/{idBillToSeen}")
    public void deleteBillToSeen(@PathVariable Long idBillToSeen) {
        billService.deleteBillToSeen(idBillToSeen);
    }

    @PutMapping("/{deliveryId}/{billId}")
    public BillToSeen assignDeliveryToBill(@PathVariable Long deliveryId, @PathVariable Long billId) {
        return billService.assignDeliveryToBill(deliveryId, billId);
    }
}