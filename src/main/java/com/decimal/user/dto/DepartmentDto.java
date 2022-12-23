package com.decimal.user.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
public class DepartmentDto {
    private Long departmentId;
    private String departmentName;
    private String departmentAddress;
    private String departmentCode;
}
