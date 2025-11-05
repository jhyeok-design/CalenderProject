package com.example.calenderproject.repository;

import com.example.calenderproject.entity.Comment;
import com.example.calenderproject.entity.Schedule;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepository extends JpaRepository<Comment, Long> {
    long countBySchedule(Schedule schedule);
}
