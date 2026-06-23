package backend.service;

import backend.dto.FeeRequestDto;
import backend.dto.FeeResponseDto;
import backend.entity.Fee;
import backend.entity.Student;
import backend.repository.FeeRepository;
import backend.repository.StudentRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class FeeService {

    private final FeeRepository feeRepository;
    private final StudentRepository studentRepository;

    public FeeService(
            FeeRepository feeRepository,
            StudentRepository studentRepository) {

        this.feeRepository = feeRepository;
        this.studentRepository = studentRepository;
    }

    public FeeResponseDto createFee(FeeRequestDto dto) {

        Student student = studentRepository.findById(dto.getStudentId())
                .orElseThrow(() -> new RuntimeException("Student not found"));

        Fee fee = new Fee();

        fee.setStudent(student);
        fee.setAmount(dto.getAmount());
        fee.setPaid(dto.getPaid());
        fee.setPaymentDate(LocalDate.now());

        Fee savedFee = feeRepository.save(fee);

        return convertToDto(savedFee);
    }

    public List<FeeResponseDto> getAllFees() {

        return feeRepository.findAll()
                .stream()
                .map(this::convertToDto)
                .toList();
    }

    public List<FeeResponseDto> getFeesByStudentId(Long studentId) {

        return feeRepository.findByStudentId(studentId)
                .stream()
                .map(this::convertToDto)
                .toList();
    }

    private FeeResponseDto convertToDto(Fee fee) {

        FeeResponseDto dto = new FeeResponseDto();

        dto.setId(fee.getId());
        dto.setStudentId(fee.getStudent().getId());
        dto.setStudentName(fee.getStudent().getName());
        dto.setAmount(fee.getAmount());
        dto.setPaid(fee.getPaid());
        dto.setPaymentDate(fee.getPaymentDate());

        return dto;
    }
}