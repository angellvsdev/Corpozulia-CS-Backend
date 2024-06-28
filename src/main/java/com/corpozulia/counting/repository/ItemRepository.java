package com.corpozulia.counting.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.corpozulia.counting.models.Item;

@Repository
public interface ItemRepository extends JpaRepository<Item, Long> {

	Page<Item> findByNameContainingIgnoreCase(String name, PageRequest of);

}
