package backend.controller;

import backend.dto.FeeRequestDto;
import backend.dto.FeeResponseDto;
import backend.service.FeeService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/fees")
public class FeeController {

    private final FeeService feeService;

    public FeeController(FeeService feeService) {
        this.feeService = feeService;
    }

    @PostMapping
    public FeeResponseDto createFee(
            @RequestBody FeeRequestDto dto) {

        return feeService.createFee(dto);
    }

    @GetMapping
    public List<FeeResponseDto> getAllFees() {
        return feeService.getAllFees();
    }

    @GetMapping("/student/{studentId}")
    public List<FeeResponseDto> getFeesByStudentId(
            @PathVariable Long studentId) {

        return feeService.getFeesByStudentId(studentId);
    }
}