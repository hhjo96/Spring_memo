package com.example.memo.dto;

import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class MemoCreateResponse {

    private final Long id;
    private final String text;
    private final LocalDateTime createdAt;
    private final LocalDateTime modifiedAt;

    public MemoCreateResponse(Long id, String text, LocalDateTime createdAt, LocalDateTime modifiedAt) {
        this.id = id;
        this.text = text;
        this.createdAt = createdAt;
        this.modifiedAt = modifiedAt;
    }
}
