package pidev.elbey.Services;

import lombok.extern.slf4j.Slf4j;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

import pidev.elbey.Entities.*;
import pidev.elbey.Repositories.*;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Month;
import java.util.*;

@Service
@Slf4j

public class DeliveryService implements IDeliveryService {
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
    private twilioService twilioService;
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public Delivery addDelivery(Delivery delivery) {
        log.info("hi");
        return deliveryRepo.save(delivery);

    }

    @Override
    public List<Delivery> getdeliveries() {
        return deliveryRepo.findAll();
    }

    @Override
    public Delivery updateDelivery(Delivery delivery) {
        return deliveryRepo.save(delivery);
    }

    @Override
    public void deleteDelivery(Long id) {
        deliveryRepo.deleteById(id);
    }

    public List<Delivery> search(String rech) {
        if (rech != null) {
            return deliveryRepo.search(rech);
        }
        return deliveryRepo.findAll();
    }

    public Delivery addOrderToDelivery(Orders o, long idDelivery) {
        Delivery delivery = deliveryRepo.findById(idDelivery).get();
        // Order child
        // Delivery master
        // on affecte child au master ==> Ajoute cascade
        delivery.getOrders().add(o);
        return deliveryRepo.save(delivery);
    }

    public DeliveryService(twilioService twilioService) {
        this.twilioService = twilioService;
    }

    public void SMSDeliveryTracking(long idDelivery, long idUser) {

        Delivery delivery = deliveryRepo.findById(idDelivery).orElse(null);
        User client = userRepo.findById(idUser).orElse(null);


        if (delivery == null || client == null) {
            throw new IllegalArgumentException("Delivery or user not found");
        }
        if (idDelivery != delivery.getIdDelivery() || idUser != client.getId()) {
            throw new IllegalArgumentException("Delivery ID or user ID does not match");
        }
        boolean isClient = false;
        for (Roles role : client.getRoles()) {
            if ("client".equalsIgnoreCase(role.getName())) {
                isClient = true;
                break;
            }
        }
        if (!isClient) {
            String message = "Sorry, you are not a client and cannot receive delivery updates!";
            twilioService.sendSms(client.getPhoneNumber(), message);
            return;
        }
        DeliveryStatus status = delivery.getDeliveryStatus();
        if (status == DeliveryStatus.DELIVERED) {
            String message = "Dear M/Ms " + client.getName() + ", Your delivery has been delivered!";
            twilioService.sendSms(client.getPhoneNumber(), message);
        } else if (status == DeliveryStatus.IN_TRANSIT) {
            String message = "Dear M/Ms " + client.getName() + ", Your delivery is in transit!";
            twilioService.sendSms(client.getPhoneNumber(), message);
        } else if (status == DeliveryStatus.CANCELED) {
            String message = "Dear M/Ms " + client.getName() + ", Your delivery has been canceled";
            twilioService.sendSms(client.getPhoneNumber(), message);
        } else {
            throw new IllegalArgumentException("Delivery status is not recognized");
        }
    }

    public Delivery assignDeliveryToUser(Long idDelivery, Long idUser) {
        Delivery delivery = deliveryRepo.findById(idDelivery).get();
        User deliveryPerson = userRepo.findById(idUser).get();
        if (delivery == null || deliveryPerson == null) {
            log.info("Delivery or delivery person not found");
            return null;
        }
        boolean foundRole = false;
        for (Roles role : deliveryPerson.getRoles()) {
            if (role.getName().equalsIgnoreCase("DELIVERY") || role.getName().equalsIgnoreCase("CLIENT")) {
                foundRole = true;
                break;
            }
        }
        if (!foundRole) {
            log.info("This user is not a delivery person or a client");
            return null;
        }
        delivery.setUser(deliveryPerson);
        delivery = deliveryRepo.save(delivery);

        // Add the delivery to the delivery person's deliveries list
        deliveryPerson.getDeliveries().add(delivery);
        userRepo.save(deliveryPerson);

        log.info("Delivery assigned to user successfully");
        return delivery;
    }

    public int getDeliveryCountForMonth(int year, int month) {
        int deliveryCount = 0;
        List<Delivery> deliveries = deliveryRepo.findByDeliveryStatus(DeliveryStatus.DELIVERED);
        for (Delivery delivery : deliveries) {
            Date deliveryDate = delivery.getDateDelivery();
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(deliveryDate);

            int deliveryYear = calendar.get(Calendar.YEAR);
            int deliveryMonth = calendar.get(Calendar.MONTH) + 1; // Months in Calendar are 0-indexed

            if (deliveryYear == year && deliveryMonth == month) {
                deliveryCount++;
            }
        }
        return deliveryCount;
    }

    public Delivery acceptOrDenyShipment(Long idDelivery, Long idUser, boolean accepted) {
        Delivery delivery = deliveryRepo.findById(idDelivery).orElse(null);
        User deliveryPerson = userRepo.findById(idUser).get();
        if (delivery == null || deliveryPerson == null) {
            log.info("Delivery or delivery person not found");
            return null;
        }
        if (delivery.getDeliveryStatus() == DeliveryStatus.IN_TRANSIT) {
            log.info("Delivery is already in transit, can't accept or deny shipment");
            return null;
        }
        boolean foundRole = false;
        for (Roles role : deliveryPerson.getRoles()) {
            if (role.getName().equalsIgnoreCase("DELIVERY") ){
                foundRole = true;
                break;
            }
        }
        if (!foundRole) {
            log.info("This user is not a delivery person ");
            return null;
        }




        if (accepted) {
            delivery.setUser(deliveryPerson);
            delivery = deliveryRepo.save(delivery);

        } else {
            delivery.setDeliveryStatus(DeliveryStatus.DENIED);
            delivery = deliveryRepo.save(delivery);
            log.info("Delivery still in panding");
        }

        return delivery;
    }







    public DeliveryStatus getDeliveryStatus(Long deliveryId) {
        Delivery delivery = deliveryRepo.findById(deliveryId)
                .orElseThrow(() -> new IllegalArgumentException("Invalid delivery ID"));
        return delivery.getDeliveryStatus();

    }
}




