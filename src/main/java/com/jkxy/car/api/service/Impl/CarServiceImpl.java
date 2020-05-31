package com.jkxy.car.api.service.Impl;

import com.jkxy.car.api.dao.CarDao;
import com.jkxy.car.api.pojo.Car;
import com.jkxy.car.api.service.CarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Service("carService")
public class CarServiceImpl implements CarService {
    @Autowired
    private CarDao carDao;

    @Override
    public List<Car> findAll() {
        return carDao.findAll();
    }

    @Override
    public Car findById(int id) {
        return carDao.findById(id);
    }

    @Override
    public List<Car> findByCarName(String carName) {
        return carDao.findByCarName(carName);
    }

    @Override
    public void deleteById(int id) {
        carDao.deleteById(id);
    }

    @Override
    public void updateById(Car car) {
        carDao.updateById(car);
    }

    @Override
    public void insertCar(Car car) {
        carDao.insertCar(car);
    }

    @Override
    public synchronized boolean saleCar(Car car) {
        // 是否购买成功标记
        boolean purchase = false;
        // 客户购买数量
        int purchaseCount = car.getCarCount();
        Car beforeCarData = carDao.findById(car.getId());
        // 车辆库存数量
        int beforeCount = beforeCarData.getCarCount();
        // 如果库存大于0，购买数量也大于0，且购买数量小于或等于库存，则进行购买后减库存操作。并将购买成功标记置为true
        if ((beforeCount > 0 && purchaseCount > 0) && beforeCount >= purchaseCount) {
            beforeCarData.setCarCount(beforeCount - purchaseCount);
            carDao.updateById(beforeCarData);
            purchase = true;
        }
        return purchase;
    }

    @Override
    public List<Car> findPageDataByCarLike(String like, int pageNum, int pageSize) {
        return carDao.findPageDataByCarLike(like, (pageNum - 1) * pageSize, pageSize);
    }


}
