package com.sugrado.rentacar.business.abstracts;

import com.sugrado.rentacar.business.dtos.requests.CreateBrandRequest;
import com.sugrado.rentacar.business.dtos.responses.CreatedBrandResponse;
import com.sugrado.rentacar.business.dtos.responses.GetAllBrandResponse;

import java.util.List;

public interface BrandService {
    CreatedBrandResponse add(CreateBrandRequest createBrandRequest);

    List<GetAllBrandResponse> getAll();
}
