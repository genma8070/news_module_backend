package com.example.news_module.repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.news_module.entity.News;

@Repository
public interface NewsDao extends JpaRepository<News, Integer> {


//	選択したページのニュースを取得する（ユーザー側）
//	ニューステーブルにメインカテゴリテーブルとサブカテゴリテーブルを外部結合する
//	メインカテゴリテーブルとサブカテゴリテーブルからカテゴリ名称を取得する
//	検索結果は12番目のカラム欄open_date（公開日）の降順並び
//	検索結果は輸入したインデクスから最大100個まで表示する
	@Query(value = "select n.news_id, n.main_category, n.sub_category, f.main_category_name,"
			+ " c.sub_category_name, n.title, n.sub_title, n.text, n.open, n.creat_date, n.update_date, n.open_date from news n "
			+ "left join main_categorys f on f.id = n.main_category "
			+ "left join sub_categorys c on c.id = n.sub_category " + "WHERE open = true " + "ORDER BY 12 DESC "
			+ "LIMIT :inputIndex, :inputItems", nativeQuery = true)
	public List<Map<String, Object>> findAllNewsPagingF(@Param("inputIndex") Integer index, @Param("inputItems") Integer items);

//	選択したページのニュースを取得する（ユーザー側）
//	ニューステーブルにメインカテゴリテーブルとサブカテゴリテーブルを外部結合する
//	メインカテゴリテーブルとサブカテゴリテーブルからカテゴリ名称を取得する
//	検索結果は12番目のカラム欄open_date（公開日）の降順並び
	@Query(value = "select n.news_id, n.main_category, n.sub_category, f.main_category_name,"
			+ " c.sub_category_name, n.title, n.sub_title, n.text, n.open, n.creat_date, n.update_date, n.open_date from news n "
			+ "left join main_categorys f on f.id = n.main_category "
			+ "left join sub_categorys c on c.id = n.sub_category "
			+ "ORDER BY 12 DESC", nativeQuery = true)
	public List<Map<String, Object>> findAllNewsF();
	
//	選択したページのニュースを取得する（ユーザー側）
//	ニューステーブルにメインカテゴリテーブルとサブカテゴリテーブルを外部結合する
//	メインカテゴリテーブルとサブカテゴリテーブルからカテゴリ名称を取得する
//	検索結果は12番目のカラム欄open_date（公開日）の昇順並び
//	検索結果は輸入したインデクスから最大100個まで表示する
	@Query(value = "select n.news_id, n.main_category, n.sub_category, f.main_category_name,"
			+ " c.sub_category_name, n.title, n.sub_title, n.text, n.open, n.creat_date, n.update_date, n.open_date from news n "
			+ "left join main_categorys f on f.id = n.main_category "
			+ "left join sub_categorys c on c.id = n.sub_category " + "ORDER BY 12 ASC "
			+ "LIMIT :inputIndex, :inputItems", nativeQuery = true)
	public List<Map<String, Object>> findAllNewsPagingBAsc(@Param("inputIndex") Integer index, @Param("inputItems") Integer items);

//	選択したページのニュースを取得する（ユーザー側）
//	ニューステーブルにメインカテゴリテーブルとサブカテゴリテーブルを外部結合する
//	メインカテゴリテーブルとサブカテゴリテーブルからカテゴリ名称を取得する
//	検索結果は12番目のカラム欄open_date（公開日）の昇順並び
	@Query(value = "select n.news_id, n.main_category, n.sub_category, f.main_category_name,"
			+ " c.sub_category_name, n.title, n.sub_title, n.text, n.open, n.creat_date, n.update_date, n.open_date from news n "
			+ "left join main_categorys f on f.id = n.main_category "
			+ "left join sub_categorys c on c.id = n.sub_category "
			+ "ORDER BY 12 ASC", nativeQuery = true)
	public List<Map<String, Object>> findAllNewsBAsc();

//	選択したページのニュースを取得する（管理者側）
//	ニューステーブルにメインカテゴリテーブルとサブカテゴリテーブルを外部結合する
//	メインカテゴリテーブルとサブカテゴリテーブルからカテゴリ名称を取得する
//	検索結果は12番目のカラム欄open_date（公開日）の降順並び
//	検索結果は輸入したインデクスから最大100個まで表示する
	@Query(value = "select n.news_id, n.main_category, n.sub_category, f.main_category_name,"
			+ " c.sub_category_name, n.title, n.sub_title, n.text, n.open, n.creat_date, n.update_date, n.open_date from news n "
			+ "left join main_categorys f on f.id = n.main_category "
			+ "left join sub_categorys c on c.id = n.sub_category" + " ORDER BY 12 DESC "
			+ "LIMIT :inputIndex, :inputItems", nativeQuery = true)
	public List<Map<String, Object>> findAllNewsPagingBDesc(@Param("inputIndex") Integer index, @Param("inputItems") Integer items);

//	選択したページのニュースを取得する（管理者側）
//	ニューステーブルにメインカテゴリテーブルとサブカテゴリテーブルを外部結合する
//	メインカテゴリテーブルとサブカテゴリテーブルからカテゴリ名称を取得する
//	検索結果は12番目のカラム欄open_date（公開日）の降順並び
	@Query(value = "select n.news_id, n.main_category, n.sub_category, f.main_category_name,"
			+ " c.sub_category_name, n.title, n.sub_title, n.text, n.open, n.creat_date, n.update_date, n.open_date from news n "
			+ "left join main_categorys f on f.id = n.main_category "
			+ "left join sub_categorys c on c.id = n.sub_category " + "ORDER BY 12 DESC", nativeQuery = true)
	public List<Map<String, Object>> findAllNewsBDesc();
	

//	選択したページの検索条件と一致するニュースを取得する（管理者側）
//	ニューステーブルにメインカテゴリテーブルとサブカテゴリテーブルを外部結合する
//	メインカテゴリテーブルとサブカテゴリテーブルからカテゴリ名称を取得する
//	検索条件の説明：
//	タイトルのあいまい検索
//	カテゴリを選択する場合は検索結果を制限する、選択しない場合は制限しない
//	二つの日にちの間のニュースを取得する、下限を選択しない場合は自動に1911-01-01を代入する、下限を選択しない場合は自動に2123-01-01を代入する
//	検索結果は12番目のカラム欄open_date（公開日）の降順並び
//	検索結果は輸入したインデクスから最大100個まで表示する
	@Query(value = "select n.news_id, n.main_category, n.sub_category, f.main_category_name,"
			+ " c.sub_category_name, n.title, n.sub_title, n.text, n.open, n.creat_date, n.update_date, n.open_date from news n "
			+ "left join main_categorys f on f.id = n.main_category "
			+ "left join sub_categorys c on c.id = n.sub_category " + "WHERE (title LIKE %:inputTitle%) "
			+ "AND (main_category = :inputMain OR :inputMain IS NULL) "
			+ "AND (sub_category = :inputSub OR :inputSub IS NULL) "
			+ "AND (open_date BETWEEN COALESCE(:inputStartTime, '1911-01-01') AND COALESCE(:inputEndTime, '2123-01-01')) "
			+ "ORDER BY 12 DESC " + "LIMIT :inputIndex, :inputItems", nativeQuery = true)
	public List<Map<String, Object>> findNewsByTitleOrCategoryOrDateBDesc(@Param("inputTitle") String title,
			@Param("inputMain") Integer main, @Param("inputSub") Integer sub,
			@Param("inputStartTime") LocalDateTime startTime, @Param("inputEndTime") LocalDateTime endTime,
			@Param("inputIndex") Integer index, @Param("inputItems") Integer items);

//	選択したページの検索条件と一致するニュースを取得する（管理者側）
//	ニューステーブルにメインカテゴリテーブルとサブカテゴリテーブルを外部結合する
//	メインカテゴリテーブルとサブカテゴリテーブルからカテゴリ名称を取得する
//	検索条件の説明：
//	タイトルのあいまい検索
//	カテゴリを選択する場合は検索結果を制限する、選択しない場合は制限しない
//	二つの日にちの間のニュースを取得する、下限を選択しない場合は自動に1911-01-01を代入する、下限を選択しない場合は自動に2123-01-01を代入する
//	検索結果は12番目のカラム欄open_date（公開日）の昇順並び
//	検索結果は輸入したインデクスから最大100個まで表示する
	@Query(value = "select n.news_id, n.main_category, n.sub_category, f.main_category_name,"
			+ " c.sub_category_name, n.title, n.sub_title, n.text, n.open, n.creat_date, n.update_date, n.open_date from news n "
			+ "left join main_categorys f on f.id = n.main_category "
			+ "left join sub_categorys c on c.id = n.sub_category " + "WHERE (title LIKE %:inputTitle%) "
			+ "AND (main_category = :inputMain OR :inputMain IS NULL) "
			+ "AND (sub_category = :inputSub OR :inputSub IS NULL) "
			+ "AND (open_date BETWEEN COALESCE(:inputStartTime, '1911-01-01') AND COALESCE(:inputEndTime, '2123-01-01')) "
			+ "ORDER BY 12 ASC " + "LIMIT :inputIndex, :inputItems", nativeQuery = true)
	public List<Map<String, Object>> findNewsByTitleOrCategoryOrDateBAsc(@Param("inputTitle") String title,
			@Param("inputMain") Integer main, @Param("inputSub") Integer sub,
			@Param("inputStartTime") LocalDateTime startTime, @Param("inputEndTime") LocalDateTime endTime,
			@Param("inputIndex") Integer index, @Param("inputItems") Integer items);

	
//	選択したページの検索条件と一致するニュースを取得する（管理者側）
//	ニューステーブルにメインカテゴリテーブルとサブカテゴリテーブルを外部結合する
//	メインカテゴリテーブルとサブカテゴリテーブルからカテゴリ名称を取得する
//	検索条件の説明：
//	タイトルのあいまい検索
//	カテゴリを選択する場合は検索結果を制限する、選択しない場合は制限しない
//	二つの日にちの間のニュースを取得する、下限を選択しない場合は自動に1911-01-01を代入する、下限を選択しない場合は自動に2123-01-01を代入する
//	検索結果は12番目のカラム欄open_date（公開日）の降順並び
	@Query(value = "select n.news_id, n.main_category, n.sub_category, f.main_category_name,"
			+ " c.sub_category_name, n.title, n.sub_title, n.text, n.open, n.creat_date, n.update_date, n.open_date from news n "
			+ "left join main_categorys f on f.id = n.main_category "
			+ "left join sub_categorys c on c.id = n.sub_category " + "WHERE (title LIKE %:inputTitle%) "
			+ "AND (main_category = :inputMain OR :inputMain IS NULL) "
			+ "AND (sub_category = :inputSub OR :inputSub IS NULL) "
			+ "AND (open_date BETWEEN COALESCE(:inputStartTime, '1911-01-01') AND COALESCE(:inputEndTime, '2123-01-01')) "
			+ "ORDER BY 12 DESC", nativeQuery = true)
	public List<Map<String, Object>> findAllNewsByTitleOrCategoryOrDateBDesc(@Param("inputTitle") String title,
			@Param("inputMain") Integer main, @Param("inputSub") Integer sub,
			@Param("inputStartTime") LocalDateTime startTime, @Param("inputEndTime") LocalDateTime endTime);

//	選択したページの検索条件と一致するニュースを取得する（管理者側）
//	ニューステーブルにメインカテゴリテーブルとサブカテゴリテーブルを外部結合する
//	メインカテゴリテーブルとサブカテゴリテーブルからカテゴリ名称を取得する
//	検索条件の説明：
//	タイトルのあいまい検索
//	カテゴリを選択する場合は検索結果を制限する、選択しない場合は制限しない
//	二つの日にちの間のニュースを取得する、下限を選択しない場合は自動に1911-01-01を代入する、下限を選択しない場合は自動に2123-01-01を代入する
//	検索結果は12番目のカラム欄open_date（公開日）の昇順並び
	@Query(value = "select n.news_id, n.main_category, n.sub_category, f.main_category_name,"
			+ " c.sub_category_name, n.title, n.sub_title, n.text, n.open, n.creat_date, n.update_date, n.open_date from news n "
			+ "left join main_categorys f on f.id = n.main_category "
			+ "left join sub_categorys c on c.id = n.sub_category " + "WHERE (title LIKE %:inputTitle%) "
			+ "AND (main_category = :inputMain OR :inputMain IS NULL) "
			+ "AND (sub_category = :inputSub OR :inputSub IS NULL) "
			+ "AND (open_date BETWEEN COALESCE(:inputStartTime, '1911-01-01') AND COALESCE(:inputEndTime, '2123-01-01')) "
			+ "ORDER BY 12 ASC", nativeQuery = true)
	public List<Map<String, Object>> findAllNewsByTitleOrCategoryOrDateBAsc(@Param("inputTitle") String title,
			@Param("inputMain") Integer main, @Param("inputSub") Integer sub,
			@Param("inputStartTime") LocalDateTime startTime, @Param("inputEndTime") LocalDateTime endTime);

//	選択したページの検索条件と一致するニュースを取得する（管理者側）
//	ニューステーブルにメインカテゴリテーブルとサブカテゴリテーブルを外部結合する
//	メインカテゴリテーブルとサブカテゴリテーブルからカテゴリ名称を取得する
//	検索条件の説明：
//	カテゴリを選択する場合は検索結果を制限する、選択しない場合は制限しない
//	検索結果は12番目のカラム欄open_date（公開日）の降順並び
	@Query(value = "select n.news_id, n.main_category, n.sub_category, f.main_category_name,"
			+ " c.sub_category_name, n.title, n.sub_title, n.text, n.open, n.creat_date, n.update_date, n.open_date from news n "
			+ "left join main_categorys f on f.id = n.main_category "
			+ "left join sub_categorys c on c.id = n.sub_category WHERE "
			+ "(main_category = :inputMain OR :inputMain IS NULL) "
			+ "AND (sub_category = :inputSub OR :inputSub IS NULL)"
			+ "ORDER BY 12 DESC", nativeQuery = true)
	public List<Map<String, Object>> findNewsByCategory(@Param("inputMain") Integer main,
			@Param("inputSub") Integer sub);
	
//	該当IDのカテゴリ名称を含めているニュースデータを取得する
	@Query(value = "select n.news_id, n.main_category, n.sub_category, f.main_category_name,"
			+ " c.sub_category_name, n.title, n.sub_title, n.text, n.open, n.creat_date, n.update_date, n.open_date from news n "
			+ "left join main_categorys f on f.id = n.main_category "
			+ "left join sub_categorys c on c.id = n.sub_category " + "WHERE n.news_id = ?1", nativeQuery = true)
	public List<Map<String, Object>> findFullNewsById(Integer id);
}
