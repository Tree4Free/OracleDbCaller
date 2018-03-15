package com.schuetz.commons.domain.dto.responses;

import com.schuetz.commons.domain.entity.AccountHistory;
import com.schuetz.commons.domain.entity.Transfer;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class BalanceHistoryResponse {
    List<Transfer> history;
}
