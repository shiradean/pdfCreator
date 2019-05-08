package com.shi.creator.service;

import java.util.List;

import com.shi.creator.dao.HolderDAO;
import com.shi.creator.entity.Holder;

public class HolderService {
    private HolderDAO holdersDao = new HolderDAO();

    public Holder findHolder(long id) {
        return holdersDao.findById(id);
    }

    public void saveHolder(Holder holder) {
        holdersDao.save(holder);
    }

    public void deleteHolder(Holder holder) {
        holdersDao.delete(holder);
    }

    public void updateHolder(Holder holder) {
        holdersDao.update(holder);
    }

    public List<Holder> findAllHolders() {
        return holdersDao.findAll();
    }

    public Holder findHolderById(int id) {
        return holdersDao.findHolderById(id);
    }
}
