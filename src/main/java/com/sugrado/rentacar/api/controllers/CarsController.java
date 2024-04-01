package com.sugrado.rentacar.api.controllers;

import com.sugrado.rentacar.business.abstracts.CarService;
import com.sugrado.rentacar.business.dtos.requests.CreateCarRequest;
import com.sugrado.rentacar.business.dtos.responses.CreatedCarResponse;
import com.sugrado.rentacar.business.dtos.responses.GetAllCarResponse;
import com.sugrado.rentacar.core.business.paging.PageInfo;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("api/v1/cars")
public class CarsController {
    private CarService carService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CreatedCarResponse add(@Valid @RequestBody CreateCarRequest createCarRequest) {
        return carService.add(createCarRequest);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<GetAllCarResponse> getAll(@RequestParam int page, @RequestParam int size) {
        PageInfo pageInfo = new PageInfo(page, size);
        return carService.getAll(pageInfo);
    }
}
