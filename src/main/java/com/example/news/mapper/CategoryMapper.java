package com.example.news.mapper;

import com.example.news.entity.Category;
import com.example.news.model.request.UpsetCategoryRequest;
import com.example.news.model.response.CategoryResponse;
import com.example.news.model.response.UpdatedCategoryResponse;
import org.mapstruct.DecoratedWith;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@DecoratedWith(CategoryMapperDelegate.class)
@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE, uses = {NewsMapper.class, CommentMapper.class})
public interface CategoryMapper {

    Category requestToCategory(UpsetCategoryRequest request);

    @Mapping(source = "categoryId", target = "id")
    Category requestToCategory(Long categoryId, UpsetCategoryRequest request);

    CategoryResponse categoryToResponse(Category categories);

    List<CategoryResponse> categoryListToResponseList(List<Category> categories);

    UpdatedCategoryResponse updatedCategoryToResponse(Category category);


}
