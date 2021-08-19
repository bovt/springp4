package ru.bvt.notesengine.rest;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.bvt.notesengine.rest.dto.CategoryDto;
import ru.bvt.notesengine.service.BookService;
import ru.bvt.notesengine.service.CategoryService;
import ru.bvt.notesengine.service.CategoryServiceSimple;

import java.util.List;

@RestController
@AllArgsConstructor
public class CategoryController {

    private final CategoryService service;

    @GetMapping("/api/category")
    public List<CategoryDto> showCategoryList() {
        return service.getAllCategories();
    }

    @PostMapping("/api/category")
    public String createCategory(@RequestBody CategoryDto newCategoryDto, BindingResult result) {
        if (result.hasErrors()) {
            return "createCategory hadErrors";
        }
        long id = service.addCategory(newCategoryDto);
        return new String("{ id: " + id + " }");
    }

    @GetMapping("/api/category/{id}")
    public CategoryDto showCategory(@PathVariable("id") long id, Model model) {
        return service.getCategory(id);
    }

    @PutMapping("/api/category/{id}")
    public String updateCategory(@PathVariable("id") long id, CategoryDto categoryDto, BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "updateCategory";
        }
        categoryDto.setId(id);
        return new String("{ id: " + service.setCategory(categoryDto) + " }");
    }

    @DeleteMapping("/api/category/{id}")
    public String deleteCategory(@PathVariable("id") long id, Model model) {
        service.deleteCategory(id);
        return new String("{ id: " + id + " }");
    }
}
