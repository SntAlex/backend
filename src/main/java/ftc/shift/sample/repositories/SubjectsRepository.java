package ftc.shift.sample.repositories;

import ftc.shift.sample.exception.BadRequest;
import ftc.shift.sample.exception.NotFoundException;
import ftc.shift.sample.models.Subject;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

@Repository
@ConditionalOnProperty(name = "use.database", havingValue = "false")
public class SubjectsRepository implements ISubjects {

    private Map<String, Map<Integer, Subject>> dayCash = new HashMap<>();

    SubjectsRepository() {
        dayCash.put("Monday", new HashMap<>());
        dayCash.put("Tuesday", new HashMap<>());
        dayCash.put("Wednesday", new HashMap<>());
        dayCash.put("Thursday", new HashMap<>());
        dayCash.put("Friday", new HashMap<>());
        dayCash.put("Saturday", new HashMap<>());
        dayCash.put("Sunday", new HashMap<>());
    }

    @Override
    public void addSubject(String dayName, Subject subject) {
        if (!dayCash.containsKey(dayName)) {
            throw new NotFoundException();
        }
        Map<Integer, Subject> subjectCash = dayCash.get(dayName);
        if ((subjectCash.containsKey(subject.getId()))) {
            throw new BadRequest();
        }
        subjectCash.put(subject.getId(), subject);
    }

    @Override
    public Collection<Subject> getAllSubjects(String dayName) {
        if (!dayCash.containsKey(dayName)) {
            throw new NotFoundException();
        }
        Map<Integer, Subject> subjectCash = dayCash.get(dayName);
        return subjectCash.values();
    }

    @Override
    public void clearSubjects(String dayName) {
        if (!dayCash.containsKey(dayName)) {
            throw new NotFoundException();
        }
        Map<Integer, Subject> subjectCash = dayCash.get(dayName);
        subjectCash.clear();
    }

    @Override
    public void changeSubject(String dayName, Subject subject) {
        if (!dayCash.containsKey(dayName)) {
            throw new NotFoundException();
        }
        Map<Integer, Subject> subjectCash = dayCash.get(dayName);
        if (!subjectCash.containsKey(subject.getId())) {
            // У пользователя не найдена книга
            throw new NotFoundException();
        }
        subjectCash.put(subject.getId(), subject);
    }

    @Override
    public void clearSubject(String dayName, Integer subjectId) {
        if (!dayCash.containsKey(dayName)) {
            throw new NotFoundException();
        }
        Map<Integer, Subject> subjectCash = dayCash.get(dayName);
        if (!subjectCash.containsKey(subjectId)) {
            throw new NotFoundException();
        }
        subjectCash.remove(subjectId);
    }

    @Override
    public Subject fetchSubject(String dayName, Integer subjectId) {
        if (!dayCash.containsKey(dayName)) {
            throw new NotFoundException();
        }
        Map<Integer, Subject> subjectCash = dayCash.get(dayName);
        if (!subjectCash.containsKey(subjectId)) {
            throw new NotFoundException();
        }
        return subjectCash.get(subjectId);
    }
}
