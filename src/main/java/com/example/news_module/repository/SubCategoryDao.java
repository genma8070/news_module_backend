package com.example.news_module.repository;

import java.util.List;
import java.util.Map;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.news_module.entity.SubCategory;

//サブカテゴリDBのレスポストリ
@Repository
public interface SubCategoryDao extends JpaRepository<SubCategory, Integer> {

//	メインカテゴリのIDによる全てのサブカテゴリを取得する
	@Query(value = "select * from sub_categorys where main_id = ?1 "
			+ "ORDER BY main_id DESC, id DESC", nativeQuery = true)
	public List<Map<String, Object>> findAllSubByMain(Integer main);
	
//	メインカテゴリのIDによる全てのサブカテゴリを取得する
	@Query(value = "select * from sub_categorys where main_id = ?1 "
			+ "ORDER BY main_id DESC, id DESC", nativeQuery = true)
	public List<SubCategory> findMainsSub(Integer main);

//	該当メインカテゴリ内の同じタイトルのサブカテゴリを取得する
	@Query(value = "select * from sub_categorys "
			+ "where main_id = :inputMain AND sub_category_name = :inputTitle", nativeQuery = true)
	public List<Map<String, Object>> findByTitle(@Param("inputTitle") String Tilte,@Param("inputMain")Integer main);

}
