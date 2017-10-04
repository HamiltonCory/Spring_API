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

    @RequestMapping( method = RequestMethod.POST, consumes =  MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity postApplication(){
        System.out.println("Creating account");
        Account account = new Account();

        if (account != null) {
            return ResponseEntity.ok().body(account);
        } else {
            return ResponseEntity.badRequest().build();
        }
    }

    @RequestMapping(value="/{id}", method = RequestMethod.GET)
    public ResponseEntity getApplication(@PathVariable("id") int id) {
        Account account = businessLogic.retrieveAccount(id);

        if(account != null) {
            return ResponseEntity.ok().body(account);
        } else {
            return ResponseEntity.badRequest().build();
        }
    }
}
