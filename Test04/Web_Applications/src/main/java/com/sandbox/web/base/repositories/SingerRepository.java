package com.sandbox.web.base.repositories;

import com.sandbox.web.base.entities.Singer;
import org.springframework.data.repository.PagingAndSortingRepository;

/**
 * Created by Dmytro Honchar
 * Date: 7/12/2022
 */

public interface SingerRepository extends PagingAndSortingRepository<Singer, Long> {
    // modification of CrudRepository,
    // has methods for retrieving entities by pagination
    // and sorting abstractions.
}
