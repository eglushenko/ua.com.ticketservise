package ua.com.ticketservise.controller;


import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ua.com.ticketservise.model.Pay;


@RestController
public class PayController {
    @PostMapping("/pay")
    String newPayment(@RequestParam long id) {
        Pay pay = new Pay();
        String statusPayment = pay.getStatus();


        return statusPayment;
    }
}
