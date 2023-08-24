package com.example.news_module.repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.news_module.entity.News;

@Repository
public interface NewsDao extends JpaRepository<News, Integer>{
	
	@Query(value = "select n.news_id, n.main_category, n.sub_category, f.main_title,"
			+ " c.sub_title, n.title, n.text, n.open, n.creat_date, n.updata_date, n.open_date from news n "
			+ "left join main_categorys f on f.id = n.main_category "
			+ "left join sub_categorys c on c.id = n.sub_category "
			+ "WHERE (title LIKE %:inputTitle%) "
			+ "AND (main_category = :inputMain OR :inputMain IS NULL) "
			+ "AND (sub_category = :inputSub OR :inputSub IS NULL) "
			+ "AND (open = true) "
			+ "AND (creat_date BETWEEN COALESCE(:inputStartTime, '1911-01-01') AND COALESCE(:inputEndTime, '2123-01-01')) "
			+ "ORDER BY 11 DESC "
			+ "LIMIT :inputIndex, 10", nativeQuery = true)
	public List<Map<String, Object>> findNewsByTitleOrCategoryOrDatePagingF(@Param("inputTitle") String title,
			@Param("inputMain") Integer main,@Param("inputSub") Integer sub,
			@Param("inputStartTime") LocalDate startTime, @Param("inputEndTime")  LocalDate endTime, @Param("inputIndex") Integer index);

	@Query(value = "select n.news_id, n.main_category, n.sub_category, f.main_title,"
			+ " c.sub_title, n.title, n.text, n.open, n.creat_date, n.updata_date, n.open_date from news n "
			+ "left join main_categorys f on f.id = n.main_category "
			+ "left join sub_categorys c on c.id = n.sub_category "
			+ "WHERE (title LIKE %:inputTitle%) "
			+ "AND (main_category = :inputMain OR :inputMain IS NULL) "
			+ "AND (sub_category = :inputSub OR :inputSub IS NULL) "
			+ "AND (open = true) "
			+ "AND (creat_date BETWEEN COALESCE(:inputStartTime, '1911-01-01') AND COALESCE(:inputEndTime, '2123-01-01')) "
			+ "ORDER BY 11 DESC ", nativeQuery = true)
	public List<Map<String, Object>> findNewsByTitleOrCategoryOrDateF(@Param("inputTitle") String title,
			@Param("inputMain") Integer main,@Param("inputSub") Integer sub,
			@Param("inputStartTime") LocalDate startTime, @Param("inputEndTime") LocalDate endTime);
	
	@Query(value ="select n.news_id, n.main_category, n.sub_category, f.main_title,"
			+ " c.sub_title, n.title, n.text, n.open, n.creat_date, n.updata_date, n.open_date from news n "
			+ "left join main_categorys f on f.id = n.main_category "
			+ "left join sub_categorys c on c.id = n.sub_category"
			+ " ORDER BY 11 DESC "
			+ "LIMIT :inputIndex, 10",nativeQuery =true)
	public List<Map<String, Object>> findAllNewsPagingB(@Param("inputIndex") Integer index);

	@Query(value ="select n.news_id, n.main_category, n.sub_category, f.main_title,"
			+ " c.sub_title, n.title, n.text, n.open, n.creat_date, n.updata_date, n.open_date from news n "
			+ "left join main_categorys f on f.id = n.main_category "
			+ "left join sub_categorys c on c.id = n.sub_category "
			+ "ORDER BY 11 DESC",nativeQuery =true)
	public List<Map<String, Object>> findAllNewsB();
	
	@Query(value = "select n.news_id, n.main_category, n.sub_category, f.main_title,"
			+ " c.sub_title, n.title, n.text, n.open, n.creat_date, n.updata_date, n.open_date from news n "
			+ "left join main_categorys f on f.id = n.main_category "
			+ "left join sub_categorys c on c.id = n.sub_category "
			+ "WHERE (title LIKE %:inputTitle%) "
			+ "AND (main_category = :inputMain OR :inputMain IS NULL) "
			+ "AND (sub_category = :inputSub OR :inputSub IS NULL) "
			+ "AND (creat_date BETWEEN COALESCE(:inputStartTime, '1911-01-01') AND COALESCE(:inputEndTime, '2123-01-01')) "
			+ "ORDER BY 11 DESC "
			+ "LIMIT :inputIndex, 10", nativeQuery = true)
	public List<Map<String, Object>> findNewsByTitleOrCategoryOrDateB(@Param("inputTitle") String title,
			@Param("inputMain") Integer main,@Param("inputSub") Integer sub,
			@Param("inputStartTime") LocalDate startTime, @Param("inputEndTime") LocalDate endTime, @Param("inputIndex") Integer index);

	@Query(value = "select n.news_id, n.main_category, n.sub_category, f.main_title,"
			+ " c.sub_title, n.title, n.text, n.open, n.creat_date, n.updata_date, n.open_date from news n "
			+ "left join main_categorys f on f.id = n.main_category "
			+ "left join sub_categorys c on c.id = n.sub_category "
			+ "WHERE (title LIKE %:inputTitle%) "
			+ "AND (main_category = :inputMain OR :inputMain IS NULL) "
			+ "AND (sub_category = :inputSub OR :inputSub IS NULL) "
			+ "AND (creat_date BETWEEN COALESCE(:inputStartTime, '1911-01-01') AND COALESCE(:inputEndTime, '2123-01-01')) "
			+ "ORDER BY 11 DESC", nativeQuery = true)
	public List<Map<String, Object>> findAllNewsByTitleOrCategoryOrDateB(@Param("inputTitle") String title,
			@Param("inputMain") Integer main,@Param("inputSub") Integer sub,
			@Param("inputStartTime") LocalDate startTime, @Param("inputEndTime") LocalDate endTime);
	
	@Query(value ="select n.news_id, n.main_category, n.sub_category, f.main_title,"
			+ " c.sub_title, n.title, n.text, n.open, n.creat_date, n.updata_date, n.open_date from news n "
			+ "left join main_categorys f on f.id = n.main_category "
			+ "left join sub_categorys c on c.id = n.sub_category "
			+ "WHERE open = true "
			+ "ORDER BY 11 DESC "
			+ "LIMIT :inputIndex, 10",nativeQuery =true)
	public List<Map<String, Object>> findAllNewsPagingF(@Param("inputIndex") Integer index);
	

	@Query(value ="select n.news_id, n.main_category, n.sub_category, f.main_title,"
			+ " c.sub_title, n.title, n.text, n.open, n.creat_date, n.updata_date, n.open_date from news n "
			+ "left join main_categorys f on f.id = n.main_category "
			+ "left join sub_categorys c on c.id = n.sub_category "
			+ "WHERE open = true "
			+ "ORDER BY 11 DESC",nativeQuery =true)
	public List<Map<String, Object>> findAllNewsF();
	
	
	@Query(value = "SELECT * FROM news WHERE news_id = :inputId", nativeQuery = true)
	public Optional<News> findNewsById(@Param("inputId") Integer id);
}
