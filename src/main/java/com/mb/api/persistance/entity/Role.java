package com.mb.api.persistance.entity;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import com.mb.api.constants.ERole;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "roles")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Role
{
	@Id
	@GeneratedValue(strategy =  GenerationType.IDENTITY)
	private Integer id;
	
	@Enumerated(EnumType.STRING)
	private ERole roleName;
}
