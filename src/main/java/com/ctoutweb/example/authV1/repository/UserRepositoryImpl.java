package com.ctoutweb.example.authV1.repository;

import java.sql.PreparedStatement;
import java.sql.Statement;
import java.sql.Timestamp;
import java.time.Instant;
import java.util.Optional;

import org.springframework.dao.IncorrectResultSizeDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import com.ctoutweb.example.authV1.model.RegisterRequest;
import com.ctoutweb.example.authV1.model.User;

@Repository
public class UserRepositoryImpl implements UserRepository {
	
	private final JdbcTemplate jdbcTemplate;
	

	public UserRepositoryImpl(JdbcTemplate jdbcTemplate) {
		super();
		this.jdbcTemplate = jdbcTemplate;
	}

	@Override
	public Optional<User> findUserByEmail(String email) {
		try {
			String query = "SELECT * FROM users WHERE email = ? LIMIT 1";
			User user = jdbcTemplate.queryForObject(query, BeanPropertyRowMapper.newInstance(User.class), email);
			return Optional.of(user);
			
		} catch (IncorrectResultSizeDataAccessException e) {
			return Optional.empty();
		}		
	}

	@SuppressWarnings("null")
	@Override
	public Long saveUser(RegisterRequest request) {
		KeyHolder keyHolder = new GeneratedKeyHolder();
		
		String query = "INSERT INTO users (email, password, created_at, updated_at) VALUES (? , ? , ?, ?)";
		
		Instant createdDate = Instant.now();
		
		long row = jdbcTemplate.update(connection -> {
			PreparedStatement pst = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
			pst.setString(1, request.getEmail());
			pst.setString(2, request.getPassword());
			pst.setTimestamp(3,Timestamp.from(createdDate));
			pst.setTimestamp(4,Timestamp.from(createdDate));
			return pst;
		}, keyHolder);;
		
		if(row > 0 ) {
			if(keyHolder.getKeys().size() <= 1) return null;		
			
			int id = (int)keyHolder.getKeys().get("id");
			
			return Long.valueOf(id);
		}
		
		return null;
		
		
	
	}

	
}
