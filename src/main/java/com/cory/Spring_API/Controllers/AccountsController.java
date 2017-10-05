package com.cory.Spring_API.Controllers;

import com.cory.Spring_API.Models.Account;
import com.cory.Spring_API.Service.BusinessLogic;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value="/accounts")
public class AccountsController {

    @Autowired
    private BusinessLogic businessLogic;

    @RequestMapping( method = RequestMethod.POST)
    public ResponseEntity postAccount(){
        Account account = businessLogic.postAccount(new Account());
        if (account != null) {
            return ResponseEntity.ok().body("New account created with ID: " + account.getAccountId());
        } else {
            return ResponseEntity.badRequest().build();
        }
    }

    @RequestMapping(value="/{id}", method = RequestMethod.GET)
    public ResponseEntity getAccount(@PathVariable("id") int id) {
        Account account = businessLogic.retrieveAccount(id);
        if(account != null) {
            return ResponseEntity.ok().body(account);
        } else {
            return ResponseEntity.badRequest().build();
        }
    }

    @RequestMapping(value="/{id}/ledger", method = RequestMethod.GET)
    public ResponseEntity getAccountLedger(@PathVariable("id") int id) {
        Account account = businessLogic.retrieveAccount(id);

        if(account != null) {
            String ledger = account.viewLedger();
            return ResponseEntity.ok().body(ledger);
        } else {
            return ResponseEntity.badRequest().build();
        }
    }
}
