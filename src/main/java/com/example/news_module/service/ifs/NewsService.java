package com.example.news_module.service.ifs;

import com.example.news_module.vo.request.NewsRequest;
import com.example.news_module.vo.response.NewsResponse;
import com.example.news_module.vo.response.NewsWithCategoryNameVo;

public interface NewsService {

	public NewsResponse addNews(NewsRequest newReq);

	public NewsResponse updataNews(NewsRequest newReq);

	public NewsResponse changNewsStatus(NewsRequest newReq);

	public NewsResponse findNewsById(NewsRequest newReq);

	public NewsWithCategoryNameVo searchNewsF(NewsRequest newReq);
	
	public NewsWithCategoryNameVo searchNewsAllF(NewsRequest newReq);

	public NewsWithCategoryNameVo findAllNewsF(NewsRequest newReq);

	public NewsWithCategoryNameVo findAllF();

	public NewsWithCategoryNameVo searchNewsB(NewsRequest newReq);
	
	public NewsWithCategoryNameVo searchNewsAllB(NewsRequest newReq);

	public NewsWithCategoryNameVo findAllNewsB(NewsRequest newReq);

	public NewsWithCategoryNameVo findAllB();

}
