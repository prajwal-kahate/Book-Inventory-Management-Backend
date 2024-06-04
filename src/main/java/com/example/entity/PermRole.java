package com.example.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;

@Entity
@Data
public class PermRole {

	@Id
	private int roleNumber;
	private String permRole;

}
