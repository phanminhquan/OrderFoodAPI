package com.example.orderfoodapi.service.impl;

import com.example.orderfoodapi.converter.FoodConverter;
import com.example.orderfoodapi.dto.FoodDTO;
import com.example.orderfoodapi.entity.Food;
import com.example.orderfoodapi.entity.Loaifood;
import com.example.orderfoodapi.repository.FoodRepository;
import com.example.orderfoodapi.repository.LoaiFoodRepository;
import com.example.orderfoodapi.service.IFoodService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class FoodService implements IFoodService {
    @Autowired
    private FoodRepository foodRepository;
    @Autowired
    private LoaiFoodRepository loaiFoodRepository;
    @Autowired
    private FoodConverter foodConverter;
    @Override
    public FoodDTO save(FoodDTO foodDTO) {
        Food food = new Food();
        if(foodDTO.getId() != null){
            Food oldFoodEntity = foodRepository.findFoodById(foodDTO.getId());
            food = foodConverter.toEntity(foodDTO,oldFoodEntity);
        }else {
            food = foodConverter.toEntity(foodDTO);
        }
        Loaifood loaifood = loaiFoodRepository.findLoaifoodById(foodDTO.getIdLoai());
        food.setLoaifood(loaifood);
        food = foodRepository.save(food);
        return foodConverter.toDTO(food);
    }

    @Override
    public void delete(int[] ids) {
        for(int item: ids){
            foodRepository.deleteById(item);
        }
    }
}
