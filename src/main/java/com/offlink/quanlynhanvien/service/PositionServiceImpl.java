package com.offlink.quanlynhanvien.service;

import com.offlink.quanlynhanvien.DAO.PositionRepository;
import com.offlink.quanlynhanvien.entity.Position;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public class PositionServiceImpl implements PositionService{

    // define field positionRepository
    @Autowired
    private PositionRepository positionRepository;

    @Override
    public List<Position> findAll() {
        return positionRepository.findAll();
    }

    @Override
    public Position findPositionById(long theId) {
        Optional<Position> result = positionRepository.findById(theId);
        Position position = null;
        if(result.isPresent()) {
            position = result.get();
        } else {
            throw new RuntimeException("Khong tim thay id vi tri : id = " + theId);
        }

        return position;
    }

    @Override
    public void save(Position thePosition) {
        positionRepository.save(thePosition);
    }

    @Override
    public void deletedPositionById(long theId) {
        positionRepository.deleteById(theId);
    }

    @Override
    public List<Position> searchPosition(String keyword) {
        return positionRepository.searchPosition(keyword);
    }
}
