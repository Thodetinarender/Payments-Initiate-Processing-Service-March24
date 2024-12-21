package com.cpt.payments.service.impl.status.handler;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
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

@ExtendWith(MockitoExtension.class)
public class InitiatedTransactionStatusHandlerTest {

    @Mock
    private TransactionDao transactionDao;

    @Mock
    private TransactionLogDao transactionLogDao;

    @InjectMocks
    private InitiatedTransactionStatusHandler initiatedTransactionStatusHandler;

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
        boolean result = initiatedTransactionStatusHandler.updateStatus(transaction);

        // Assert
        assertTrue(result); // Assuming updateStatus should return true
        assertEquals(TransactionStatusEnum.INITIATED.getId(), transaction.getTxnStatusId());
        assertEquals(TransactionStatusEnum.INITIATED.getId(), transaction.getTxnDetailsId());
        verify(transactionDao).getTransactionById(transaction.getId());
        verify(transactionDao).updateTransaction(transaction);
    }
    
    @Test
    void testUpdateStatus_SuccessfulUpdate() {
        // Arrange
        Transaction transaction = new Transaction();
        transaction.setId(1); // Assuming id is set
        
        // Mocking behavior of the TransactionDao
        when(transactionDao.getTransactionById(transaction.getId()))
            .thenReturn(transaction); // Return the same transaction
        
        when(transactionDao.updateTransaction(transaction))
            .thenReturn(true); // Assuming updateTransaction returns true
        
        // Act
        boolean result = initiatedTransactionStatusHandler.updateStatus(transaction);

        // Assert
        assertTrue(result); // Assuming updateStatus should return true
        assertEquals(TransactionStatusEnum.INITIATED.getId(), transaction.getTxnStatusId());
        assertEquals(TransactionStatusEnum.INITIATED.getId(), transaction.getTxnDetailsId());
        verify(transactionDao).getTransactionById(transaction.getId());
        verify(transactionDao).updateTransaction(transaction);
    }

    @Test
    void testUpdateStatus_FailedUpdate() {
        // Arrange
        Transaction transaction = new Transaction();
        transaction.setId(1); // Assuming id is set
        
        // Mocking behavior of the TransactionDao
        when(transactionDao.getTransactionById(transaction.getId()))
            .thenReturn(transaction); // Return the same transaction
        
        when(transactionDao.updateTransaction(transaction))
            .thenReturn(false); // Assuming updateTransaction returns false
        
        // Act
        boolean result = initiatedTransactionStatusHandler.updateStatus(transaction);

        // Assert
        assertFalse(result); // Assuming updateStatus should return false
        assertEquals(TransactionStatusEnum.INITIATED.getId(), transaction.getTxnStatusId());
        assertEquals(TransactionStatusEnum.INITIATED.getId(), transaction.getTxnDetailsId());
        verify(transactionDao).getTransactionById(transaction.getId());
        verify(transactionDao).updateTransaction(transaction);
    }

//    @Test
//    void testUpdateStatus_TransactionNotFound() {
//        // Arrange
//        Transaction transaction = new Transaction();
//        transaction.setId(1); // Assuming id is set
//        
//        // Mocking behavior of the TransactionDao
//        when(transactionDao.getTransactionById(transaction.getId()))
//            .thenReturn(null); // Simulate transaction not found
//        
//        // Act
//        boolean result = initiatedTransactionStatusHandler.updateStatus(transaction);
//
//        // Assert
//        assertFalse(result); // Assuming updateStatus should return false
//        verify(transactionDao).getTransactionById(transaction.getId());
//        verifyNoInteractions(transactionDao, transactionLogDao); // No interactions with DAOs expected
//    }

//    @Test
//    void testUpdateStatus_ExceptionInDao() {
//        // Arrange
//        Transaction transaction = new Transaction();
//        transaction.setId(1); // Assuming id is set
//        
//        // Mocking behavior of the TransactionDao
//        when(transactionDao.getTransactionById(transaction.getId()))
//            .thenReturn(transaction); // Return the same transaction
//        
//        when(transactionDao.updateTransaction(transaction))
//            .thenThrow(RuntimeException.class); // Simulate exception
//        
//        // Act
//        boolean result = initiatedTransactionStatusHandler.updateStatus(transaction);
//
//        // Assert
//        assertFalse(result); // Assuming updateStatus should return false
//        assertEquals(TransactionStatusEnum.INITIATED.getId(), transaction.getTxnStatusId());
//        assertEquals(TransactionStatusEnum.INITIATED.getId(), transaction.getTxnDetailsId());
//        verify(transactionDao).getTransactionById(transaction.getId());
//        verify(transactionDao).updateTransaction(transaction);
//        verifyNoInteractions(transactionLogDao); // No interactions with TransactionLogDao expected
//    }

//    @Test
//    void testUpdateStatus_LogTransaction() {
//        // Arrange
//        Transaction transaction = new Transaction();
//        transaction.setId(1); // Assuming id is set
//        
//        // Mocking behavior of the TransactionDao
//        when(transactionDao.getTransactionById(transaction.getId()))
//            .thenReturn(transaction); // Return the same transaction
//        
//        when(transactionDao.updateTransaction(transaction))
//            .thenReturn(true); // Assuming updateTransaction returns true
//        
//        // Act
//        boolean result = initiatedTransactionStatusHandler.updateStatus(transaction);
//
//        // Assert
//        assertTrue(result); // Assuming updateStatus should return true
//        assertEquals(TransactionStatusEnum.INITIATED.getId(), transaction.getTxnStatusId());
//        assertEquals(TransactionStatusEnum.INITIATED.getId(), transaction.getTxnDetailsId());
//        verify(transactionDao).getTransactionById(transaction.getId());
//        verify(transactionDao).updateTransaction(transaction);
//
//        // Verify logging of transaction
//        verify(transactionLogDao).createTransactionLog(argThat(log ->
//            log.getTransactionId() == transaction.getId() &&
//            log.getTxnFromStatus().equals("FROM_STATUS") && // Provide expected from status
//            log.getTxnToStatus().equals(TransactionStatusEnum.INITIATED.getName())
//        ));
//    }
}
