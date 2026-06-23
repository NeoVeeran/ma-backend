package backend.service;

import backend.dto.AttendanceResponseDto;
import backend.entity.Attendance;
import backend.entity.Student;
import backend.repository.AttendanceRepository;
import backend.repository.StudentRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class AttendanceService {

    private final AttendanceRepository attendanceRepository;
    private final StudentRepository studentRepository;

    public AttendanceService(
            AttendanceRepository attendanceRepository,
            StudentRepository studentRepository) {

        this.attendanceRepository = attendanceRepository;
        this.studentRepository = studentRepository;
    }

    public Attendance markAttendance(AttendanceResponseDto dto) {

        Student student = studentRepository.findById(dto.getStudentId())
                .orElseThrow(() -> new RuntimeException("Student not found"));

        Attendance attendance = new Attendance();

        attendance.setStudent(student);
        attendance.setPresent(dto.getPresent());
        attendance.setAttendanceDate(LocalDate.now());

        return attendanceRepository.save(attendance);
    }

    public List<Attendance> getAllAttendance() {
        return attendanceRepository.findAll();
    }

    public List<AttendanceResponseDto> getAttendanceByStudentId(
            Long studentId) {

        return attendanceRepository.findByStudentId(studentId)
                .stream()
                .map(this::convertToDto)
                .toList();
    }

    private AttendanceResponseDto convertToDto(
            Attendance attendance) {

        AttendanceResponseDto dto = new AttendanceResponseDto();

        dto.setId(attendance.getId());
        dto.setStudentId(attendance.getStudent().getId());
        dto.setStudentName(attendance.getStudent().getName());
        dto.setAttendanceDate(attendance.getAttendanceDate());
        dto.setPresent(attendance.getPresent());

        return dto;
    }
}