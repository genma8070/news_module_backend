package com.example.news_module.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.example.news_module.contants.MsgCode;
import com.example.news_module.contants.Pattern;
import com.example.news_module.entity.SubCategory;
import com.example.news_module.repository.NewsDao;
import com.example.news_module.repository.SubCategoryDao;
import com.example.news_module.service.ifs.SubCategoryService;
import com.example.news_module.vo.request.SubCategoryRequest;
import com.example.news_module.vo.response.SubCategoryResponse;

@Service
public class SubCategoryServiceImpl implements SubCategoryService {

	@Autowired
	private SubCategoryDao subCategoryDao;

	@Autowired
	private NewsDao newsDao;

	@Override
	public SubCategoryResponse findSubCategoryById(SubCategoryRequest req) {
		SubCategory target = subCategoryDao.findById(req.getSubId()).get();
		return new SubCategoryResponse(target);

	}

//	該当メインカテゴリIDを基づいて所属しているサブカテゴリを取得する
	@Override
	public SubCategoryResponse findSubCategoryByMainId(SubCategoryRequest req) {

		List<SubCategoryResponse> eList = new ArrayList<SubCategoryResponse>();

		List<Map<String, Object>> res = subCategoryDao.findAllSubByMain(req.getMainId());

		for (Map<String, Object> map : res) {
			SubCategoryResponse e = new SubCategoryResponse();
			for (String item : map.keySet()) {
				switch (item) {

				case "id":
					e.setSubId((Integer) map.get(item));
//					このカテゴリのニュースを取得する
					List<Map<String, Object>> size = newsDao.findNewsByCategory(null, e.getSubId());
					e.setNews(size.size());
					break;
				case "sub_category_name":
					e.setSubCategoryName((String) map.get(item));
					break;
				case "main_id":
					e.setMainId((Integer) map.get(item));
					break;
				}
			}
			eList.add(e);
		}
		if (eList.size() == 0) {
			return new SubCategoryResponse(MsgCode.NOT_FOUND.getMessage(), MsgCode.NOT_FOUND.getType());
		}

		return new SubCategoryResponse(eList);

	}

//	全てのサブカテゴリを取得する
	@Override
	public SubCategoryResponse getAllSubCategory() {

		//並べ方を指定する
		List<SubCategory> res = subCategoryDao.findAll(Sort.by(Sort.Direction.DESC, "mainId")
                .and(Sort.by(Sort.Direction.DESC, "subId")));
		List<SubCategoryResponse> outPutList = new ArrayList<>();
		for (SubCategory target : res) {
			SubCategoryResponse outPut = new SubCategoryResponse();
//			それぞれのカテゴリのニュースを取得する
			List<Map<String, Object>> size = newsDao.findNewsByCategory(null, target.getSubId());
			outPut.setSubId(target.getSubId());
			outPut.setSubCategoryName(target.getSubCategoryName());
			outPut.setMainId(target.getMainId());
//			ニュース数
			outPut.setNews(size.size());
			outPutList.add(outPut);
		}
		if (res.size() == 0) {
			return new SubCategoryResponse(MsgCode.NOT_FOUND.getMessage(), MsgCode.NOT_FOUND.getType());
		}
		return new SubCategoryResponse(outPutList);
	}

//	サブカテゴリの追加か更新
	public SubCategoryResponse addOrUpdateSubCategory(SubCategoryRequest req) {
		String name = req.getSubCategoryName();

//		カテゴリ名称の字数が10桁以内かどうかの判断式
		if (!name.matches(Pattern.CATEGORY_NAME.getPattern())) {
			return new SubCategoryResponse(Pattern.CATEGORY_NAME.getMessage(), Pattern.CATEGORY_NAME.getType());
		}

//		名称が空白かどうかの判断式
		if (name.isBlank()) {
			return new SubCategoryResponse(MsgCode.CANNOT_BLANK.getMessage(), MsgCode.CANNOT_BLANK.getType());
		}

//		所属するメインカテゴリID
		Integer main = req.getMainId();

//		所属するメインカテゴリがあるかどうかの判断式
		if (main == null) {
			return new SubCategoryResponse(MsgCode.NEED_MAINCATEGORY_ID.getMessage(), MsgCode.NEED_MAINCATEGORY_ID.getType());
		}
		
		Integer id = req.getSubId();

//		ID入力がある場合は更新する
		if(id != null) {
			SubCategory update = new SubCategory(id, name, main);
//			既に同名のサブカテゴリがあるかどうかの判断式
			List<Map<String, Object>> target = subCategoryDao.findByTitle(name, main);
//			同名がない場合はそのまま更新する
			if (target.size() == 0) {
				subCategoryDao.save(update);
				return new SubCategoryResponse(MsgCode.CATEGORY_EDIT_SUCCESSFUL.getMessage(),
						MsgCode.CATEGORY_EDIT_SUCCESSFUL.getType());
			} else {
				return new SubCategoryResponse(MsgCode.IS_EXISTED.getMessage(), MsgCode.IS_EXISTED.getType());
			}
		}else {
			SubCategory create = new SubCategory(name, main);
//			既に同名のサブカテゴリがあるかどうかの判断式
			List<Map<String, Object>> target = subCategoryDao.findByTitle(name, main);
//			同名がない場合はそのまま追加する
			if (target.size() == 0) {
				subCategoryDao.save(create);
				return new SubCategoryResponse(MsgCode.CATEGORY_CREATE_SUCCESSFUL.getMessage(),
						MsgCode.CATEGORY_CREATE_SUCCESSFUL.getType());
			} else {
				return new SubCategoryResponse(MsgCode.IS_EXISTED.getMessage(), MsgCode.IS_EXISTED.getType());
			}

			
		}
		
		
	}



//	サブカテゴリを削除する
	public SubCategoryResponse deleteSubCategory(SubCategoryRequest req) {
//		選択したサブカテゴリのIDリストを取得する
		List<Integer> deleteList = req.getDeleteList();
//		未選択かどうかの判断式
		if (deleteList.size() == 0) {
			return new SubCategoryResponse(MsgCode.NO_TARGET.getMessage(), MsgCode.NO_TARGET.getType());
		}
		for (Integer sub : deleteList) {
//			このカテゴリのニュースを取得する
			List<Map<String, Object>> target = newsDao.findNewsByCategory(null, sub);
//			ニュースがない場合は削除する
			if (target.size() == 0) {
				subCategoryDao.deleteById(sub);
			} else {
				return new SubCategoryResponse(MsgCode.NOT_EMPTY.getMessage(), MsgCode.NOT_EMPTY.getType());
			}
		}
		return new SubCategoryResponse(MsgCode.CATEGORY_DELETE_SUCCESSFUL.getMessage(),
				MsgCode.CATEGORY_DELETE_SUCCESSFUL.getType());

	}

}
