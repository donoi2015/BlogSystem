package com.dono.crud.blog.service;

import com.dono.crud.blog.model.Reader;

import java.util.List;

public interface ReaderService {
    List<Reader> findall();

    Reader getOne(int id);

    Reader getOne(String username);

    void save(Reader reader);

    void delete(Reader reader);
}
