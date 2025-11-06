package com.example.calenderproject.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity
@Table(name = "comments")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Comment extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private String leaveComment;
    @Column(nullable = false)
    private String writer;
    @Column(nullable = false)
    private String password;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "schedule_id")
    private Schedule schedule;


    public Comment(String leaveComment, String writer, String password, Schedule schedule) {
        this.leaveComment = leaveComment;
        this.writer = writer;
        this.password = password;
        this.schedule = schedule;
    }
}
