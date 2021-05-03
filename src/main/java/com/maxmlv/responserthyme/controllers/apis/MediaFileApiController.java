package com.maxmlv.responserthyme.controllers.apis;

import com.maxmlv.responserthyme.services.MediaFileService;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletResponse;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;

@Controller
@RequestMapping("/api/image")
public class MediaFileApiController {
    @Autowired
    private MediaFileService mediaFileService;

    @GetMapping("/{media_id}")
    public void getImage(@PathVariable("media_id") long media_id, HttpServletResponse response) throws IOException {
        response.setContentType("image/jpeg");

        InputStream inputStream = new ByteArrayInputStream(mediaFileService.findById(media_id).getBlobImg());
        IOUtils.copy(inputStream, response.getOutputStream());
    }
}
