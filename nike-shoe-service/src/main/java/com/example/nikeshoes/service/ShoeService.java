package com.example.nikeshoes.service;

import com.example.nikeshoes.domain.entity.Shoe;
import com.example.nikeshoes.domain.repository.ShoeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ShoeService {

    private final ShoeRepository shoeRepository;

    public List<Shoe> findAll() {
        return shoeRepository.findAll();
    }

    public void save(Shoe shoe) {
        shoeRepository.save(shoe);
    }

    public void deleteById(Long id) {
        shoeRepository.deleteById(id);
    }
}

