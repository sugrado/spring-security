package com.sugrado.rentacar.business.concretes;

import com.sugrado.rentacar.business.abstracts.CarService;
import com.sugrado.rentacar.business.dtos.requests.CreateCarRequest;
import com.sugrado.rentacar.business.dtos.responses.CreatedCarResponse;
import com.sugrado.rentacar.business.dtos.responses.GetAllCarResponse;
import com.sugrado.rentacar.core.business.paging.PageInfo;
import com.sugrado.rentacar.core.utilities.mapping.ModelMapperService;
import com.sugrado.rentacar.dataAccess.abstracts.CarRepository;
import com.sugrado.rentacar.entities.concretes.Car;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
@Service
public class CarManager implements CarService {
    private CarRepository carRepository;
    private ModelMapperService modelMapperService;

    @Override
    public CreatedCarResponse add(CreateCarRequest createCarRequest) {
        Car car = this.modelMapperService.forRequest().map(createCarRequest, Car.class);

        car.setCreatedDate(LocalDateTime.now());
        car.setState(1);
        Car createdCar = carRepository.save(car);

        return this.modelMapperService.forResponse()
                .map(createdCar, CreatedCarResponse.class);
    }

    @Override
    public List<GetAllCarResponse> getAll(PageInfo pageInfo) {


        PageRequest pageRequest = PageRequest.of(pageInfo.getPage(), pageInfo.getSize());
        List<Car> cars = carRepository.findAll(pageRequest).getContent();

        return cars.stream().map(car ->
                this.modelMapperService.forResponse()
                        .map(car, GetAllCarResponse.class)).collect(Collectors.toList());
    }
}
