package com.sugrado.rentacar.business.concretes;

import com.sugrado.rentacar.business.abstracts.BrandService;
import com.sugrado.rentacar.business.dtos.requests.CreateBrandRequest;
import com.sugrado.rentacar.business.dtos.responses.CreatedBrandResponse;
import com.sugrado.rentacar.business.dtos.responses.GetAllBrandResponse;
import com.sugrado.rentacar.business.rules.BrandBusinessRules;
import com.sugrado.rentacar.core.utilities.mapping.ModelMapperService;
import com.sugrado.rentacar.dataAccess.abstracts.BrandRepository;
import com.sugrado.rentacar.entities.concretes.Brand;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
@Service
public class BrandManager implements BrandService {
    private BrandRepository brandRepository;
    private ModelMapperService modelMapperService;
    private BrandBusinessRules brandBusinessRules;

    @Override
    public CreatedBrandResponse add(CreateBrandRequest createBrandRequest) {

        brandBusinessRules.brandNameCanNotBeDuplicated(createBrandRequest.getName());

        Brand brand = this.modelMapperService.forRequest().map(createBrandRequest, Brand.class);

        brand.setCreatedDate(LocalDateTime.now());
        Brand createdBrand = brandRepository.save(brand);

        return this.modelMapperService.forResponse().map(createdBrand, CreatedBrandResponse.class);
    }

    @Override
    public List<GetAllBrandResponse> getAll() {
        var result = brandRepository.findAll();

        return result.stream().map(brand ->
                this.modelMapperService.forResponse()
                        .map(brand, GetAllBrandResponse.class)).collect(Collectors.toList());
    }
}
