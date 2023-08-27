package com.example.HealthFriends.service.impl;

import com.example.HealthFriends.dto.ExRecordByTypeDto;
import com.example.HealthFriends.dto.ExerciseRecordDto;
import com.example.HealthFriends.dto.ExerciseTypeDto;
import com.example.HealthFriends.dto.RecordingDto;
import com.example.HealthFriends.entity.ExerciseRecordData;
import com.example.HealthFriends.entity.ExerciseTypeData;
import com.example.HealthFriends.entity.UserData;
import com.example.HealthFriends.repository.JPAExerciseRepository;
import com.example.HealthFriends.repository.JPAExerciseTypeRepository;
import com.example.HealthFriends.repository.JPAUserRepository;
import com.example.HealthFriends.service.ExerciseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.rmi.NoSuchObjectException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Optional;

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
        Optional<UserData> record = jpaUserRepository.findById(userId);
        if (record.isEmpty()) {
            throw new NoSuchObjectException("There is NO such user_id!!");
        }

        List<ExerciseTypeData> findList = jpaExerciseTypeRepository.findByUserId(userId);

        ArrayList<ExerciseTypeDto> etdList = new ArrayList<>();

        for (ExerciseTypeData etd : findList) {
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

        Optional<UserData> user = jpaUserRepository.findById(exerciseTypeDto.getCreated_by());
        if (user.isEmpty()) {
            throw new NoSuchObjectException("There is NO such user_id!!");
        }

        ExerciseTypeData entity = exerciseTypeDto.toEntity();

        ExerciseTypeData saved = jpaExerciseTypeRepository.save(entity);

        exerciseTypeDto.setId(saved.getId());

        return exerciseTypeDto;
    }

    @Override
    public ExerciseRecordDto exerciseStart(Long uid, Long exid) throws NoSuchObjectException {

        Optional<ExerciseTypeData> exercise = jpaExerciseTypeRepository.findById(exid);
        if (exercise.isEmpty()) {
            throw new NoSuchObjectException("There is NO such exercise_no!!");
        }
        Optional<UserData> user = jpaUserRepository.findById(uid);
        if (user.isEmpty()) {
            throw new NoSuchObjectException("There is NO such user_id!!");
        }

        ExerciseRecordDto exerciseRecordDto = new ExerciseRecordDto();

        exerciseRecordDto.setUserId(uid);
        exerciseRecordDto.setExerciseNo(exid);
        exerciseRecordDto.setStartTime(new Timestamp(System.currentTimeMillis()));

        ExerciseRecordData saved = jpaExerciseRepository.save(exerciseRecordDto.toEntity());

        exerciseRecordDto.setId(saved.getId());

        return exerciseRecordDto;
    }

    @Override
    public ExerciseRecordDto exerciseTerminate(Long recordId) throws NoSuchObjectException {
        Optional<ExerciseRecordData> record = jpaExerciseRepository.findById(recordId);
        if (record.isEmpty()) {
            throw new NoSuchObjectException("There is NO such record_id!!");
        }
        ExerciseRecordData data = record.get();
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
        Optional<ExerciseRecordData> byId = jpaExerciseRepository.findById(recordingDto.getId());
        if (byId.isEmpty()) {
            throw new NoSuchObjectException("There is NO such exercise_no!!");
        }

        ExerciseRecordData data = byId.get();
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
        Optional<UserData> byUserId = jpaUserRepository.findById(user_id);
        if (byUserId.isEmpty()) {
            throw new NoSuchObjectException("There is NO such user_id!!");
        }

        List<ExerciseRecordData> dataList = null;
        Optional<ExerciseTypeData> byExId = null;

        if (exercise_no == 0L) {
            dataList = jpaExerciseRepository.findByUserId(user_id);
        } else {
            byExId = jpaExerciseTypeRepository.findById(exercise_no);
            if (byExId.isEmpty()) {
                throw new NoSuchObjectException("There is NO such exercise_no!!");
            }

            dataList = jpaExerciseRepository.findByUserIdAndExerciseNo(user_id, exercise_no);
        }

        List<ExRecordByTypeDto> dtoList = new ArrayList<>();

        for (ExerciseRecordData ed : dataList) {
            ExRecordByTypeDto dto = new ExRecordByTypeDto();

            dto.setId(ed.getId());
            dto.setStart_time(ed.getStartTime());
            dto.setExercise_time(ed.getExTime());
            dto.setCount(ed.getCount());
            dto.setSets(ed.getSets());

            String exName = jpaExerciseTypeRepository.findById(ed.getExerciseNo()).get().getExName();

            dto.setTitle(exName);

            dtoList.add(dto);
        }

        return dtoList;
    }

    public List<ExRecordByTypeDto> getDailyExerciseRecordById(Long id) throws NoSuchObjectException {
        Optional<ExerciseRecordData> byId = jpaExerciseRepository.findById(id);
        if (byId.isEmpty()) {
            throw new NoSuchObjectException("There is NO such id!!");
        }

        ExerciseRecordData data = byId.get();

        String date1 = new SimpleDateFormat("yyyy-MM-dd").format(data.getStartTime());
        Calendar cal = Calendar.getInstance();
        cal.setTime(data.getStartTime());
        cal.add(Calendar.DATE, 1);
        Timestamp tmp = new Timestamp(data.getStartTime().getTime());
        tmp.setTime(cal.getTime().getTime());
        String date2 = new SimpleDateFormat("yyyy-MM-dd").format(tmp);

        List<ExerciseRecordData> byDate = jpaExerciseRepository.findByDate(date1, date2);

        List<ExRecordByTypeDto> dtoList = new ArrayList<>();

        for (ExerciseRecordData erd : byDate) {
            ExRecordByTypeDto dto = new ExRecordByTypeDto();

            dto.setId(erd.getId());
            dto.setStart_time(erd.getStartTime());
            dto.setExercise_time(erd.getExTime());
            dto.setSets(erd.getSets());
            dto.setCount(erd.getCount());

            dto.setTitle(jpaExerciseTypeRepository.findById(erd.getExerciseNo()).get().getExName());

            dtoList.add(dto);
        }

        return dtoList;
    }

}
