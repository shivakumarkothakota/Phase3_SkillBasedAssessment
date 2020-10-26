package com.rest.its.daos;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.w3c.dom.stylesheets.LinkStyle;

import com.rest.its.entities.Interview;

import java.util.List;

@Repository
public interface InterviewRepository extends JpaRepository<Interview, Integer> {
    List<Interview> findByInterviewName(String interviewName);
    List<Interview> findAllByInterviewerName(String interviewerName);
}
