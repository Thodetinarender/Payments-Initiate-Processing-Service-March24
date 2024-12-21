package com.cpt.payments.service.impl.status.handler;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.cpt.payments.constants.TransactionStatusEnum;
import com.cpt.payments.dao.TransactionDao;
import com.cpt.payments.dao.TransactionLogDao;
import com.cpt.payments.dto.Transaction;
import com.cpt.payments.dto.TransactionLog;

@ExtendWith(MockitoExtension.class)
public class CreatedTransactionStatusHandlerTest {

    @Mock
    private TransactionDao transactionDao;

    @Mock
    private TransactionLogDao transactionLogDao;

    @InjectMocks
    private CreatedTransactionStatusHandler statusHandler;

    @Test
    void testUpdateStatus_Success() {
        // Arrange
        Transaction transaction = new Transaction();
        transaction.setId(123); // Set transaction ID
        transaction.setTxnDetailsId(TransactionStatusEnum.CREATED.getId()); // Initial status

        when(transactionDao.createTransaction(any(Transaction.class))).thenReturn(transaction);

        // Act
        boolean result = statusHandler.updateStatus(transaction);

        // Assert
        assertTrue(result);
        assertEquals(TransactionStatusEnum.CREATED.getId(), transaction.getTxnDetailsId());
        verify(transactionDao, times(1)).createTransaction(any(Transaction.class));
        verify(transactionLogDao, times(1)).createTransactionLog(any(TransactionLog.class));
    }

    @Test
    void testUpdateStatus_Failure() {
        // Arrange
        Transaction transaction = new Transaction();
        transaction.setId(123); // Set transaction ID
        transaction.setTxnDetailsId(TransactionStatusEnum.CREATED.getId()); // Initial status

        when(transactionDao.createTransaction(any(Transaction.class))).thenReturn(null);

        // Act
        boolean result = statusHandler.updateStatus(transaction);

        // Assert
        assertFalse(result);
        assertEquals(TransactionStatusEnum.CREATED.getId(), transaction.getTxnDetailsId());
        verify(transactionDao, times(1)).createTransaction(any(Transaction.class));
        verify(transactionLogDao, never()).createTransactionLog(any(TransactionLog.class));
    }
}
