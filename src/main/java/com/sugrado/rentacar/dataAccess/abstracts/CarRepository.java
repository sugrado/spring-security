package com.sugrado.rentacar.dataAccess.abstracts;

import com.sugrado.rentacar.entities.concretes.Car;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CarRepository extends JpaRepository<Car, Integer> {
}
