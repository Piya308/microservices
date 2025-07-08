package com.priyanka.accounts.controller;

import com.priyanka.accounts.constants.AccountConstants;
import com.priyanka.accounts.dto.CustomerDto;
import com.priyanka.accounts.dto.ResponseDto;
import com.priyanka.accounts.service.IAccountService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/api", produces = {MediaType.APPLICATION_JSON_VALUE})
@AllArgsConstructor
public class AccountsController {

    private IAccountService accountService;

    @GetMapping("sayHello")
    public String sayHelloWorld(){
        return "Hello World Everyone";
    }

    @PostMapping("create")
    public ResponseEntity<ResponseDto> createAccount(@RequestBody CustomerDto customerDto) {
        accountService.createAccount(customerDto);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(new ResponseDto(AccountConstants.STATUS_201, AccountConstants.MESSAGE_201));
    }
}
