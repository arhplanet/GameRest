package no.arhplanet.game.dao;

import java.util.List;

public interface Dao<T> {

    List<? extends T> getAll() throws Exception;

    T getById(Long id) throws Exception;



}