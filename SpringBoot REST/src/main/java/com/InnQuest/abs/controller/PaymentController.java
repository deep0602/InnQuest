package com.intellect.abs.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.intellect.abs.dto.PaymentDto;
import com.intellect.abs.service.PaymentService;



@RestController
@RequestMapping("/payments")
@CrossOrigin
public class PaymentController {
	
	@Autowired
    private PaymentService paymentService;

    @PostMapping
    public ResponseEntity<PaymentDto> createPayment(@RequestBody PaymentDto paymentDto) {
        return new ResponseEntity<>(paymentService.createPayment(paymentDto), HttpStatus.CREATED);
    }

    @PutMapping("/{paymentId}")
    public ResponseEntity<PaymentDto> updatePayment(@PathVariable int paymentId, @RequestParam("paymentStatus") String paymentStatus) {
        return new ResponseEntity<>(paymentService.updatePayment(paymentId, paymentStatus), HttpStatus.OK);
    }

    @GetMapping("/{paymentId}")
    public ResponseEntity<PaymentDto> getPayment(@PathVariable int paymentId) {
        return new ResponseEntity<>(paymentService.getPayment(paymentId), HttpStatus.FOUND);
    }

}
