package com.sugrado.rentacar.business.rules;

import com.sugrado.rentacar.business.messages.BrandMessages;
import com.sugrado.rentacar.core.utilities.exceptions.types.BusinessException;
import com.sugrado.rentacar.dataAccess.abstracts.BrandRepository;
import com.sugrado.rentacar.entities.concretes.Brand;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class BrandBusinessRules {
    BrandRepository brandRepository;

    public void brandNameCanNotBeDuplicated(String brandName) {
        Optional<Brand> brand = brandRepository.findByNameIgnoreCase(brandName);
        if (brand.isPresent()) {
            throw new BusinessException(BrandMessages.BRAND_NAME_ALREADY_EXISTS);
        }
    }
}
