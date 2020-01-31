package ftc.shift.sample.repositories;

import ftc.shift.sample.models.Subject;

import java.util.Collection;

public interface ISubjects {

    void addAllSubjects(String dayName, Collection<Subject> subjects);

    void addSubject(String dayName, Subject subject);

    Collection<Subject> getAllSubjects(String dayName);

    void clearSubjects(String dayName);

    void changeSubject(String dayName, Subject subject);

    void clearSubject(String dayName, Integer subjectId);

    Subject fetchSubject(String dayName, Integer subjectId);
}
