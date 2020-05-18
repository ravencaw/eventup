package es.eventup.app.models.service;


import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import es.eventup.app.models.entity.Blog;
import es.eventup.app.models.repository.BlogRepository;



@Service
public class BlogServiceImpl implements BlogService {

	@Autowired
	BlogRepository repository;

	@Override
	@Transactional(readOnly = true)
	public List<Blog> findAll() {
		return (List<Blog>) repository.findAll();
	}

	@Override
	@Transactional
	public void save(Blog entity) {
		repository.save(entity);
	}

	@Override
	@Transactional(readOnly = true)
	public Optional<Blog> findOne(Long id) {
		return repository.findById(id);
	}

	@Override
	@Transactional
	public void delete(Long id) {
		repository.deleteById(id);
	}
	
}