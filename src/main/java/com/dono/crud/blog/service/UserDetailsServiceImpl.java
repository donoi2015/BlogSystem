package com.dono.crud.blog.service;

import com.dono.crud.blog.model.Reader;
import com.dono.crud.blog.repository.ReaderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Service("myservice")
public class UserDetailsServiceImpl implements UserDetailsService {

    private final
    ReaderService readerService;

    @Autowired
    public UserDetailsServiceImpl(ReaderService readerService) {
        this.readerService = readerService;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Reader reader = readerService.getOne(username);
        return new User(reader.getUsername(), reader.getPassword(), getGrantedAuthorities(reader));
    }

    private List<GrantedAuthority> getGrantedAuthorities(Reader reader) {
        List<GrantedAuthority> list = new ArrayList<>();
        list.add(new SimpleGrantedAuthority("ROLE_" + reader.getUserType()));
        return list;
    }
}
