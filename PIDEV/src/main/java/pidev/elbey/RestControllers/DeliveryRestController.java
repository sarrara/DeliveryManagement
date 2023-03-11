package pidev.elbey.RestControllers;

import com.google.zxing.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.*;
import pidev.elbey.Entities.*;

import pidev.elbey.Repositories.DeliveryRepo;
import pidev.elbey.Services.IDeliveryService;


import java.time.Month;
import java.util.List;


@RestController
@RequestMapping("/Delivery")

public class DeliveryRestController {
    @Autowired
    IDeliveryService deliveryService;
    @Autowired
    private DeliveryRepo deliveryRepo;

    // CRUD
    @PostMapping("/add")
    Delivery addDelivery(@RequestBody Delivery d) {
        return deliveryService.addDelivery(d);
    }

    @GetMapping("/deliveries")
    List<Delivery> getDeliveries() {
        return deliveryService.getdeliveries();
    }

    @PutMapping("/delivery/update")
    Delivery updateDelivery(@RequestBody Delivery delivery) {
        return deliveryService.updateDelivery(delivery);
    }

    @DeleteMapping("/delete/{idDelivery}")
    public void deleteDelivery(@PathVariable Long idDelivery) {
        deliveryService.deleteDelivery(idDelivery);
    }

    //assign
    @GetMapping("/search/{Destination}")
    public List<Delivery> searchDelivery(@PathVariable("Destination") String keyword) {
        List<Delivery> delivery = deliveryService.search(keyword);
        return delivery;
    }




    @PostMapping("/{idDelivery}/orders")
    public Delivery addOrderToDelivery(@RequestBody Orders o, @PathVariable Long idDelivery) {
        return deliveryService.addOrderToDelivery(o, idDelivery);
    }

    @GetMapping("/deliveries/{idDelivery}/users/{idUser}/suivie")
    public void Suivie(@PathVariable("idDelivery") long idDelivery,
                                     @PathVariable("idUser") long idUser) {
        deliveryService.SMSDeliveryTracking(idDelivery, idUser);
    }
    @GetMapping("/{year}/{month}")
    public int getDeliveryCountForMonth(@PathVariable int year,  @PathVariable int month) {

        return deliveryService.getDeliveryCountForMonth(year,month);
    }
    @PutMapping("/{idDelivery}/user/{idUser}")
    public Delivery assignDeliveryToUser(@PathVariable Long idDelivery, @PathVariable Long idUser) {
        return deliveryService.assignDeliveryToUser(idDelivery, idUser);
    }
    @PutMapping("/{idDelivery}/acceptOrDenyShipment/{idUser}/{accepted}")
    public  Delivery acceptOrDenyShipment(@PathVariable Long idDelivery, @PathVariable Long idUser, @PathVariable boolean accepted) {
        return deliveryService.acceptOrDenyShipment(idDelivery, idUser,accepted);
    }

}
