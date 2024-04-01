package com.sugrado.rentacar.business.abstracts;

import com.sugrado.rentacar.business.dtos.requests.CreateCarRequest;
import com.sugrado.rentacar.business.dtos.responses.CreatedCarResponse;
import com.sugrado.rentacar.business.dtos.responses.GetAllCarResponse;
import com.sugrado.rentacar.core.business.paging.PageInfo;

import java.util.List;

public interface CarService {
    CreatedCarResponse add(CreateCarRequest createCarRequest);

    List<GetAllCarResponse> getAll(PageInfo pageInfo);
}
