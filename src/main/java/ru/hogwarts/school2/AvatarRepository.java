package ru.hogwarts.school2;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;
import java.util.Optional;

public interface AvatarRepository extends PagingAndSortingRepository<Avatar, Long> {

    List<Avatar> findAll();
    Optional<Avatar> findByStudentId(Long studentId);
}
