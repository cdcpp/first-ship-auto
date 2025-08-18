package com.cucupang.first_ship.repository;

import com.cucupang.first_ship.entity.Keyword;
import com.cucupang.first_ship.enu.SearchYn;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface KeywordRepository extends JpaRepository<Keyword, Integer> {


    List<Keyword> findBySearchYn(SearchYn searchYn);
}
