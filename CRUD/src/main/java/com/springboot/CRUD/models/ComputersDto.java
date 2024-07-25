package com.springboot.CRUD.models;

import jakarta.validation.constraints.*;

import java.time.LocalDate;

public class ComputersDto {

    private int id;

    @NotEmpty(message = "Computer Name can not be empty")
    private String computerName;

    @NotEmpty(message = "Serial NUmber can not be empty")
    private String serialNumber;

    private LocalDate date;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public  String getComputerName() {
        return computerName;
    }

    public void setComputerName(String computerName) {
        this.computerName = computerName;
    }

    public  String getSerialNumber() {
        return serialNumber;
    }

    public void setSerialNumber(String serialNumber) {
        this.serialNumber = serialNumber;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = LocalDate.now();
    }


}
