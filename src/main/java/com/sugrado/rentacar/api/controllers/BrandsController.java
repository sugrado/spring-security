package com.sugrado.rentacar.api.controllers;

import com.sugrado.rentacar.business.abstracts.BrandService;
import com.sugrado.rentacar.business.dtos.requests.CreateBrandRequest;
import com.sugrado.rentacar.business.dtos.responses.CreatedBrandResponse;
import com.sugrado.rentacar.business.dtos.responses.GetAllBrandResponse;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("api/v1/brands")
public class BrandsController {
    private BrandService brandService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CreatedBrandResponse add(@Valid @RequestBody CreateBrandRequest createBrandRequest) {
        return brandService.add(createBrandRequest);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<GetAllBrandResponse> getAll() {
        return brandService.getAll();
    }
}
