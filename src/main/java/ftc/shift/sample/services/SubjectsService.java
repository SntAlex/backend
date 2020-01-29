package ftc.shift.sample.services;

import ftc.shift.sample.models.Subject;
import ftc.shift.sample.repositories.SubjectsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
public class SubjectsService {
    private final SubjectsRepository subjectsRepository;

    @Autowired
    public SubjectsService(SubjectsRepository subjectsRepository) {
        this.subjectsRepository = subjectsRepository;
    }

    public void addSubject(String dayName, Subject subject) {
        subjectsRepository.addSubject(dayName,subject);
    }

    public Collection<Subject> getAllSubjects(String dayName){
        return subjectsRepository.getAllSubjects(dayName);
    }

    public void clearSubjects(String dayName) {
        subjectsRepository.clearSubjects(dayName);
    }

    public void clearSubject(String dayName, Integer id) {
        subjectsRepository.clearSubject(dayName, id);
    }

    public void changeSubject(String dayName, Subject subject) {
        subjectsRepository.changeSubject(dayName, subject);
    }

    public Subject fetchSubject(String dayName, Integer subjectId) {
        return subjectsRepository.fetchSubject(dayName, subjectId);
    }
}
