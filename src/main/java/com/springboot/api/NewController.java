package com.springboot.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.springboot.dto.request.NewRequestDTO;
import com.springboot.dto.response.NewResponsePage;
import com.springboot.dto.response.NewResponseDTO;
import com.springboot.service.INewService;

@CrossOrigin
@RestController
public class NewController {

    @Autowired
    private INewService newService;

    @GetMapping(value = "/new")
    public NewResponsePage showNew(
            @RequestParam(value = "page", required = false) Integer page,
            @RequestParam(value = "limit", required = false) Integer limit) {
        NewResponsePage result = new NewResponsePage();
        if (page != null && limit != null) {
            result.setPage(page);
            Pageable pageable = PageRequest.of(page - 1, limit);
            result.setListResult(newService.findAll(pageable));
            result.setTotalPage((int) Math.ceil((double) (newService.totalItem()) / limit));
        } else {
            result.setListResult(newService.findAll());
        }
        return result;
    }

    @PostMapping(value = "/new")
    public NewResponseDTO createNew(@RequestBody NewRequestDTO model) {
        return newService.save(model);
    }

    @PutMapping(value = "/new/{id}")
    public NewResponseDTO updateNew(@RequestBody NewRequestDTO model, @PathVariable("id") long id) {
        model.setId(id);
        return newService.save(model);
    }

    @DeleteMapping(value = "/new")
    public void deleteNew(@RequestBody long[] ids) {
        newService.delete(ids);
    }
}
