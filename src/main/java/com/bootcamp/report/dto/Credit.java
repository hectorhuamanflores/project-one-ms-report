package com.bootcamp.report.dto;

import java.time.LocalDate;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Credit {
   
	private String  id;              // Identificador Credito
    private Integer documentNumber;  // Dni - Ruc
    private String  tyCredito;       // personal - empresarial
    private String  tyCustomer;      // Persona - Empresarial
    private LocalDate     dateStar;        // Fecha de creacion
    private LocalDate     dateEnd;         // Fecha de caducidad
    private String  creditScore;     // bueno - malo - regular
	private Double  lineCredit;      // Linea de credito
	private Double  consumeCredit;   // Monto del consumo de credito total
	private Double  availableCredit; // Monto del credito disponible
}
