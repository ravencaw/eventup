package es.eventup.app.models.service;

import java.util.List;
import java.util.Optional;

import org.springframework.boot.autoconfigure.data.web.SpringDataWebProperties.Pageable;
import org.springframework.data.domain.Page;

import es.eventup.app.models.entity.Blog;

public interface BlogService {
	
		public List<Blog> findAll();

		public void save(Blog entity);
		
		public Optional<Blog> findOne(Long id);
		
		public void delete(Long id);
	}


