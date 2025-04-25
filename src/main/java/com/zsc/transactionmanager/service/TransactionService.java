package com.zsc.transactionmanager.service;

import com.zsc.transactionmanager.exception.TransactonException;
import com.zsc.transactionmanager.entity.Transaction;
import com.zsc.transactionmanager.mapper.TransactionMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.security.InvalidParameterException;
import java.util.List;

@Service
@CacheConfig(cacheNames = "transactions")
@Transactional
public class TransactionService {

    private static final Logger LOGGER = LoggerFactory.getLogger(TransactionService.class);

    @Autowired
    private TransactionMapper transactionMapper;

    public Transaction createTransaction(Transaction transaction) {
        if (transactionMapper.insert(transaction) != 1) {
            LOGGER.error("Failed to create transaction");
            throw new TransactonException("Failed to create transaction");
        }
        return transaction;
    }

    public List<Transaction> getAllTransactions(int page, int size) {
        int offset = page * size;
        return transactionMapper.findAll(offset, size);
    }

    public void update(Transaction transaction) {
        if (!transactionMapper.findById(transaction.getId()).isPresent()) {
            LOGGER.error("Transaction not exist");
            throw new InvalidParameterException("Transaction not exist");
        }
        transactionMapper.update(transaction);
    }

    public void deleteTransaction(String id) {
        if (!transactionMapper.findById(id).isPresent()) {
            LOGGER.error("Transaction not exist");
            throw new InvalidParameterException("Transaction not exist");
        }
        if (transactionMapper.deleteById(id) != 1) {
            LOGGER.error("Transaction delete failed");
            throw new TransactonException("Transaction delete failed");
        }
    }
}