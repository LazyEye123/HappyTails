package com.genuinecoder.springclient.error;

import java.util.List;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class ValidationErrorResponse extends Throwable {

    private List<Violation> violations;

}
