package com.example.news_module.repository;

import java.util.List;
import java.util.Map;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.news_module.entity.SubCategory;

@Repository
public interface SubCategoryDao extends JpaRepository<SubCategory, Integer>{
	
	@Query(value ="select * from sub_categorys where father = ?1",nativeQuery =true)
	public List<Map<String, Object>> findAllSubByFather(Integer father);

}
