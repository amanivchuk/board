package com.manivchuk.board.persistence.repository;

import com.manivchuk.board.persistence.entity.board.Board;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface BoardRepository extends JpaRepository<Board, Long> {

    @Query("select b from Board b where " +
            "(:title is null or :title='' or lower(b.title) like lower(concat('%', :title, '%')))")
    Page<Board> findByParams(@Param("title") String title, Pageable pageable);

}
