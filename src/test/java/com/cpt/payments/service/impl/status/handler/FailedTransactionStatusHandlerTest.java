package com.cpt.payments.service.impl.status.handler;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
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
public class FailedTransactionStatusHandlerTest {

    @Mock
    private TransactionDao transactionDao;

    @Mock
    private TransactionLogDao transactionLogDao;

    @InjectMocks
    private FailedTransactionStatusHandler statusHandler;

    @Test
    void testUpdateStatus_Success() {
        // Arrange
        Transaction transaction = new Transaction();
        transaction.setId(123); // Set transaction ID
        transaction.setTxnStatusId(TransactionStatusEnum.CREATED.getId()); // Initial status

        // Mock behavior of the TransactionDao
        when(transactionDao.getTransactionById(anyLong())).thenReturn(transaction);
        when(transactionDao.updateTransaction(any(Transaction.class))).thenReturn(true);

        // Act
        boolean result = statusHandler.updateStatus(transaction);

        // Assert
        assertTrue(result);
        assertEquals(TransactionStatusEnum.FAILED.getId(), transaction.getTxnStatusId());
        verify(transactionDao, times(1)).updateTransaction(any(Transaction.class));

        // Verify that createTransactionLog method is called
        verify(transactionLogDao, times(1)).createTransactionLog(any(TransactionLog.class));
    }

    @Test
    void testUpdateStatus_UpdateTransactionFailed() {
        // Arrange
        Transaction transaction = new Transaction();
        transaction.setId(123); // Set transaction ID
        transaction.setTxnStatusId(TransactionStatusEnum.CREATED.getId()); // Initial status

        // Mock behavior of the TransactionDao
        when(transactionDao.getTransactionById(anyLong())).thenReturn(transaction);
        when(transactionDao.updateTransaction(any(Transaction.class))).thenReturn(false);

        // Act
        boolean result = statusHandler.updateStatus(transaction);

        // Assert
        assertFalse(result);
        assertEquals(TransactionStatusEnum.FAILED.getId(), transaction.getTxnStatusId()); // Corrected assertion
        verify(transactionDao, times(1)).updateTransaction(any(Transaction.class));
        verify(transactionLogDao, never()).createTransactionLog(any(TransactionLog.class));
    }

 // Add more test cases as needed

}
