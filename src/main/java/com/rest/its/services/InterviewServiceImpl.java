package com.rest.its.services;

import com.rest.its.daos.InterviewRepository;
import com.rest.its.daos.UserRepository;
import com.rest.its.dto.InterviewDto;
import com.rest.its.entities.Interview;
import com.rest.its.entities.User;
import com.rest.its.exceptions.CustomException;
import com.rest.its.services.ValidationService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class InterviewServiceImpl implements InterviewService {

    @Autowired
    InterviewRepository interviewRepository;

    @Autowired
    UserRepository userRepository;
    
    @Autowired
    private ConverterService converterservice;

    @Override
    public List<Interview> getInterviews() {
        return interviewRepository.findAll();
    }

    @Override
    public int getInterviewsCount() {
        return interviewRepository.findAll().size();
    }

    @Override
    public InterviewDto findByInterviewId(int interviewId) {
        return converterservice.convertToDto(interviewRepository.findById(interviewId).orElseThrow(() -> new CustomException("Interview Id : " + interviewId + " is not found.")));       
    }

    @Override
    public List<InterviewDto> findAllByInterviewName(String interviewName) {
        return interviewRepository.findByInterviewName(interviewName).stream().map(converterservice::convertToDto).collect(Collectors.toList());
    }

    @Override
    public List<InterviewDto> findAllByInterviewerName(String interviewerName) {
        return interviewRepository.findAllByInterviewerName(interviewerName).stream().map(converterservice::convertToDto).collect(Collectors.toList());
    }

    @Override
    public List<Interview> findAllById(List<Integer> interviewIds) {
        return interviewRepository.findAllById(interviewIds);
    }

    @Override
    @Transactional
    public Interview addInterview(Interview interview) {
    	ValidationService interviewValidation = new ValidationService();
        if (interviewValidation.validateInterview(interview)) {
            if (null == interview.getUsers()) {
                if (!interviewRepository.findById(interview.getInterviewId()).isPresent()) {
                    interviewRepository.save(interview);
                    
                }
                else {
                	throw new CustomException("Interview Id : " + interview.getInterviewId() + " is already present.");
                }
            } 
            else {
                throw new CustomException("Can't assign users when creating new interview.");}
        } 
        
        else 
            throw new CustomException(interviewValidation.getErrors());
        return interview;
    }

    @Override
    public void deleteInterview(int interviewId) {
        interviewRepository.findById(interviewId).orElseThrow(() -> new CustomException("Interview Id : " + interviewId + " is not found."));
        interviewRepository.deleteById(interviewId);

    }

    @Override
    public Interview updateInterview(Interview updatedInterview) {
    	ValidationService interviewValidation = new ValidationService();
        if (interviewValidation.validateInterview(updatedInterview)) {
            Interview curInterview = interviewRepository.findById(updatedInterview.getInterviewId()).orElseThrow(() -> new CustomException("Interview Id : " + updatedInterview.getInterviewId() + " is not found."));
            curInterview.setInterview(updatedInterview);
            if (null != updatedInterview.getUsers()) {
                List<Integer> userIds = updatedInterview.getUsers().stream().map(User::getUserId).collect(Collectors.toList());
                List<User> users = userRepository.findAllById(userIds);
                curInterview.addUsers(users);
            }
            interviewRepository.save(curInterview);

        } else
            throw new CustomException(interviewValidation.getErrors());

        return updatedInterview;
    }

    @Override
    public Interview addcandidateToInterview(int interviewId, int userId) {
    	//throw custom exception if user id already exist
        Interview interview = interviewRepository.findById(interviewId).orElseThrow(() -> new CustomException("Invalid Interview id : " + interviewId));
        User user = userRepository.findById(userId).orElseThrow(() -> new CustomException("Candidate not registered : " + userId));
        if (interview.getUsers().stream().noneMatch(user1 -> user1.getUserId() == userId)) {
            interview.getUsers().add(user);
            interviewRepository.save(interview);
        }
        else {
        	//throw custom exception if user id already exist	
        	throw new CustomException(userId + " is already mapped to the interview id " + interviewId );
        }
        return interview;
    }

    @Override
    public Interview removeUsersFromInterview(int interviewId, int userId) {
        Interview interview = interviewRepository.findById(interviewId).orElseThrow(() -> new CustomException("Interview Id is not found."));
        User user = userRepository.findById(userId).orElseThrow(() -> new CustomException("User Id : " + userId + " is not found."));
        interview.getUsers().remove(user);
        return interviewRepository.save(interview);
    }

    @Override
    public Interview updateInterviewStatus(int interviewId, String status) {
        Interview interview = interviewRepository.findById(interviewId).orElseThrow(() -> new CustomException("Interview Id is not found."));
        interview.setInterviewStatus(status);
        return interviewRepository.save(interview);
    }
}
