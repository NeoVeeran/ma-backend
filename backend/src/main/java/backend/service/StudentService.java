package backend.service;

import backend.dto.StudentResponseDto;
import backend.entity.Student;
import backend.repository.AttendanceRepository;
import backend.repository.FeeRepository;
import backend.repository.StudentRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class StudentService {

    private final StudentRepository studentRepository;
    private final AttendanceRepository attendanceRepository;
    private final FeeRepository feeRepository;

    public StudentService(
            StudentRepository studentRepository,
            AttendanceRepository attendanceRepository,
            FeeRepository feeRepository) {

        this.studentRepository = studentRepository;
        this.attendanceRepository = attendanceRepository;
        this.feeRepository = feeRepository;
    }

    public List<Student> getAllStudents() {
        return studentRepository.findAll();
    }

    public Student saveStudent(Student student) {

        if (studentRepository.existsByEmail(student.getEmail())) {
            throw new ResponseStatusException(
                    HttpStatus.CONFLICT,
                    "Email already exists"
            );
        }

        return studentRepository.save(student);
    }

    public StudentResponseDto getStudentById(Long id) {

        Student student = studentRepository.findById(id)
                .orElseThrow(() ->
                        new ResponseStatusException(
                                HttpStatus.NOT_FOUND,
                                "Student not found"));

        return convertToDto(student);
    }

    @Transactional
    public void deleteStudent(Long id) {

        attendanceRepository.deleteByStudentId(id);
        feeRepository.deleteByStudentId(id);
        studentRepository.deleteById(id);
    }

    public Student updateStudent(Long id, Student updatedStudent) {

        Student student = studentRepository.findById(id)
                .orElseThrow(() ->
                        new ResponseStatusException(
                                HttpStatus.NOT_FOUND,
                                "Student not found"
                        ));

        if (studentRepository.existsByEmailAndIdNot(updatedStudent.getEmail(), id)) {
            throw new ResponseStatusException(
                    HttpStatus.CONFLICT,
                    "Email already exists"
            );
        }

        student.setName(updatedStudent.getName());
        student.setEmail(updatedStudent.getEmail());
        student.setPhone(updatedStudent.getPhone());
        student.setBeltRank(updatedStudent.getBeltRank());

        return studentRepository.save(student);
    }

    private StudentResponseDto convertToDto(Student student) {

        StudentResponseDto dto = new StudentResponseDto();

        dto.setId(student.getId());
        dto.setName(student.getName());
        dto.setEmail(student.getEmail());
        dto.setPhone(student.getPhone());
        dto.setBeltRank(student.getBeltRank());

        return dto;
    }
}