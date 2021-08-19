package ru.bvt.tgnotesagent.rest;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.bvt.tgnotesagent.rest.dto.CategoryDto;
import ru.bvt.tgnotesagent.service.CategoryServiceWithCB;

import java.util.List;

@RestController
@AllArgsConstructor
public class CategoryControllerWithCB {

    private final CategoryServiceWithCB categoryService;

    @GetMapping("/api/category")
    public List<CategoryDto> showCategoryList() {
        return categoryService.getAllCategories();
    }

}
