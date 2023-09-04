package com.example.HealthFriends.controller;

import com.example.HealthFriends.entity.testData;
import com.example.HealthFriends.repository.JPATestRepository;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Tag(name = "HI", description = "테스트용")
@RestController
public class TestController {

    @Autowired
    private JPATestRepository jpaTestRepository;

    @Operation(summary = "get Data from Database", description = "DB 데이터 출력")
    @ApiResponse(responseCode = "200", description = "성공")
    @GetMapping("/api/test/get")
    public List<testData> getData() {
        return jpaTestRepository.findAll();
    }

    @Operation(summary = "insert Data to Database", description = "DB 서버 데이터 입력")
    @ApiResponse(responseCode = "200", description = "성공")
    @PutMapping("/api/test/insert")
    public void insertData(@RequestParam Integer integer) {
        testData data = new testData();
        data.setData(integer);
        jpaTestRepository.save(data);
    }

}
