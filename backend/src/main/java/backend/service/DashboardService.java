package backend.service;

import backend.dto.DashboardDto;
import backend.repository.AttendanceRepository;
import backend.repository.FeeRepository;
import backend.repository.StudentRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class DashboardService {

    private final StudentRepository studentRepository;
    private final AttendanceRepository attendanceRepository;
    private final FeeRepository feeRepository;

    public DashboardService(
            StudentRepository studentRepository,
            AttendanceRepository attendanceRepository,
            FeeRepository feeRepository) {

        this.studentRepository = studentRepository;
        this.attendanceRepository = attendanceRepository;
        this.feeRepository = feeRepository;
    }

    public DashboardDto getDashboard() {

        DashboardDto dto = new DashboardDto();

        LocalDate today = LocalDate.now();

        dto.setTotalStudents(studentRepository.count());

        dto.setPresentToday(
                attendanceRepository.countByAttendanceDateAndPresent(
                        today,
                        true
                ));

        dto.setAbsentToday(
                attendanceRepository.countByAttendanceDateAndPresent(
                        today,
                        false
                ));

        dto.setFeesCollected(feeRepository.getCollectedFees());

        dto.setFeesPending(feeRepository.getPendingFees());

        return dto;
    }
}