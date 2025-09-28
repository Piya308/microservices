package com.priyanka.accounts.controller;

import com.priyanka.accounts.constants.AccountConstants;
import com.priyanka.accounts.dto.AccountContactInfoDto;
import com.priyanka.accounts.dto.CustomerDto;
import com.priyanka.accounts.dto.ErrorResponseDto;
import com.priyanka.accounts.dto.ResponseDto;
import com.priyanka.accounts.service.IAccountService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
@Validated
@RestController
@RequestMapping(path = "/api", produces = {MediaType.APPLICATION_JSON_VALUE})
@Tag(name = "Accounts", description = "Operations related to account management")
public class AccountsController {

    @Autowired
    private IAccountService accountService;

    @Value("${build.version}")
    private String buildVersion;

    @Autowired
    private Environment environment;

    @Autowired
    AccountContactInfoDto accountContactInfoDto;

    @Operation(
            summary = "Get build information",
            description = "Http Status Internal Server Error")
    @GetMapping("/build-info")
    public ResponseEntity<String> getBuildVersion(){
        return
                ResponseEntity.status(HttpStatus.OK)
                        .body(buildVersion);
    }

    @Operation(
            summary = "Get java version information",
            description = "Http Status Internal Server Error")
    @GetMapping("/java-version")
    public ResponseEntity<String> getJavaVersion(){
        return
                ResponseEntity.status(HttpStatus.OK)
                        .body(environment.getProperty("JAVA_HOME"));
    }

    @Operation(
            summary = "Get contact info information",
            description = "Http Status Internal Server Error")
    @GetMapping("/contact-info")
    public ResponseEntity<AccountContactInfoDto> getContactInfo(){
        return
                ResponseEntity.status(HttpStatus.OK)
                        .body(accountContactInfoDto);
    }

    @Operation(
            summary = "create account by",
            description = "Create account given customer",
            tags = {"Accounts"},
            responses = {
                    @ApiResponse(responseCode = "201", description = "Account created"),
                    @ApiResponse(responseCode = "500", description = "Https- Internal Server Error")
            }
    )
    @PostMapping("create")
    public ResponseEntity<ResponseDto> createAccount( @Valid @RequestBody CustomerDto customerDto) {
        accountService.createAccount(customerDto);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(new ResponseDto(AccountConstants.STATUS_201, AccountConstants.MESSAGE_201));
    }
    @Operation(
            summary = "Get account by ID",
            description = "Fetches account details for a given account number",
            tags = {"Accounts"},
            responses = {
                    @ApiResponse(responseCode = "200", description = "Successful retrieval",
                    content = @Content(
                            schema = @Schema(implementation = ErrorResponseDto.class)
                    )
                    ),
                    @ApiResponse(responseCode = "404", description = "Account not found")
            }
    )
    @GetMapping("/fetch")
    public ResponseEntity<CustomerDto> fetchAccounts(@Valid @RequestParam @Pattern(regexp = "(^$|[0-9]{10})", message = "please enter valid mobile number")
                                                          String mobileNumber){
        CustomerDto customerDto = accountService.fetchAccount(mobileNumber);
        return ResponseEntity.status(HttpStatus.OK)
                .body(customerDto);
    }

    @Operation(
            summary = "Update account by ID",
            description = "Modifies existing account details using the account number",
            tags = {"Accounts"},
            responses = {
                    @ApiResponse(responseCode = "200", description = "Account updated successfully"),
                    @ApiResponse(responseCode = "400", description = "Invalid account data"),
                    @ApiResponse(responseCode = "404", description = "Account not found"),
                    @ApiResponse(responseCode = "500", description = "Internal server error")
            }
    )
    @PutMapping("/update")
    public ResponseEntity<ResponseDto> updateAccount(@Valid @RequestBody CustomerDto customerDto){
        boolean isUpdated = accountService.updateAccount(customerDto);
        if(isUpdated){
            return ResponseEntity
                    .status(HttpStatus.CREATED)
                    .body(new ResponseDto(AccountConstants.STATUS_200, AccountConstants.MESSAGE_200));
        }else {
            return ResponseEntity
                    .status(HttpStatus.EXPECTATION_FAILED)
                    .body(new ResponseDto(AccountConstants.STATUS_417_UPDATE  , AccountConstants.MESSAGE_417_UPDATE));
        }
    }

    @Operation(
            summary = "Delete account by ID",
            description = "Removes the account with the specified account number from the system",
            tags = {"Accounts"},
            responses = {
                    @ApiResponse(responseCode = "200", description = "Account deleted successfully"),
                    @ApiResponse(responseCode = "404", description = "Account not found"),
                    @ApiResponse(responseCode = "500", description = "Internal server error")
            }
    )
    @DeleteMapping("/delete")
    public ResponseEntity<ResponseDto> deleteAccount(@RequestParam String mobileNumber) {
        boolean isDeleted = accountService.deleteAccount(mobileNumber);
        if (isDeleted) {
            return ResponseEntity
                    .status(HttpStatus.CREATED)
                    .body(new ResponseDto(AccountConstants.STATUS_200, AccountConstants.MESSAGE_200));
        } else {
          return  ResponseEntity
                    .status(HttpStatus.EXPECTATION_FAILED)
                    .body(new ResponseDto(AccountConstants.STATUS_417_DELETE, AccountConstants.MESSAGE_417_DELETE));
        }
    }

}
