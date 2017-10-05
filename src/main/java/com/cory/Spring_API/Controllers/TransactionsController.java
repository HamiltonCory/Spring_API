package com.cory.Spring_API.Controllers;

import com.cory.Spring_API.Models.Account;
import com.cory.Spring_API.Models.Transaction;
import com.cory.Spring_API.Service.BusinessLogic;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value="/transactions")
public class TransactionsController {
    /* Remember to include "Content-Type:application/json" in the header of the http request */

    @Autowired
    private BusinessLogic businessLogic;

    @RequestMapping( method = RequestMethod.POST, consumes =  MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity postApplication(@RequestBody Transaction newTransaction){
        Account account = businessLogic.retrieveAccount(newTransaction.getAccountId());
        Transaction transaction = businessLogic.postTransaction(newTransaction);
        businessLogic.updateLedger(account);

        if (transaction != null) {
            return ResponseEntity.ok().body(transaction);
        } else {
            return ResponseEntity.badRequest().build();
        }
    }
}
