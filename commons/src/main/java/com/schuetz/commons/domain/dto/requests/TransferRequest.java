package com.schuetz.commons.domain.dto.requests;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TransferRequest {
    private long fromId;
    private long toId;
    private long amount;
}
