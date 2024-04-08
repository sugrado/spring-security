package com.sugrado.rentacar.business.concretes;

import com.sugrado.rentacar.business.dtos.requests.CreateBrandRequest;
import com.sugrado.rentacar.business.rules.BrandBusinessRules;
import com.sugrado.rentacar.core.utilities.exceptions.types.BusinessException;
import com.sugrado.rentacar.core.utilities.mapping.ModelMapperManager;
import com.sugrado.rentacar.core.utilities.mapping.ModelMapperService;
import com.sugrado.rentacar.dataAccess.abstracts.BrandRepository;
import com.sugrado.rentacar.entities.concretes.Brand;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.modelmapper.ModelMapper;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

class BrandManagerTest {
    private BrandManager brandManager;
    private BrandRepository brandRepository;

    @BeforeEach
    void setUp() {
        brandRepository = Mockito.mock(BrandRepository.class);
        ModelMapper mapper = new ModelMapper();
        ModelMapperService modelMapperService = new ModelMapperManager(mapper);
        BrandBusinessRules brandBusinessRules = new BrandBusinessRules(brandRepository);
        brandManager = new BrandManager(brandRepository, modelMapperService, brandBusinessRules);
    }

    @AfterEach
    void cleanUp() {

    }

    @Test
    void addBrandWithExistingName_ShouldThrowException() {
        // Arrange, Act, Assert
        Mockito.when(brandRepository.findByNameIgnoreCase("BMW")).thenReturn(Optional.of(new Brand()));
        CreateBrandRequest createBrandRequest = new CreateBrandRequest("BMW");
        assertThrows(BusinessException.class, () -> {
            brandManager.add(createBrandRequest);
        });
    }

    @Test
    void addBrandSuccessfully_ShouldReturnBrand() {
        Brand savedBrand = new Brand();
        savedBrand.setName("BMW");
        savedBrand.setId(1);
        Mockito.when(brandRepository.findByNameIgnoreCase("BMW")).thenReturn(Optional.empty());
        Mockito.when(brandRepository.save(Mockito.any(Brand.class))).thenReturn(savedBrand);
        CreateBrandRequest createBrandRequest = new CreateBrandRequest("BMW");
        brandManager.add(createBrandRequest);
        assertTrue(true);
    }
}