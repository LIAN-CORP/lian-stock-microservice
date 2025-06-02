package com.lian.marketing.lianstockmicroservice.domain.model;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.http.MediaType;

import java.util.Arrays;

@Getter
@AllArgsConstructor
public enum FileType {

    JPG("jpg", MediaType.IMAGE_JPEG),
    JPEG("jpeg", MediaType.IMAGE_JPEG),
    PNG("png", MediaType.IMAGE_PNG);

    private final String extension;
    private final MediaType mediaType;

    public static MediaType fromFileName(String fileName) {
        int dotIndex = fileName.lastIndexOf(".");
        String fileExtension = (dotIndex == -1) ? "" : fileName.substring(dotIndex + 1);
        return Arrays.stream(values())
                .filter(e -> e.getExtension().equals(fileExtension))
                .findFirst()
                .map(FileType::getMediaType)
                .orElse(MediaType.APPLICATION_OCTET_STREAM);
    }

}
