package com.shi.creator.service;

import java.util.List;

import com.shi.creator.dao.ContractDAO;
import com.shi.creator.entity.Contract;

public class ContractService {
    private ContractDAO contractsDao = new ContractDAO();

    public Contract findContract(long id) {
        return contractsDao.findById(id);
    }

    public void saveContract(Contract contract) {
        contractsDao.save(contract);
    }

    public void deleteContract(Contract contract) {
        contractsDao.delete(contract);
    }

    public void updateContract(Contract contract) {
        contractsDao.update(contract);
    }

    public List<Contract> findAllContracts() {
        return contractsDao.findAll();
    }

    public Contract findContractByNumber(String number) {
        return contractsDao.findContractByNumber(number);
    }
}
