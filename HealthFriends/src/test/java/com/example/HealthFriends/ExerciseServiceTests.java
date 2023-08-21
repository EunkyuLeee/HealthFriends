package com.example.HealthFriends;

import com.example.HealthFriends.service.impl.ExerciseServiceImpl;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class ExerciseServiceTests {
    @Autowired
    private ExerciseServiceImpl exerciseService;

//    @Test
//    public void testGetListByExerciseNo() {
//        System.out.println(exerciseService.getListByExerciseNo(3L));
//    }

    @Test
    public void testSortedListByTime(){
        System.out.println(exerciseService.sortedListByTime(3L));
    }
}
