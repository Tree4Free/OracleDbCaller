package com.schuetz.commons.domain.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Transfer {
    long id;
    long fromId;
    long toId;
    long timestamp;
    long amount;
}
