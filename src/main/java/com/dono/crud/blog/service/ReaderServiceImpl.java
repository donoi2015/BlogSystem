package com.dono.crud.blog.service;

import com.dono.crud.blog.model.Reader;
import com.dono.crud.blog.repository.PostRepository;
import com.dono.crud.blog.repository.ReaderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReaderServiceImpl implements ReaderService {

    @Autowired
    ReaderRepository readerRepository;

    @Override
    public List<Reader> getAll() {
        return readerRepository.findAll();
    }

    @Override
    public Reader getOne(int id){
        return readerRepository.findOne(id);
    }

    @Override
    public Reader getOne(String username) {
        return readerRepository.findByUsername(username);
    }

    @Override
    public void save(Reader reader) {
        readerRepository.save(reader);
    }

    @Override
    public void delete(Reader reader) {
        readerRepository.delete(reader);
    }

}
