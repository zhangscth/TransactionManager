package com.zsc.transactionmanager.controller;

import ch.qos.logback.core.util.StringUtil;
import com.zsc.transactionmanager.entity.TransactionRequest;
import com.zsc.transactionmanager.entity.Transaction;
import com.zsc.transactionmanager.service.TransactionService;
import jakarta.validation.Valid;
import org.slf4j.LoggerFactory;
import org.slf4j.Logger;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.InvalidParameterException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/")
public class TransactionController {

    private static final Logger LOGGER = LoggerFactory.getLogger(TransactionController.class);

    @Autowired
    private TransactionService transactionService;

    @RequestMapping(value = "/transaction", method = RequestMethod.POST)
    public ResponseEntity<Transaction> create(@Valid @RequestBody TransactionRequest request) {
        Transaction transaction = new Transaction();
        BeanUtils.copyProperties(request, transaction);
        transaction.setTimestamp(LocalDateTime.now());
        String id = UUID.randomUUID().toString();
        transaction.setId(id);
        return ResponseEntity.ok(transactionService.createTransaction(transaction));
    }

    @RequestMapping(value = "/transaction", method = RequestMethod.GET)
    public ResponseEntity<List<Transaction>> list(@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "10") int size) {
        if (page < 0) {
            LOGGER.error("invalid page");
            throw new InvalidParameterException("invalid page");
        }
        if (size <= 0 || size > 100) {
            LOGGER.error("invalid page size");
            throw new InvalidParameterException("invalid size");
        }
        List<Transaction> allTransactions = transactionService.getAllTransactions(page, size);
        return ResponseEntity.ok(allTransactions);
    }

    @RequestMapping(value = "/transaction", method = RequestMethod.PUT)
    public ResponseEntity<List<Transaction>> update(@Valid @RequestBody TransactionRequest request) {
        if (StringUtil.isNullOrEmpty(request.getId())) {
            LOGGER.error("invalid id");
            throw new InvalidParameterException("invalid id");
        }
        Transaction transaction = new Transaction();
        BeanUtils.copyProperties(request, transaction);
        transactionService.update(transaction);
        return ResponseEntity.ok().build();
    }

    @RequestMapping(value = "/transaction/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<List<Transaction>> delete(@PathVariable String id) {
        if (StringUtil.isNullOrEmpty(id)) {
            LOGGER.error("invalid id");
            throw new InvalidParameterException("invalid id");
        }
        transactionService.deleteTransaction(id);
        return ResponseEntity.ok().build();
    }
}