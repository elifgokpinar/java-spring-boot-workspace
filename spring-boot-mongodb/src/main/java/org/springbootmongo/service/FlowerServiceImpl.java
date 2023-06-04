package org.springbootmongo.service;


import org.springbootmongo.entity.Flower;
import org.springbootmongo.exception.FlowerException;
import org.springbootmongo.repository.FlowerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class FlowerServiceImpl implements FlowerService{

    @Autowired
    FlowerRepository flowerRepository;

    @Override
    public List<Flower> getAllFlowers() {
        List<Flower> flowers = flowerRepository.findAll();
        if(flowers.size()>0)
            return flowers;
        else
            return new ArrayList<Flower>();
    }

    @Override
    public Flower getFlowerById(String id) {
        return flowerRepository.findById(id)
                               .orElseThrow(() -> new RuntimeException("Flower is not found. ID: "+id));
    }

    @Override
    public Flower createFlower(Flower flower) throws FlowerException {
        Optional<Flower> flowerOptional = flowerRepository.findByName(flower.getName());
        if(flowerOptional.isPresent())
            throw new FlowerException(FlowerException.FlowerAlreadyExistByNameException(flowerOptional.get().getName()));
        return flowerRepository.save(flower);
    }

    @Override
    public void updateFlower(String id,Flower flower) throws FlowerException {
        Optional<Flower> optionalFlower = flowerRepository.findById(id);
        if(optionalFlower.isPresent()){
            Flower currentFlower = optionalFlower.get();
            currentFlower.setName(flower.getName() != null ? flower.getName() : currentFlower.getName());
            currentFlower.setName(flower.getColor() != null ? flower.getColor() : currentFlower.getColor());
            flowerRepository.save(currentFlower);
        }
        else
            throw new FlowerException(FlowerException.FlowerAlreadyExistException(id));
    }

    @Override
    public void deleteFlower(String id) throws FlowerException {
        Optional<Flower> flower = flowerRepository.findById(id);
        if(flower.isPresent())
            flowerRepository.deleteById(id);
        else
            throw new FlowerException(FlowerException.FlowerNotFoundException(id));
    }
}
