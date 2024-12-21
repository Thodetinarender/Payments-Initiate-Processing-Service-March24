package com.cpt.payments.service.impl.status.handler;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoInteractions;
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

@ExtendWith(MockitoExtension.class)
public class PendingTransactionStatusHandlerTest {

    @Mock
    private TransactionDao transactionDao;

    @Mock
    private TransactionLogDao transactionLogDao;

    @InjectMocks
    private PendingTransactionStatusHandler pendingTransactionStatusHandler;

    @Test
    void testUpdateStatus() {
        // Arrange
        Transaction transaction = new Transaction();
        transaction.setId(1); // Assuming id is set
        
        // Mocking behavior of the TransactionDao
        when(transactionDao.getTransactionById(transaction.getId()))
            .thenReturn(transaction); // Return the same transaction
        
        when(transactionDao.updateTransaction(transaction))
            .thenReturn(true); // Assuming updateTransaction returns true
        
        // Act
        boolean result = pendingTransactionStatusHandler.updateStatus(transaction);

        // Assert
        assertTrue(result); // Assuming updateStatus should return true
        assertEquals(TransactionStatusEnum.PENDING.getId(), transaction.getTxnStatusId());
        assertEquals(TransactionStatusEnum.PENDING.getId(), transaction.getTxnDetailsId());
        verify(transactionDao).getTransactionById(transaction.getId());
        verify(transactionDao).updateTransaction(transaction);
        verify(transactionLogDao).createTransactionLog(any()); // Ensure transaction log is created
    }
    
    @Test
    void testUpdateStatus_SuccessfulUpdate() {
        // Arrange
        Transaction transaction = new Transaction();
        transaction.setId(1); // Assuming id is set

        // Mocking behavior of the TransactionDao
        when(transactionDao.getTransactionById(transaction.getId())).thenReturn(transaction); // Return the same transaction
        when(transactionDao.updateTransaction(transaction)).thenReturn(true); // Assuming updateTransaction returns true

        // Act
        boolean result = pendingTransactionStatusHandler.updateStatus(transaction);

        // Assert
        assertTrue(result); // Assuming updateStatus should return true
        assertEquals(TransactionStatusEnum.PENDING.getId(), transaction.getTxnStatusId());
        assertEquals(TransactionStatusEnum.PENDING.getId(), transaction.getTxnDetailsId());
        verify(transactionDao).getTransactionById(transaction.getId());
        verify(transactionDao).updateTransaction(transaction);
        verify(transactionLogDao).createTransactionLog(any()); // Ensure transaction log is created
    }

    @Test
    void testUpdateStatus_FailedUpdate() {
        // Arrange
        Transaction transaction = new Transaction();
        transaction.setId(1); // Assuming id is set

        // Mocking behavior of the TransactionDao
        when(transactionDao.getTransactionById(transaction.getId())).thenReturn(transaction); // Return the same transaction
        when(transactionDao.updateTransaction(transaction)).thenReturn(false); // Assuming updateTransaction returns false

        // Act
        boolean result = pendingTransactionStatusHandler.updateStatus(transaction);

        // Assert
        assertFalse(result); // Assuming updateStatus should return false
        assertEquals(TransactionStatusEnum.PENDING.getId(), transaction.getTxnStatusId());
        assertEquals(TransactionStatusEnum.PENDING.getId(), transaction.getTxnDetailsId());
        verify(transactionDao).getTransactionById(transaction.getId());
        verify(transactionDao).updateTransaction(transaction);
        // No need to verify transaction log creation in case of a failed update
        verifyNoInteractions(transactionLogDao);
    }

}
