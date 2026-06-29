package backend.repository;

import backend.entity.Fee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface FeeRepository
        extends JpaRepository<Fee, Long> {

    List<Fee> findByStudentId(Long studentId);

    @Query("SELECT COALESCE(SUM(f.amount),0) FROM Fee f WHERE f.paid=true")
    Double getCollectedFees();

    @Query("SELECT COALESCE(SUM(f.amount),0) FROM Fee f WHERE f.paid=false")
    Double getPendingFees();

    @Modifying
    @Transactional
    @Query("DELETE FROM Fee f WHERE f.student.id = :studentId")
    void deleteByStudentId(@Param("studentId") Long studentId);
}