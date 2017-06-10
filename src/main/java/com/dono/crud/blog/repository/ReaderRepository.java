package com.dono.crud.blog.repository;

import com.dono.crud.blog.model.Reader;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

@Transactional
public interface ReaderRepository extends JpaRepository<Reader, Integer> {
    Reader findByUsername(String string);
}
