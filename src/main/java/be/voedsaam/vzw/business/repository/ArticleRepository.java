package be.voedsaam.vzw.business.repository;

import be.voedsaam.vzw.business.Article;
import be.voedsaam.vzw.business.Drive;
import be.voedsaam.vzw.business.Schedule;
import be.voedsaam.vzw.business.User;
import be.voedsaam.vzw.enums.ArticleType;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ArticleRepository extends CrudRepository<Article, Long>{

    List<Article> findAllByArticleType(ArticleType articleType);


}
