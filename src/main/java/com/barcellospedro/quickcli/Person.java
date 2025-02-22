package com.barcellospedro.quickcli;

import java.math.BigDecimal;
import java.time.LocalDate;

class Person {
    private String title;
    private LocalDate birth;
    private BigDecimal balance;
    private Boolean alive;

    public String gettitle() {
        return title;
    }

    public void settitle(String value) {
        title = value;
    }

    public LocalDate getbirth() {
        return birth;
    }

    public void setbirth(LocalDate value) {
        birth = value;
    }

    public BigDecimal getbalance() {
        return balance;
    }

    public void setbalance(BigDecimal value) {
        balance = value;
    }

    public Boolean getalive() {
        return alive;
    }

    public void setalive(Boolean value) {
        alive = value;
    }

}
