package com.example.HealthFriends;

import com.example.HealthFriends.service.impl.ExerciseServiceImpl;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class ExerciseServiceRankingTests {
    @Autowired
    private ExerciseServiceImpl exerciseServiceImpl;

//    @Test
//    public void testGetListByExerciseNo() {
//        System.out.println(exerciseServiceImpl.getListByExerciseNo(3L));
//    }

//    @Test
//    public void testSortedListByTime(){
//        System.out.println(exerciseServiceImpl.sortedListByTime(3L));
//    }
}
