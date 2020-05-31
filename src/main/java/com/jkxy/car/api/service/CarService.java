package com.jkxy.car.api.service;

import com.jkxy.car.api.pojo.Car;

import java.util.List;


public interface CarService {

    List<Car> findAll();

    Car findById(int id);

    List<Car> findByCarName(String carName);

    void deleteById(int id);

    void updateById(Car car);

    void insertCar(Car car);

    boolean saleCar(Car car);

    List<Car> findPageDataByCarLike(String like, int pageNum, int pageSize);
}
