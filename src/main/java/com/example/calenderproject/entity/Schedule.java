package com.example.calenderproject.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@Entity
@Table(name = "schedules")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Schedule extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;
    private String title;
    private String content;
    private String name;
    private String password;

    public Schedule(String title, String content, String name, String password, LocalDateTime createdAt, LocalDateTime modifiedAt) {
        this.title = title;
        this.content = content;
        this.name = name;
        this.password = password;

    }

    public void updateSchedule(String title, String content, String name, String password, LocalDateTime createdAt, LocalDateTime modifiedAt) {
        this.title = title;
        this.content = content;
        this.name = name;
        this.password = password;
    }
@PrePersist
    public void prePersist(){
        if (getModifiedAt() == null){
            setModifiedAt(getCreatedAt());
        }
    }
}
