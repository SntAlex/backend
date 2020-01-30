package ftc.shift.sample.repositories;

import ftc.shift.sample.models.Subject;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class SubjectExtractor implements ResultSetExtractor<List<Subject>> {
    @Override
    public List<Subject> extractData(ResultSet rs) throws SQLException, DataAccessException {
        Map<Integer, Subject> subjectMap = new HashMap<>();

        while (rs.next()) {
            Integer subjectId = rs.getInt("SUBJECT_ID");

            Subject subject;
            if (subjectMap.containsKey(subjectId)) {
                subject = subjectMap.get(subjectId);
            } else {
                subject = new Subject();

                subject.setId(rs.getInt("SUBJECT_ID"));
                subject.setName(rs.getString("NAME"));
                subject.setAudience(rs.getString("AUDIENCE"));
                subject.setTime(rs.getString("TIME"));
                subject.setNote(rs.getString("NOTE"));

                subjectMap.put(subjectId, subject);
            }
        }
        return new ArrayList<>(subjectMap.values());
    }
}
