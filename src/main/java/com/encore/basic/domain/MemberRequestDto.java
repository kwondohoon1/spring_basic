package com.encore.basic.domain;

import lombok.Data;
import lombok.Getter;

import java.time.LocalDateTime;
@Data
public class MemberRequestDto {
        private String name;
        private String email;
        private String password;
    }
