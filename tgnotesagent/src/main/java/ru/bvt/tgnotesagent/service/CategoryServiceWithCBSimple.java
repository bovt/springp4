package ru.bvt.tgnotesagent.service;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.bvt.tgnotesagent.feign.NoteFeignClientBotEdition;
import ru.bvt.tgnotesagent.rest.dto.CategoryDto;

import java.util.Arrays;
import java.util.List;

@AllArgsConstructor
@Service
public class CategoryServiceWithCBSimple implements CategoryServiceWithCB {

    private NoteFeignClientBotEdition noteFeignClientBotEdition;

    @HystrixCommand(fallbackMethod = "defaultGetAllCategories")
    public List<CategoryDto> getAllCategories() {
        return noteFeignClientBotEdition.showCategoryList();
    }

    private List<CategoryDto> defaultGetAllCategories() {
        List<CategoryDto> categoryList = Arrays.asList(new CategoryDto(0,"N/A"));
        return categoryList;
    }

}
