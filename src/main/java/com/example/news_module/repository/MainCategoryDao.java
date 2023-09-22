package com.example.news_module.repository;

import java.util.List;
import java.util.Map;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.news_module.entity.MainCategory;

//メインカテゴリDBのレスポストリ
@Repository
public interface MainCategoryDao extends JpaRepository<MainCategory, Integer> {

//	全てのメインカテゴリを取得する
	@Query(value = "select * from main_categorys", nativeQuery = true)
	public List<Map<String, Object>> findAllMain();

//	同じタイトルのメインカテゴリを取得する
	@Query(value = "select * from main_categorys where main_category_name = :inputTitle", nativeQuery = true)
	public List<Map<String, Object>> findByTitle(@Param("inputTitle") String Tilte);

}
