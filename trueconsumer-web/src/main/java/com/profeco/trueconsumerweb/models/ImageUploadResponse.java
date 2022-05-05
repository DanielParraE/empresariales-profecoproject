package com.profeco.trueconsumerweb.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ImageUploadResponse implements Serializable {
    private String  fileName;
    private String downloadUri;
    private String size;
}
