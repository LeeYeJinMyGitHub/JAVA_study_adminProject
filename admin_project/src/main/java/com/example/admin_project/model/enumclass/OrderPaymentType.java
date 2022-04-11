package com.example.admin_project.model.enumclass;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum OrderPaymentType {

    Card(0, "카드 결제", "결제 수단 : 카드"),
    Money(1, "현금 결제", "결제 수단 : 현금");

    private Integer id;
    private String title;
    private String description;
}
