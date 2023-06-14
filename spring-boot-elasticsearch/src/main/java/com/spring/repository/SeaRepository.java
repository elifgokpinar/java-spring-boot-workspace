package com.spring.repository;

import com.spring.entity.Sea;
import org.springframework.data.elasticsearch.annotations.Query;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

import java.util.List;

public interface SeaRepository extends ElasticsearchRepository<Sea, String> {

    //
    @Query("{\"bool\": {\"must\": [{\"match\": {\"name\": \"?0\"}}]}}")
    List<Sea> getByCustomQuery(String search);

    List<Sea>  findByName(String name);

    List<Sea>  findByNameLikeOrAreaLike(String name, String area);

}
