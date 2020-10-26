package com.rest.its.services;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.rest.its.dto.InterviewDto;
import com.rest.its.dto.UserDto;
import com.rest.its.entities.Interview;
import com.rest.its.entities.User;

@Component
public class ConverterService {
	
	@Autowired
	private ModelMapper modelMapper;
	
	public UserDto convertToDto(User user) {
		return modelMapper.map(user, UserDto.class);
	}
	
	public InterviewDto convertToDto(Interview interview) {
		return modelMapper.map(interview, InterviewDto.class);
	}

}
