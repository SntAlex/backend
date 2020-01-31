package ftc.shift.sample.services;

import ftc.shift.sample.models.Subject;
import ftc.shift.sample.repositories.DatabaseSubjectsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
public class SubjectsService {
    private final DatabaseSubjectsRepository databaseSubjectsRepository;

    @Autowired
    public SubjectsService(DatabaseSubjectsRepository databaseSubjectsRepository) {
        this.databaseSubjectsRepository = databaseSubjectsRepository;
    }

    public void addSubject(String dayName, Subject subject) {
        databaseSubjectsRepository.addSubject(dayName, subject);
    }

    public Collection<Subject> getAllSubjects(String dayName) {
        return databaseSubjectsRepository.getAllSubjects(dayName);
    }

    public void clearSubjects(String dayName) {
        databaseSubjectsRepository.clearSubjects(dayName);
    }

    public void clearSubject(String dayName, Integer id) {
        databaseSubjectsRepository.clearSubject(dayName, id);
    }

    public void changeSubject(String dayName, Subject subject) {
        databaseSubjectsRepository.changeSubject(dayName, subject);
    }

    public Subject fetchSubject(String dayName, Integer subjectId) {
        return databaseSubjectsRepository.fetchSubject(dayName, subjectId);
    }

    public void addAllSubjects(String dayName, Collection<Subject> subjects){
        databaseSubjectsRepository.addAllSubjects(dayName, subjects);
    }
}
