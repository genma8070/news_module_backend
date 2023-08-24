package com.example.news_module.repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.news_module.entity.News;

@Repository
public interface NewsDao extends JpaRepository<News, Integer> {

//	選択したページの検索条件と一致するニュースを取得する（ユーザー側）
//	ニューステーブルにメインカテゴリテーブルとサブカテゴリテーブルを外部結合する
//	メインカテゴリテーブルとサブカテゴリテーブルからカテゴリ名称を取得する
//	検索条件の説明：
//	タイトルのあいまい検索
//	カテゴリを選択する場合は検索結果を制限する、選択しない場合は制限しない
//	ユーザー側の検索は公開しているニュースだけ表示する
//	二つの日にちの間のニュースを取得する、下限を選択しない場合は自動に1911-01-01を代入する、下限を選択しない場合は自動に2123-01-01を代入する
//	検索結果は11番目のカラム欄open_date（公開日）の降順並び
//	検索結果は輸入したインデクスから最大10個まで表示する
	@Query(value = "select n.news_id, n.main_category, n.sub_category, f.main_title,"
			+ " c.sub_title, n.title, n.text, n.open, n.creat_date, n.updata_date, n.open_date from news n "
			+ "left join main_categorys f on f.id = n.main_category "
			+ "left join sub_categorys c on c.id = n.sub_category " + "WHERE (title LIKE %:inputTitle%) "
			+ "AND (main_category = :inputMain OR :inputMain IS NULL) "
			+ "AND (sub_category = :inputSub OR :inputSub IS NULL) " + "AND (open = true) "
			+ "AND (open_date BETWEEN COALESCE(:inputStartTime, '1911-01-01') AND COALESCE(:inputEndTime, '2123-01-01')) "
			+ "ORDER BY 11 DESC " + "LIMIT :inputIndex, 10", nativeQuery = true)
	public List<Map<String, Object>> findNewsByTitleOrCategoryOrDatePagingF(@Param("inputTitle") String title,
			@Param("inputMain") Integer main, @Param("inputSub") Integer sub,
			@Param("inputStartTime") LocalDate startTime, @Param("inputEndTime") LocalDate endTime,
			@Param("inputIndex") Integer index);

//	ニューステーブルにメインカテゴリテーブルとサブカテゴリテーブルを外部結合する
//	メインカテゴリテーブルとサブカテゴリテーブルからカテゴリ名称を取得する
//	検索条件の説明：
//	タイトルのあいまい検索
//	カテゴリを選択する場合は検索結果を制限する、選択しない場合は制限しない
//	ユーザー側の検索は公開しているニュースだけ表示する
//	二つの日にちの間のニュースを取得する、下限を選択しない場合は自動に1911-01-01を代入する、下限を選択しない場合は自動に2123-01-01を代入する
//	検索結果は11番目のカラム欄open_date（公開日）の降順並び
	@Query(value = "select n.news_id, n.main_category, n.sub_category, f.main_title,"
			+ " c.sub_title, n.title, n.text, n.open, n.creat_date, n.updata_date, n.open_date from news n "
			+ "left join main_categorys f on f.id = n.main_category "
			+ "left join sub_categorys c on c.id = n.sub_category " + "WHERE (title LIKE %:inputTitle%) "
			+ "AND (main_category = :inputMain OR :inputMain IS NULL) "
			+ "AND (sub_category = :inputSub OR :inputSub IS NULL) " + "AND (open = true) "
			+ "AND (open_date BETWEEN COALESCE(:inputStartTime, '1911-01-01') AND COALESCE(:inputEndTime, '2123-01-01')) "
			+ "ORDER BY 11 DESC ", nativeQuery = true)
	public List<Map<String, Object>> findNewsByTitleOrCategoryOrDateF(@Param("inputTitle") String title,
			@Param("inputMain") Integer main, @Param("inputSub") Integer sub,
			@Param("inputStartTime") LocalDate startTime, @Param("inputEndTime") LocalDate endTime);

//	選択したページのニュースを取得する（ユーザー側）
//	ニューステーブルにメインカテゴリテーブルとサブカテゴリテーブルを外部結合する
//	メインカテゴリテーブルとサブカテゴリテーブルからカテゴリ名称を取得する
//	検索結果は11番目のカラム欄open_date（公開日）の降順並び
//	検索結果は輸入したインデクスから最大10個まで表示する
	@Query(value = "select n.news_id, n.main_category, n.sub_category, f.main_title,"
			+ " c.sub_title, n.title, n.text, n.open, n.creat_date, n.updata_date, n.open_date from news n "
			+ "left join main_categorys f on f.id = n.main_category "
			+ "left join sub_categorys c on c.id = n.sub_category " + "WHERE open = true " + "ORDER BY 11 DESC "
			+ "LIMIT :inputIndex, 10", nativeQuery = true)
	public List<Map<String, Object>> findAllNewsPagingF(@Param("inputIndex") Integer index);

//	選択したページのニュースを取得する（ユーザー側）
//	ニューステーブルにメインカテゴリテーブルとサブカテゴリテーブルを外部結合する
//	メインカテゴリテーブルとサブカテゴリテーブルからカテゴリ名称を取得する
//	検索結果は11番目のカラム欄open_date（公開日）の降順並び
	@Query(value = "select n.news_id, n.main_category, n.sub_category, f.main_title,"
			+ " c.sub_title, n.title, n.text, n.open, n.creat_date, n.updata_date, n.open_date from news n "
			+ "left join main_categorys f on f.id = n.main_category "
			+ "left join sub_categorys c on c.id = n.sub_category " + "WHERE open = true "
			+ "ORDER BY 11 DESC", nativeQuery = true)
	public List<Map<String, Object>> findAllNewsF();

//	選択したページのニュースを取得する（管理者側）
//	ニューステーブルにメインカテゴリテーブルとサブカテゴリテーブルを外部結合する
//	メインカテゴリテーブルとサブカテゴリテーブルからカテゴリ名称を取得する
//	検索結果は11番目のカラム欄open_date（公開日）の降順並び
//	検索結果は輸入したインデクスから最大10個まで表示する
	@Query(value = "select n.news_id, n.main_category, n.sub_category, f.main_title,"
			+ " c.sub_title, n.title, n.text, n.open, n.creat_date, n.updata_date, n.open_date from news n "
			+ "left join main_categorys f on f.id = n.main_category "
			+ "left join sub_categorys c on c.id = n.sub_category" + " ORDER BY 11 DESC "
			+ "LIMIT :inputIndex, 10", nativeQuery = true)
	public List<Map<String, Object>> findAllNewsPagingB(@Param("inputIndex") Integer index);

//	選択したページのニュースを取得する（管理者側）
//	ニューステーブルにメインカテゴリテーブルとサブカテゴリテーブルを外部結合する
//	メインカテゴリテーブルとサブカテゴリテーブルからカテゴリ名称を取得する
//	検索結果は11番目のカラム欄open_date（公開日）の降順並び
	@Query(value = "select n.news_id, n.main_category, n.sub_category, f.main_title,"
			+ " c.sub_title, n.title, n.text, n.open, n.creat_date, n.updata_date, n.open_date from news n "
			+ "left join main_categorys f on f.id = n.main_category "
			+ "left join sub_categorys c on c.id = n.sub_category " + "ORDER BY 11 DESC", nativeQuery = true)
	public List<Map<String, Object>> findAllNewsB();

//	選択したページの検索条件と一致するニュースを取得する（管理者側）
//	ニューステーブルにメインカテゴリテーブルとサブカテゴリテーブルを外部結合する
//	メインカテゴリテーブルとサブカテゴリテーブルからカテゴリ名称を取得する
//	検索条件の説明：
//	タイトルのあいまい検索
//	カテゴリを選択する場合は検索結果を制限する、選択しない場合は制限しない
//	二つの日にちの間のニュースを取得する、下限を選択しない場合は自動に1911-01-01を代入する、下限を選択しない場合は自動に2123-01-01を代入する
//	検索結果は11番目のカラム欄open_date（公開日）の降順並び
//	検索結果は輸入したインデクスから最大10個まで表示する
	@Query(value = "select n.news_id, n.main_category, n.sub_category, f.main_title,"
			+ " c.sub_title, n.title, n.text, n.open, n.creat_date, n.updata_date, n.open_date from news n "
			+ "left join main_categorys f on f.id = n.main_category "
			+ "left join sub_categorys c on c.id = n.sub_category " + "WHERE (title LIKE %:inputTitle%) "
			+ "AND (main_category = :inputMain OR :inputMain IS NULL) "
			+ "AND (sub_category = :inputSub OR :inputSub IS NULL) "
			+ "AND (open_date BETWEEN COALESCE(:inputStartTime, '1911-01-01') AND COALESCE(:inputEndTime, '2123-01-01')) "
			+ "ORDER BY 11 DESC " + "LIMIT :inputIndex, 10", nativeQuery = true)
	public List<Map<String, Object>> findNewsByTitleOrCategoryOrDateB(@Param("inputTitle") String title,
			@Param("inputMain") Integer main, @Param("inputSub") Integer sub,
			@Param("inputStartTime") LocalDate startTime, @Param("inputEndTime") LocalDate endTime,
			@Param("inputIndex") Integer index);

//	選択したページの検索条件と一致するニュースを取得する（管理者側）
//	ニューステーブルにメインカテゴリテーブルとサブカテゴリテーブルを外部結合する
//	メインカテゴリテーブルとサブカテゴリテーブルからカテゴリ名称を取得する
//	検索条件の説明：
//	タイトルのあいまい検索
//	カテゴリを選択する場合は検索結果を制限する、選択しない場合は制限しない
//	二つの日にちの間のニュースを取得する、下限を選択しない場合は自動に1911-01-01を代入する、下限を選択しない場合は自動に2123-01-01を代入する
//	検索結果は11番目のカラム欄open_date（公開日）の降順並び
	@Query(value = "select n.news_id, n.main_category, n.sub_category, f.main_title,"
			+ " c.sub_title, n.title, n.text, n.open, n.creat_date, n.updata_date, n.open_date from news n "
			+ "left join main_categorys f on f.id = n.main_category "
			+ "left join sub_categorys c on c.id = n.sub_category " + "WHERE (title LIKE %:inputTitle%) "
			+ "AND (main_category = :inputMain OR :inputMain IS NULL) "
			+ "AND (sub_category = :inputSub OR :inputSub IS NULL) "
			+ "AND (open_date BETWEEN COALESCE(:inputStartTime, '1911-01-01') AND COALESCE(:inputEndTime, '2123-01-01')) "
			+ "ORDER BY 11 DESC", nativeQuery = true)
	public List<Map<String, Object>> findAllNewsByTitleOrCategoryOrDateB(@Param("inputTitle") String title,
			@Param("inputMain") Integer main, @Param("inputSub") Integer sub,
			@Param("inputStartTime") LocalDate startTime, @Param("inputEndTime") LocalDate endTime);

}
