package com.example.HealthFriends.service.impl;

import com.example.HealthFriends.dto.*;
import com.example.HealthFriends.entity.ExRanking;
import com.example.HealthFriends.entity.ExDailyRecord;
import com.example.HealthFriends.entity.ExerciseRecord;
import com.example.HealthFriends.entity.Exercise;
import com.example.HealthFriends.entity.User;
import com.example.HealthFriends.repository.JPAExerciseRepository;
import com.example.HealthFriends.repository.JPAExerciseTypeRepository;
import com.example.HealthFriends.repository.JPAUserRepository;
import com.example.HealthFriends.service.ExerciseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.rmi.NoSuchObjectException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class ExerciseServiceImpl implements ExerciseService {

    private final JPAExerciseRepository jpaExerciseRepository;
    private final JPAExerciseTypeRepository jpaExerciseTypeRepository;
    private final JPAUserRepository jpaUserRepository;

    @Autowired
    public ExerciseServiceImpl
            (JPAExerciseRepository jpaExerciseRepository,
             JPAExerciseTypeRepository jpaExerciseTypeRepository,
             JPAUserRepository jpaUserRepository) {
        this.jpaExerciseRepository = jpaExerciseRepository;
        this.jpaExerciseTypeRepository = jpaExerciseTypeRepository;
        this.jpaUserRepository = jpaUserRepository;
    }

    @Override
    public List<ExerciseTypeDto> getExerciseTypesByUserId(Long userId) throws NoSuchObjectException {
        Optional<User> record = jpaUserRepository.findById(userId);
        if (record.isEmpty()) {
            throw new NoSuchObjectException("There is NO such user_id!!");
        }

        List<Exercise> findList = jpaExerciseTypeRepository.findByUserId(userId);

        ArrayList<ExerciseTypeDto> etdList = new ArrayList<>();

        for (Exercise etd : findList) {
            ExerciseTypeDto dto = new ExerciseTypeDto();

            dto.setId(etd.getId());
            dto.setExercise_name(etd.getExName());
            dto.setCreated_by(etd.getCBy());

            etdList.add(dto);
        }

        return etdList;
    }

    @Override
    public ExerciseTypeDto addExType(ExerciseTypeDto exerciseTypeDto) throws NoSuchObjectException {

        Optional<User> user = jpaUserRepository.findById(exerciseTypeDto.getCreated_by());
        if (user.isEmpty()) {
            throw new NoSuchObjectException("There is NO such user_id!!");
        }

        Exercise entity = exerciseTypeDto.toEntity();

        Exercise saved = jpaExerciseTypeRepository.save(entity);

        exerciseTypeDto.setId(saved.getId());

        return exerciseTypeDto;
    }

    @Override
    public ExerciseRecordDto exerciseStart(Long uid, Long exid) throws NoSuchObjectException {

        Optional<Exercise> exercise = jpaExerciseTypeRepository.findById(exid);
        if (exercise.isEmpty()) {
            throw new NoSuchObjectException("There is NO such exercise_no!!");
        }
        Optional<User> user = jpaUserRepository.findById(uid);
        if (user.isEmpty()) {
            throw new NoSuchObjectException("There is NO such user_id!!");
        }

        ExerciseRecordDto exerciseRecordDto = new ExerciseRecordDto();

        exerciseRecordDto.setUserId(uid);
        exerciseRecordDto.setExerciseNo(exid);
        exerciseRecordDto.setStartTime(new Timestamp(System.currentTimeMillis()));

        ExerciseRecord saved = jpaExerciseRepository.save(exerciseRecordDto.toEntity());

        exerciseRecordDto.setId(saved.getId());

        return exerciseRecordDto;
    }

    @Override
    public ExerciseRecordDto exerciseTerminate(Long recordId) throws NoSuchObjectException {
        Optional<ExerciseRecord> record = jpaExerciseRepository.findById(recordId);
        if (record.isEmpty()) {
            throw new NoSuchObjectException("There is NO such record_id!!");
        }
        ExerciseRecord data = record.get();
        if (data.getCount() != null || data.getExTime() != null) {
            throw new IllegalArgumentException("This is NOT an ongoing exercise!!");
        }

        jpaExerciseRepository.deleteById(recordId);

        ExerciseRecordDto dto = new ExerciseRecordDto();

        dto.setId(data.getId());
        dto.setExerciseNo(data.getExerciseNo());
        dto.setStartTime(data.getStartTime());
        dto.setUserId(data.getUserId());

        return dto;
    }

    @Override
    public ExerciseRecordDto exerciseRecord(RecordingDto recordingDto) throws NoSuchObjectException {
        Optional<ExerciseRecord> byId = jpaExerciseRepository.findById(recordingDto.getId());
        if (byId.isEmpty()) {
            throw new NoSuchObjectException("There is NO such exercise_no!!");
        }

        ExerciseRecord data = byId.get();
        ExerciseRecordDto recordDto = new ExerciseRecordDto();

        recordDto.setId(data.getId());
        recordDto.setExerciseNo(data.getExerciseNo());
        recordDto.setUserId(data.getUserId());
        recordDto.setStartTime(data.getStartTime());

        String time = recordingDto.getExercise_time();
        Integer sets = recordingDto.getSets();
        Integer count = recordingDto.getCount();

        if (time != null) {
            if (!isTimeFormat(time)) {
                throw new IllegalArgumentException("This time dose NOT match the time format!! (HH:MM:SS.SS)");
            }
            recordDto.setExTime(time);
        } else if (sets != null && count != null) {
            if (sets > count) {
                throw new IllegalArgumentException("The set CANNOT be greater than the count!!");
            }
            recordDto.setSets(sets);
            recordDto.setCount(count);
        } else {
            throw new IllegalArgumentException("Data is requierd.");
        }

        jpaExerciseRepository.save(recordDto.toEntity());

        return recordDto;
    }

    private boolean isTimeFormat(String str) {
        return str.matches("\\d+:\\d{2}:\\d{2}.\\d{2}");
    }

    @Override
    public List<ExRecordByTypeDto> getExerciseRecord(Long user_id, Long exercise_no) throws NoSuchObjectException {
        Optional<User> byUserId = jpaUserRepository.findById(user_id);
        if (byUserId.isEmpty()) {
            throw new NoSuchObjectException("There is NO such user_id!!");
        }

        List<ExDailyRecord> dataList = null;

        if (exercise_no == 0L) {
            dataList = jpaExerciseRepository.findByUserId(user_id);
        } else {
            Optional<Exercise> byExId;
            byExId = jpaExerciseTypeRepository.findById(exercise_no);
            if (byExId.isEmpty()) {
                throw new NoSuchObjectException("There is NO such exercise_no!!");
            }

            dataList = jpaExerciseRepository.findByUserIdAndExerciseNo(user_id, exercise_no);
        }

        List<ExRecordByTypeDto> dtoList = new ArrayList<>();

        for (ExDailyRecord ed : dataList) {
            ExRecordByTypeDto dto = new ExRecordByTypeDto();

            dto.setId(ed.getId());
            dto.setStart_time(ed.getStart_time());
            dto.setExercise_time(ed.getExercise_time());
            dto.setCount(ed.getCount());
            dto.setSets(ed.getSets());
            dto.setTitle(ed.getExercise_name());

            dtoList.add(dto);
        }

        return dtoList;
    }

    public List<ExRecordByTypeDto> getDailyExerciseRecordById(Long id) throws NoSuchObjectException {
        Optional<ExerciseRecord> byId = jpaExerciseRepository.findById(id);
        if (byId.isEmpty()) {
            throw new NoSuchObjectException("There is NO such id!!");
        }

        ExerciseRecord data = byId.get();

        String date1 = new SimpleDateFormat("yyyy-MM-dd").format(data.getStartTime());
        Calendar cal = Calendar.getInstance();
        cal.setTime(data.getStartTime());
        cal.add(Calendar.DATE, 1);
        Timestamp tmp = new Timestamp(data.getStartTime().getTime());
        tmp.setTime(cal.getTime().getTime());
        String date2 = new SimpleDateFormat("yyyy-MM-dd").format(tmp);

        List<ExDailyRecord> byDate = jpaExerciseRepository.findByDate(data.getUserId(), date1, date2);

        List<ExRecordByTypeDto> dtoList = new ArrayList<>();

        for (ExDailyRecord erd : byDate) {
            ExRecordByTypeDto dto = new ExRecordByTypeDto();

            dto.setId(erd.getId());
            dto.setStart_time(erd.getStart_time());
            dto.setExercise_time(erd.getExercise_time());
            dto.setSets(erd.getSets());
            dto.setCount(erd.getCount());
            dto.setTitle(erd.getExercise_name());

            dtoList.add(dto);
        }

        return dtoList;
    }


    @Override
    public List<Ranking> getRanking(Long exNo) throws NoSuchObjectException {
        Optional<Exercise> byId = jpaExerciseTypeRepository.findById(exNo);
        if (byId.isEmpty()) {
            throw new NoSuchObjectException("There is NO such exercise_no!!");
        } else if (byId.get().getCBy() != 0L) {
            throw new IllegalArgumentException("This exercise_no is NOT accessable!!");
        }

        List<ExRanking> rankList = jpaExerciseRepository.getRankList(exNo);
        return getRankings(rankList);
    }

    @Override
    public Ranking getRankingByUserId(Long exNo, Long uid) throws NoSuchObjectException {
        Optional<User> byId = jpaUserRepository.findById(uid);
        if (byId.isEmpty() || uid == 0L) {
            throw new NoSuchObjectException("There is NO such user_id!!");
        }
        Optional<Exercise> findById = jpaExerciseTypeRepository.findById(exNo);
        if (findById.isEmpty()) {
            throw new NoSuchObjectException("There is NO such exercise_no!!");
        }

        List<ExRanking> rankList = jpaExerciseRepository.getRankList(exNo);
        List<Ranking> rankings = getRankings(rankList);

        int index = 0;

        for (ExRanking r : rankList) {
            if (Objects.equals(r.getId(), uid)) {
                break;
            }
            index++;
        }

        return rankings.get(index);
    }

    private List<Ranking> getRankings(List<ExRanking> rankList) {
        List<Ranking> ranking = new ArrayList<>();
        Ranking prev = null;
        long index = 1;

        for (ExRanking er : rankList) {
            Ranking tmp = new Ranking();

            tmp.setName(er.getName());
            tmp.setTime(er.getExercise_time());
            if (prev == null) {
                tmp.setRank(index);
            } else {
                tmp.setRank(prev.getTime().equals(er.getExercise_time()) ? prev.getRank() : index);
            }
            prev = tmp;
            ranking.add(tmp);
            index++;
        }
        return ranking;
    }
}
