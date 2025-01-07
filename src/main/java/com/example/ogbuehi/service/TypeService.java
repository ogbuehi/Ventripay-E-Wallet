package com.example.ogbuehi.service;


import com.example.ogbuehi.model.Type;
import com.example.ogbuehi.repository.TypeRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j(topic = "TypeService")
@Service
@RequiredArgsConstructor
public class TypeService {
    private final TypeRepository typeRepository;
    /**
     * Fetches a single type reference (entity) by the given id
     *
     * @param id
     * @return Type
     */
    public Type getReferenceById(long id) {
        return typeRepository.getReferenceById(id);
    }
}
