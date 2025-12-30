package com.example.memo.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity
@Table(name = "memos")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Memo extends BaseEntity {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String text;

    public Memo(String text) {
        this.text = text;
    }

    public void update(String text) {
        this.text = text;
    }
}
