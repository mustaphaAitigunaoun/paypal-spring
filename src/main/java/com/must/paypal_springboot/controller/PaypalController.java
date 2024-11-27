package com.must.paypal_springboot.controller;

import com.must.paypal_springboot.service.PaypalService;
import com.paypal.api.payments.Links;
import com.paypal.api.payments.Payment;
import com.paypal.base.rest.PayPalRESTException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.view.RedirectView;

@Controller
@RequiredArgsConstructor
@Slf4j
public class PaypalController {
    private final PaypalService paypalService;

    @GetMapping("/")
    public String home() {
        return "index";
    }

    @PostMapping("/payment/create")
    public RedirectView createPayment() {
        try {
            String cancelURL = "http://localhost:8000/payment/cancel";
            String successURL = "http://localhost:8000/payment/success";

            Payment payment = paypalService.payment(
                    10.0,
                    "USD",
                    "paypal",
                    "sale",
                    "Payment Description",
                    cancelURL,
                    successURL
            );

            for(Links links : payment.getLinks()) {
                if(links.getRel().equals("approval_url")){
                    return new RedirectView(links.getHref());
                }
            }

        }catch (PayPalRESTException e) {
            log.error("error payments :: ",e);
        }
        return new RedirectView("/payment/error");
    }

    @GetMapping("/payment/success")
    public String successPayment(
            @RequestParam("paymentId") String paymentId,
            @RequestParam("payerID") String payerId
    ) {
        try {
            Payment payment = paypalService.paymentExecute(
                   paymentId,
                   payerId
            );
            if(payment.getState().equals("approved")){
                return "successPayment";
            }
        }catch(PayPalRESTException e) {
            log.error("error success :: ",e);
        }
        return "successPayment";
    }

    @GetMapping("/payment/cancel")
    public String cancelPayment() {
        return "cancelPayment";
    }

    @GetMapping("/payment/error")
    public String errorPayment() {
        return "errorPayment";
    }
}
