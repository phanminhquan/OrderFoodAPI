package com.example.orderfoodapi.service.impl;

import com.example.orderfoodapi.converter.NhaHangConverter;
import com.example.orderfoodapi.dto.NhahangDTO;
import com.example.orderfoodapi.entity.Nhahang;
import com.example.orderfoodapi.repository.NhaHangRepository;
import com.example.orderfoodapi.service.INhaHangService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class NhaHangService implements INhaHangService {

    @Autowired
    private NhaHangConverter nhaHangConverter;

    @Autowired
    private NhaHangRepository nhaHangRepository;
    @Override
    public NhahangDTO save(NhahangDTO nhahangDTO) {
        Nhahang nhahang = new Nhahang();
        if(nhahangDTO.getId() != null){
            Nhahang oldNhahang = nhaHangRepository.findNhahangById(nhahangDTO.getId());
            nhahang = nhaHangConverter.toEntity(nhahangDTO,oldNhahang);
        }else{
            nhahang = nhaHangConverter.toEntity(nhahangDTO);
        }
        nhahang = nhaHangRepository.save(nhahang);
        return nhaHangConverter.toDTO(nhahang);
    }

    @Override
    public void delete(int[] ids) {
        for(int item : ids)
        {
            nhaHangRepository.deleteById(item);
        }
    }
}
