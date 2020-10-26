package com.rest.its.services;

import java.util.List;

import com.rest.its.dto.InterviewDto;
import com.rest.its.entities.Interview;

public interface InterviewService {
    Interview addInterview(Interview interview);

    void deleteInterview(int interviewId);

    Interview updateInterview(Interview interview);

    List<Interview> getInterviews();

    int getInterviewsCount();

    InterviewDto findByInterviewId(int interviewId);

    List<InterviewDto> findAllByInterviewName(String interviewName);

    List<InterviewDto> findAllByInterviewerName(String interviewerName);

    List<Interview> findAllById(List<Integer> interviewIds);

    Interview addcandidateToInterview(int interviewId, int userId);

    Interview removeUsersFromInterview(int interviewId, int userId);

    Interview updateInterviewStatus(int interviewId, String status);

}
