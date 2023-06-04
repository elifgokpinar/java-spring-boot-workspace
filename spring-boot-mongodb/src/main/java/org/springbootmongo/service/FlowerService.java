package org.springbootmongo.service;
import org.springbootmongo.entity.Flower;
import org.springbootmongo.exception.FlowerException;

import java.util.List;

public interface FlowerService {

    List<Flower> getAllFlowers();

    Flower getFlowerById(String id);

    Flower createFlower(Flower flower) throws FlowerException;

    void updateFlower(String id, Flower flower) throws FlowerException;

    void deleteFlower(String id) throws FlowerException;


}
