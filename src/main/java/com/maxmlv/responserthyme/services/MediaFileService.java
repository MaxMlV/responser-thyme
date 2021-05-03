package com.maxmlv.responserthyme.services;

import com.maxmlv.responserthyme.models.MediaFile;
import com.maxmlv.responserthyme.models.Post;
import com.maxmlv.responserthyme.models.User;
import com.maxmlv.responserthyme.repositories.MediaFilesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Service
public class MediaFileService {
    @Autowired
    private MediaFilesRepository mediaFilesRepository;

    public MediaFile findById(long media_id) {
        return mediaFilesRepository.findById(media_id);
    }

    public MediaFile addMediaFile(User user, Post post, MultipartFile file) throws IOException {
        MediaFile mediaFile = new MediaFile(user, post, file.getBytes());
        return mediaFilesRepository.save(mediaFile);
    }

    @Transactional
    public void deleteByPost(Post post) {
        mediaFilesRepository.deleteByPost(post);
    }
}
