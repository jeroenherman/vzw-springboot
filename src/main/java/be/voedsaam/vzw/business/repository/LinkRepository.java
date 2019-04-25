package be.voedsaam.vzw.business.repository;

import be.voedsaam.vzw.business.Article;
import be.voedsaam.vzw.business.Link;
import be.voedsaam.vzw.enums.ArticleType;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface LinkRepository extends CrudRepository<Link, Long>{



}
