package com.example.news_module.repository;

import java.util.List;
import java.util.Map;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.news_module.entity.MainCategory;

@Repository
public interface MainCategoryDao extends JpaRepository<MainCategory, Integer>{
	
	@Query(value ="select * from main_categorys",nativeQuery =true)
	public List<Map<String, Object>> findAllMain();

}
