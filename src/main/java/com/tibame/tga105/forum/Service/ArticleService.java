package com.tibame.tga105.forum.Service;

import com.tibame.tga105.forum.entity.ArticleEntity;
import com.tibame.tga105.forum.entity.ReplyEntity;
import com.tibame.tga105.forum.Repository.ArticleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class ArticleService {

    @Autowired
    ArticleRepository articleRepository;

    public List<ArticleEntity> findAll(){

        return articleRepository.findAll();

    }
    public ArticleEntity findById(Integer articleid){
        return  articleRepository.findByArticleid(articleid);

    }

    public  void  delete(Integer articleid){
        articleRepository.deleteById(articleid);
    }
    public  ArticleEntity  add(ArticleEntity articleEntity){
       return articleRepository.save(articleEntity);

    }

    public void addArticle(){
        ArticleEntity articleEntity=new ArticleEntity();
//       articleEntity.setUserid(1);
        articleEntity.setArticletitle("哈利波特");
        articleEntity.setArticlecontext("好看");

        List<ReplyEntity> replyEnityList=new ArrayList<>();
        ReplyEntity replyEnity1=new ReplyEntity("確定好看嗎");
        replyEnityList.add(replyEnity1);
        ReplyEntity replyEnity2=new ReplyEntity("難看");
        replyEnityList.add(replyEnity2);

        articleRepository.save(articleEntity);
    }



    public  List<ArticleEntity> findByS(String context){

        return articleRepository.findArticleEntitiesByArticlecontextLike(context);
    }

    public List<ArticleEntity> findByJPQL(int length){
        return  articleRepository.findByJPQL(length);
    }
    @Transactional
    public int findByJPQL(String articletitle,
                          String articlecontext,
                          Integer articleid){
        return  articleRepository.updateByJPQL(articletitle,articlecontext,articleid);
    }

    public List<ArticleEntity> findByWords(String articletitle,
                                           String articlecontext){
        return  articleRepository.findByKeyWords(articletitle,articlecontext);
    }

    public Page<ArticleEntity> findAllByPage(Pageable pageable){

        return  articleRepository.findAll(pageable);
    }
    
    public List<ArticleEntity>findUserArticle(Integer userid) {
    	
    	return articleRepository.findUserArticle(userid);
    }
    public List<ArticleEntity> findtype(Integer id){
    	return articleRepository.findByArticleTypeId(id);
    }
    
    public List<ArticleEntity> findHotArticle(){
    	return articleRepository.findArticlePopularity();
    }
    
    public List<ArticleEntity> findViews(){
    	
    	return articleRepository.findArticleView();
    }
    
    public List<ArticleEntity> sortByPostdate(){
    	
    	return articleRepository.sortByPostdatetime();
    }
    }

