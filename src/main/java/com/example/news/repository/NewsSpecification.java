package com.example.news.repository;

import com.example.news.entity.Category;
import com.example.news.entity.News;
import com.example.news.entity.User;
import com.example.news.model.NewsFilter;
import jakarta.persistence.criteria.Join;
import org.springframework.data.jpa.domain.Specification;

public interface NewsSpecification {
    static Specification<News> withFilter(NewsFilter newsFilter) {
        return Specification.where(byUserName(newsFilter.getUserName()))
                .and(byCategoryName(newsFilter.getCategoryName()));
    }

    static Specification<News> byUserName(String userName) {
        return ((root, query, cb) -> {
            if (userName == null) {
                return null;
            }
            Join<News, Category> categoryJoin = root.join("category");
            Join<Category, User> userJoin = categoryJoin.join("user");
            return cb.equal(userJoin.get("name"), userName);
        });
    }

    static Specification<News> byCategoryName(String categoryName) {
        return ((root, query, cb) -> {
            if (categoryName == null) {
                return null;
            }
            Join<News, Category> categoryJoin = root.join("category");
            return cb.equal(categoryJoin.get("name"), categoryName);
        });
    }
}
