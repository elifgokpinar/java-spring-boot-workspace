package org.springbootmongo.repository;

import org.springbootmongo.entity.Flower;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface FlowerRepository  extends MongoRepository<Flower,String> {

   /* @Query("{'flower': ?0}")
    Optional<Flower> findByFlower(String flowerName);*/
   Optional<Flower> findByName(String name);

}
