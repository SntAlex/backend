package ftc.shift.sample.repositories;

import ftc.shift.sample.exception.BadRequest;
import ftc.shift.sample.exception.NotFoundException;
import ftc.shift.sample.models.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.annotation.PostConstruct;
import java.util.Collection;
import java.util.List;

@Repository
@ConditionalOnProperty(name = "use.database", havingValue = "true")
public class DatabaseSubjectsRepository implements ISubjects {
    private NamedParameterJdbcTemplate jdbcTemplate;
    private SubjectExtractor subjectExtractor;

    enum Day{
        MONDAY("Monday"),
        TUESDAY("Tuesday"),
        WEDNESDAY("Wednesday"),
        THURSDAY("Thursday"),
        FRIDAY("Friday"),
        SATURDAY("Saturday"),
        SUNDAY("Sunday");

        private String day;

        Day(String day) {
            this.day = day;
        }

        public String getDay() {
            return day;
        }
    }

    @Autowired
    public DatabaseSubjectsRepository(NamedParameterJdbcTemplate jdbcTemplate, SubjectExtractor subjectExtractor) {
        this.jdbcTemplate = jdbcTemplate;
        this.subjectExtractor = subjectExtractor;
    }
/*
    @PostConstruct
    public void initialize() {
        String createSubjectTableSql = "create table SUBJECTS (" +
                "SUBJECT_ID  INTEGER," +
                "DAY  VARCHAR(64)," +
                "NAME     VARCHAR(64)," +
                "AUDIENCE   VARCHAR(64)," +
                "TIME    VARCHAR(64)," +
                "NOTE    VARCHAR(64)" +
                ");";
        jdbcTemplate.update(createSubjectTableSql, new MapSqlParameterSource());
    }
*/

        @Override
    public void addSubject(String dayName, Subject subject) {
            checkDay(dayName);

            String insertSubjectSql = "insert into SUBJECTS (SUBJECT_ID, DAY, NAME, AUDIENCE, TIME, NOTE) values (:subjectId, :dayName, :name, :audience, :time, :note)";
            MapSqlParameterSource subjectParams = new MapSqlParameterSource()
                    .addValue("subjectId", subject.getId())
                    .addValue("dayName", dayName)
                    .addValue("name", subject.getName())
                    .addValue("audience", subject.getAudience())
                    .addValue("time", subject.getTime())
                    .addValue("note", subject.getNote());

            jdbcTemplate.update(insertSubjectSql, subjectParams);
        }

    @Override
    public Collection<Subject> getAllSubjects(String dayName) {
        checkDay(dayName);
        String sql = "select DAY, SUBJECTS.SUBJECT_ID, NAME, AUDIENCE, TIME, NOTE " +
                "from SUBJECTS " +
                "where SUBJECTS.DAY=:dayName";
        MapSqlParameterSource params = new MapSqlParameterSource()
                .addValue("dayName", dayName);

        return jdbcTemplate.query(sql, params, subjectExtractor);
    }

    @Override
    public void clearSubjects(String dayName) {
        checkDay(dayName);
        String deleteSubjectsSql = "delete from SUBJECTS";
        MapSqlParameterSource params = new MapSqlParameterSource();
        jdbcTemplate.update(deleteSubjectsSql, params);
    }

    @Override
    public void changeSubject(String dayName, Subject subject) {
        checkDay(dayName);
        String updateSubjectSql = "update SUBJECTS " +
                "set " +
                "NAME=:name, " +
                "AUDIENCE=:audience, " +
                "TIME=:time, " +
                "NOTE=:note " +
                "where SUBJECT_ID=:subjectId and DAY=:dayName";

        MapSqlParameterSource params = new MapSqlParameterSource()
                .addValue("subjectId", subject.getId())
                .addValue("dayName", dayName)
                .addValue("name", subject.getName())
                .addValue("audience", subject.getAudience())
                .addValue("time", subject.getTime())
                .addValue("note", subject.getNote());

        jdbcTemplate.update(updateSubjectSql, params);
    }

    @Override
    public void clearSubject(String dayName, Integer subjectId) {
        checkDay(dayName);
        String deleteSubjectSql = "delete from SUBJECTS where SUBJECT_ID=:subjectId and DAY=:dayName";
        MapSqlParameterSource params = new MapSqlParameterSource()
                .addValue("subjectId", subjectId)
                .addValue("dayName", dayName);
        jdbcTemplate.update(deleteSubjectSql, params);

    }

    @Override
    public Subject fetchSubject(String dayName, Integer subjectId) {
        checkDay(dayName);
        String sql = "select DAY, SUBJECTS.SUBJECT_ID, NAME, AUDIENCE, TIME, NOTE " +
                "from SUBJECTS " +
                "where SUBJECTS.SUBJECT_ID=:subjectId and SUBJECTS.DAY=:dayName";
        MapSqlParameterSource params = new MapSqlParameterSource()
                .addValue("dayName", dayName)
                .addValue("subjectId", subjectId);

        List<Subject> subjects = jdbcTemplate.query(sql, params, subjectExtractor);

        if (subjects.isEmpty()) {
            throw new NotFoundException();
        }
        return subjects.get(0);
    }

    private void checkDay(String dayName)
    {
        Day[] days = Day.values();
        Boolean check = false;
        for (Day day : days) {
            if(day.getDay().equals(dayName)){
                check = true;
            }
        }

        if (!check)
            throw new BadRequest();
    }
}
