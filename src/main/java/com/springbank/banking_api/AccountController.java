package com.springbank.banking_api;

import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
public class AccountController {

    private final AccountRepository accountRepository;
    private final TransactionRepository transactionRepository;

    public AccountController(AccountRepository accountrepository, TransactionRepository transactionRepository) {
        this.accountRepository = accountrepository;
        this.transactionRepository = transactionRepository;
    }

    @GetMapping("/balance")
    public AccountResponse getBalance() {
        Account account = accountRepository.findById("Acc123")
                .orElseThrow(()->new RuntimeException("Account not found"));
        return new AccountResponse(account.getAccountNumber(), account.getOwnerName(), account.getBalance());
    }

    @PostMapping("/deposit")
    public AccountResponse deposit(@RequestBody @Valid AmountRequest request) {
        Account account = accountRepository.findById("Acc123")
                        .orElseThrow(()->new RuntimeException("Account not found"));
        account.deposit(request.getAmount());
        accountRepository.save(account);
        transactionRepository.save(new Transaction(account.getAccountNumber(), "DEPOSIT", request.getAmount(), LocalDateTime.now()));
        return new AccountResponse(account.getAccountNumber(), account.getOwnerName(), account.getBalance());
    }

    @PostMapping("/withdraw")
    public AccountResponse withdraw(@RequestBody @Valid AmountRequest request) {
        Account account = accountRepository.findById("Acc123")
                        .orElseThrow(()->new RuntimeException("Account not found"));
       account.withdraw(request.getAmount());
       accountRepository.save(account);
       transactionRepository.save(new Transaction(account.getAccountNumber(), "WITHDRAW", request.getAmount(), LocalDateTime.now()));
       return new AccountResponse(account.getAccountNumber(), account.getOwnerName(), account.getBalance());
    }

    @GetMapping("/transactions")
    public List<Transaction> getTransactions() {
        return transactionRepository.findByAccountNumberOrderByTimestampDesc("Acc123");
    }
    @ExceptionHandler(InsufficientFundsException.class)
    public ResponseEntity<String> handleInsufficientFunds(InsufficientFundsException ex) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<String> handleIllegalArgumentException(IllegalArgumentException ex) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<String> handleValidException(MethodArgumentNotValidException ex) {
        FieldError fieldError = ex.getBindingResult().getFieldError();
        String message = (fieldError != null) ? fieldError.getDefaultMessage() : "Invalid request";
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(message);
    }
}
