package com.maxmlv.responserthyme.repositories;


import com.maxmlv.responserthyme.models.MediaFile;
import com.maxmlv.responserthyme.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MediaFilesRepository extends JpaRepository<MediaFile, Long> {
    List<MediaFile> findAllByUser(User user);
}
