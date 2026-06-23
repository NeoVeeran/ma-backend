package backend.controller;

import backend.dto.AttendanceResponseDto;
import backend.entity.Attendance;
import backend.service.AttendanceService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/attendance")
public class AttendanceController {

    private final AttendanceService attendanceService;

    public AttendanceController(AttendanceService attendanceService) {
        this.attendanceService = attendanceService;
    }

    @PostMapping
    public Attendance markAttendance(
            @RequestBody AttendanceResponseDto dto) {

        return attendanceService.markAttendance(dto);
    }


    @GetMapping
    public List<Attendance> getAttendance() {
        return attendanceService.getAllAttendance();
    }

    @GetMapping("/student/{studentId}")
    public List<AttendanceResponseDto> getAttendanceByStudentId(
            @PathVariable Long studentId) {

        return attendanceService
                .getAttendanceByStudentId(studentId);
    }
}