package com.rest.its.services;

import com.rest.its.daos.UserRepository;
import com.rest.its.dto.UserDto;
import com.rest.its.entities.Interview;
import com.rest.its.entities.User;
import com.rest.its.exceptions.CustomException;
import com.rest.its.services.ValidationService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	UserRepository userRepository;

	@Autowired
	InterviewService interviewService;

	@Autowired
	private ConverterService converterservice;

	@PersistenceContext
	private EntityManager entityManger;



	// Allows to add a user
	@Override
	@Transactional
	public User addUser(User user) {
		ValidationService userValidation = new ValidationService();
		if (userValidation.validateUser(user)) {
			if (null == user.getInterviews()) {
				Optional<User> usr = userRepository.findById(user.getUserId());
				if (usr.isPresent()) {
					throw new CustomException("User Id : " + user.getUserId() + " is already present");
				} else
					userRepository.save(user);
			} else
				throw new CustomException("Can't select interviews when creating new user.");
		} else
			throw new CustomException(userValidation.getErrors());
		return user;
	}

	@Override
	public List<UserDto> getAllUsers() {
		return userRepository.findAll().stream().map(converterservice::convertToDto).collect(Collectors.toList());
	}

	@Override
	public UserDto getUserById(int userId) {
		return converterservice.convertToDto(userRepository.findById(userId).orElseThrow(() -> new CustomException("User Id" + userId + " not found.")));
	}



	@Transactional
	@Override
	public void deleteUser(int userId) {
		User user = userRepository.findById(userId).orElseThrow(() -> new CustomException("User Id : " + userId + " not found."));
		Set<Interview> interviews = user.getInterviews();
		interviews.forEach((interview) -> interviewService.removeUsersFromInterview(interview.getInterviewId(), userId));
		userRepository.deleteById(userId);
	}

	@Transactional
	@Override
	public User saveUser(User updatedUser) {
		ValidationService userValidation = new ValidationService();
		if (userValidation.validateUser(updatedUser)) {
			User curUserWithThisId = userRepository.findById(updatedUser.getUserId()).orElseThrow(() -> new CustomException("User Id : " + updatedUser.getUserId() + " not found."));
			curUserWithThisId.copyUser(updatedUser);
			if (null != updatedUser.getInterviews()) {
				updatedUser.getInterviews().forEach(interview -> {
					interviewService.addcandidateToInterview(interview.getInterviewId(), updatedUser.getUserId());
				});
			}
			userRepository.save(curUserWithThisId);
			return updatedUser;
		} else
		throw new CustomException(userValidation.getErrors());
	}
}
