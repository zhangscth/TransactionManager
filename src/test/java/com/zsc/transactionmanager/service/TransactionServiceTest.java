package com.zsc.transactionmanager.service;

import com.zsc.transactionmanager.entity.Transaction;
import com.zsc.transactionmanager.exception.TransactonException;
import com.zsc.transactionmanager.mapper.TransactionMapper;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.math.BigDecimal;
import java.security.InvalidParameterException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

public class TransactionServiceTest {

    @InjectMocks
    private TransactionService transactionService;

    @Mock
    private TransactionMapper transactionMapper;

    @Before
    public void setUp() {
        transactionService = new TransactionService();
        MockitoAnnotations.openMocks(this); // 初始化 Mock 注解
    }


    @Test
    public void test_createTransaction_pass_when_create_succ() {
        Transaction transaction = new Transaction();
        transaction.setAmount(BigDecimal.valueOf(10.0));
        transaction.setDescription("123123");
        transaction.setType("debit");
        when(transactionMapper.insert(any())).thenReturn(1);
        Transaction transaction1 = transactionService.createTransaction(transaction);
        Assert.assertNotNull(transaction1);
    }

    @Test(expected= TransactonException.class)
    public void test_createTransaction_pass_when_transaction_is_exist() {
        Transaction transaction = new Transaction();
        transaction.setAmount(BigDecimal.valueOf(10.0));
        transaction.setDescription("123123");
        transaction.setType("debit");
        when(transactionMapper.insert(any())).thenReturn(-1);
        transactionService.createTransaction(transaction);
    }

    @Test
    public void test_getAllTransactions() {
        int page = 10;
        int size = 10;
        List<Transaction> allTransactions = new ArrayList<>();
        allTransactions.add(new Transaction());
        when(transactionMapper.findAll(anyInt(), anyInt())).thenReturn(allTransactions);
        List<Transaction> allTransactionRes = transactionService.getAllTransactions(page, size);
        Assert.assertNotNull(allTransactionRes);
        Assert.assertEquals(1, allTransactionRes.size());
    }

    @Test(expected = Exception.class)
    public void test_getAllTransactions_when_mapper_throw_exception() {
        int page = 10;
        int size = 10;
        List<Transaction> allTransactions = new ArrayList<>();
        allTransactions.add(new Transaction());
        when(transactionMapper.findAll(anyInt(), anyInt())).thenReturn(allTransactions);
        doThrow(Exception.class).when(transactionMapper).findAll(anyInt(), anyInt());
        transactionService.getAllTransactions(page, size);
    }

    @Test
    public void test_update() {
        Transaction transaction = new Transaction();
        transaction.setAmount(BigDecimal.valueOf(10.0));
        transaction.setDescription("123123");
        transaction.setType("debit");
        transaction.setId("id");
        when(transactionMapper.findById(anyString())).thenReturn(Optional.of(transaction));
        transactionService.update(transaction);
    }

    @Test(expected = InvalidParameterException.class)
    public void test_update_when_transaction_is_not_exist() {
        Transaction transaction = Mockito.mock(Transaction.class);
        when(transactionMapper.findById(anyString())).thenReturn(Optional.ofNullable(null));
        doNothing().when(transactionMapper).update(any());
        transactionService.update(transaction);
    }

    @Test
    public void test_deleteTransaction() {
        Transaction transaction = new Transaction();
        String id = "id";
        transaction.setAmount(BigDecimal.valueOf(10.0));
        transaction.setDescription("123123");
        transaction.setType("debit");
        transaction.setId(id);
        when(transactionMapper.findById(anyString())).thenReturn(Optional.ofNullable(transaction));
        when(transactionMapper.deleteById(anyString())).thenReturn(1);
        transactionService.deleteTransaction(id);
    }

    @Test(expected = InvalidParameterException.class)
    public void test_deleteTransaction_when_findById_faild() {
        Transaction transaction = new Transaction();
        String id = "id";
        transaction.setAmount(BigDecimal.valueOf(10.0));
        transaction.setDescription("123123");
        transaction.setType("debit");
        transaction.setId(id);
        when(transactionMapper.findById(anyString())).thenReturn(Optional.ofNullable(null));
        when(transactionMapper.deleteById(anyString())).thenReturn(1);
        transactionService.deleteTransaction(id);
    }

    @Test(expected = TransactonException.class)
    public void test_deleteTransaction_when_mapper_delete_faild() {
        Transaction transaction = new Transaction();
        String id = "id";
        transaction.setAmount(BigDecimal.valueOf(10.0));
        transaction.setDescription("123123");
        transaction.setType("debit");
        transaction.setId(id);
        when(transactionMapper.findById(anyString())).thenReturn(Optional.ofNullable(transaction));
        when(transactionMapper.deleteById(anyString())).thenReturn(-1);
        transactionService.deleteTransaction(id);
    }
}