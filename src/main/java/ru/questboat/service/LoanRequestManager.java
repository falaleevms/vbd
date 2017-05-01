package ru.questboat.service;

import org.springframework.stereotype.Service;
import ru.questboat.model.LoanRequest;

import java.util.List;

/**
 * Created by Mikhail Falaleev on 23.04.2017.
 */

@Service
public interface LoanRequestManager {
    public LoanRequest save(LoanRequest loanRequest);
    public List<LoanRequest> findAll();
    public boolean changeLoanStatus(long id, String newStatus);
}
