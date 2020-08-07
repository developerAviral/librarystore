package com.genpact.librarystore.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.genpact.librarystore.entity.Library;

@Repository
public interface LibraryRepository extends JpaRepository<Library, Long> {

}
