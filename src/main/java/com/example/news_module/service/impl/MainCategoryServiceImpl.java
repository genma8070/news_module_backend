package com.example.news_module.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.news_module.contants.MsgCode;
import com.example.news_module.contants.Pattern;
import com.example.news_module.entity.MainCategory;
import com.example.news_module.entity.SubCategory;
import com.example.news_module.repository.MainCategoryDao;
import com.example.news_module.repository.NewsDao;
import com.example.news_module.repository.SubCategoryDao;
import com.example.news_module.service.ifs.MainCategoryService;
import com.example.news_module.vo.request.MainCategoryRequest;
import com.example.news_module.vo.response.MainCategoryResponse;

@Service
public class MainCategoryServiceImpl implements MainCategoryService {

	@Autowired
	private MainCategoryDao mainCategoryDao;

	@Autowired
	private SubCategoryDao subCategoryDao;

	@Autowired
	private NewsDao newsDao;

//	該当IDのメインカテゴリを取得する
	@Override
	public MainCategoryResponse findMainCategoryById(MainCategoryRequest req) {

		MainCategory target = mainCategoryDao.findById(req.getMainId()).get();

		return new MainCategoryResponse(target);
	}

//	全てのメインカテゴリを取得する
	@Override
	public MainCategoryResponse findMainCategory() {

		List<MainCategoryResponse> eList = new ArrayList<MainCategoryResponse>();

//		全てのメインカテゴリを取得する
		List<Map<String, Object>> res = mainCategoryDao.findAllMain();

		for (Map<String, Object> map : res) {
			MainCategoryResponse e = new MainCategoryResponse();
			for (String item : map.keySet()) {
				switch (item) {

				case "id":
					e.setMainId((Integer) map.get(item));
//					このカテゴリのニュースを取得する
					List<Map<String, Object>> size = newsDao.findNewsByCategory(e.getMainId(), null);
					e.setNews(size.size());
					break;
				case "main_category_name":
					e.setMainCategoryName((String) map.get(item));
					break;

				}
			}
			eList.add(e);
		}
		if (eList.size() == 0) {
			return new MainCategoryResponse(MsgCode.NOT_FOUND.getMessage(), MsgCode.NOT_FOUND.getType());
		}

		return new MainCategoryResponse(eList);

	}

//	メインカテゴリの追加か更新
	public MainCategoryResponse addOrUpdateMainCategory(MainCategoryRequest req) {
		String name = req.getMainCategoryName();

//		カテゴリ名称の字数が10桁以内かどうかの判断式
		if (!name.matches(Pattern.CATEGORY_NAME.getPattern())) {
			return new MainCategoryResponse(Pattern.CATEGORY_NAME.getMessage(), Pattern.CATEGORY_NAME.getType());
		}

//		名称が空白かどうかの判断式
		if (name.isBlank()) {
			return new MainCategoryResponse(MsgCode.CANNOT_BLANK.getMessage(), MsgCode.CANNOT_BLANK.getType());
		}

		Integer id = req.getMainId();

//		ID入力がある場合は更新する
		if (id != null) {
			MainCategory update = new MainCategory(id, name);

//			既に同名のメインカテゴリがあるかどうかの判断式
			List<Map<String, Object>> target = mainCategoryDao.findByTitle(name);
//			同名がない場合はそのまま更新する
			if (target.size() == 0) {
				mainCategoryDao.save(update);
				return new MainCategoryResponse(MsgCode.CATEGORY_EDIT_SUCCESSFUL.getMessage(),
						MsgCode.CATEGORY_EDIT_SUCCESSFUL.getType());
			} else {
				return new MainCategoryResponse(MsgCode.IS_EXISTED.getMessage(), MsgCode.IS_EXISTED.getType());
			}

		} else {
			MainCategory create = new MainCategory(name);
//			既に同名のメインカテゴリがあるかどうかの判断式
			List<Map<String, Object>> target = mainCategoryDao.findByTitle(name);
//			同名がない場合はそのまま追加する
			if (target.size() == 0) {
				mainCategoryDao.save(create);
				return new MainCategoryResponse(MsgCode.CATEGORY_CREATE_SUCCESSFUL.getMessage(),
						MsgCode.CATEGORY_CREATE_SUCCESSFUL.getType());
			} else {
				return new MainCategoryResponse(MsgCode.IS_EXISTED.getMessage(), MsgCode.IS_EXISTED.getType());
			}
		}
	}

//	メインカテゴリを削除する
	public MainCategoryResponse deleteMainCategory(MainCategoryRequest req) {
//		選択したメインカテゴリのIDリストを取得する
		List<Integer> deleteList = req.getDeleteList();
//		未選択かどうかの判断式
		if (deleteList.size() == 0) {
			return new MainCategoryResponse(MsgCode.NO_TARGET.getMessage(), MsgCode.NO_TARGET.getType());
		}

		for (Integer main : deleteList) {
//			このカテゴリのニュースを取得する
			List<Map<String, Object>> target = newsDao.findNewsByCategory(main, null);
//			ニュースがない場合は削除する
			if (target.size() == 0) {
				// サブカテゴリを削除してからメインカテゴリを削除する
				List<SubCategory> sub = subCategoryDao.findMainsSub(main);
//				サブがない場合はそのままメインを削除する
				if (sub != null) {
					for (SubCategory subId : sub) {
						subCategoryDao.deleteById(subId.getSubId());
					}
					mainCategoryDao.deleteById(main);
				} else {
					mainCategoryDao.deleteById(main);
				}

			} else {
				return new MainCategoryResponse(MsgCode.NOT_EMPTY.getMessage(), MsgCode.NOT_EMPTY.getType());
			}
		}
		return new MainCategoryResponse(MsgCode.CATEGORY_DELETE_SUCCESSFUL.getMessage(),
				MsgCode.CATEGORY_DELETE_SUCCESSFUL.getType());

	}

}
