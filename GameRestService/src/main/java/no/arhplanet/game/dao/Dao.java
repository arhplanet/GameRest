package no.arhplanet.game.dao;

import java.util.List;

public interface Dao<T> {

    List<? extends T> getAll();

    T getById(String id);



}