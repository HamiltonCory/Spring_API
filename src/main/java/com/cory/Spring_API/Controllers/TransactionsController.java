package com.cory.Spring_API.Controllers;

import com.cory.Spring_API.Models.Transaction;
import com.cory.Spring_API.Service.BusinessLogic;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value="/transactions")
public class TransactionsController {

    @Autowired
    private BusinessLogic businessLogic;

    @RequestMapping( method = RequestMethod.POST, consumes =  MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity postApplication(@RequestBody Transaction newTransaction){
        System.out.println("Creating account");
        Transaction transaction = new Transaction(newTransaction);

        if (transaction != null) {
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.badRequest().build();
        }
    }
}
