package com.example.news_module.repository;

import java.util.List;
import java.util.Map;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.news_module.entity.SubCategory;

@Repository
public interface SubCategoryDao extends JpaRepository<SubCategory, Integer> {

//	メインカテゴリのIDによる全てのサブカテゴリを取得する
	@Query(value = "select * from sub_categorys where main_id = ?1", nativeQuery = true)
	public List<Map<String, Object>> findAllSubByFather(Integer father);

	@Query(value = "select * from sub_categorys where sub_title = :inputTitle", nativeQuery = true)
	public List<Map<String, Object>> findByTitle(@Param("inputTitle") String Tilte);

}
