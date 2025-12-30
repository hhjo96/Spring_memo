package com.example.memo.service;

import com.example.memo.dto.*;
import com.example.memo.entity.Memo;
import com.example.memo.repository.MemoRepository;
import lombok.RequiredArgsConstructor;
import org.jspecify.annotations.Nullable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class MemoService {
    private final MemoRepository memoRepository;

    @Transactional
    public MemoCreateResponse save(MemoCreateRequest request) {
        Memo memo = new Memo(request.getText());
        Memo savedMemo = memoRepository.save(memo);
        return new MemoCreateResponse(savedMemo.getId(), savedMemo.getText(), savedMemo.getCreatedAt(), savedMemo.getModifiedAt());
    }

    @Transactional(readOnly = true)
    public List<MemoGetResponse> findAll() {
        List<Memo> memos = memoRepository.findAll();

//        List<MemoGetResponse> memosGetResponse = new ArrayList<>();
//        for (Memo memo : memos) {
//            MemoGetResponse Response = new MemoGetResponse(memo.getId(), memo.getText(), memo.getCreatedAt(), memo.getModifiedAt());
//            memosGetResponse.add(Response);
//        }

        return memos.stream().map(memo -> new MemoGetResponse(memo.getId(), memo.getText(), memo.getCreatedAt(), memo.getModifiedAt())).toList();
    }

    @Transactional(readOnly = true)
    public MemoGetResponse findOne(Long memoId) {
        Memo memo = memoRepository.findById(memoId).orElseThrow(() -> new IllegalStateException("none"));
        return new MemoGetResponse(memo.getId(), memo.getText(), memo.getCreatedAt(), memo.getModifiedAt());
    }

    @Transactional
    public MemoUpdateResponse update(Long memoId, MemoUpdateRequest request) {
        Memo memo = memoRepository.findById(memoId).orElseThrow(() -> new IllegalStateException("none"));
        memo.update(request.getText());
        return new MemoUpdateResponse(memo.getId(), memo.getText(), memo.getCreatedAt(), memo.getModifiedAt());
    }

    @Transactional
    public void delete(Long memoId) {
        boolean existence = memoRepository.existsById(memoId);
        if (!existence) {
            throw new IllegalStateException("없는 건데 왜 삭제하려고 하세요!!~");
        }
        memoRepository.deleteById(memoId);
    }
}
