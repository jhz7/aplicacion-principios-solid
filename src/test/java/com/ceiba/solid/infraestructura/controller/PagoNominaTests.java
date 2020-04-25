package com.ceiba.solid.infraestructura.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.web.servlet.MockMvc;

import java.text.SimpleDateFormat;
import java.util.Calendar;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
class PagoNominaTests {

	@Autowired
	private MockMvc mvc;

	@Test
	void generarPagosASalariosTest() throws Exception {

		Calendar fechaDesembolsoGerente = Calendar.getInstance();
		SimpleDateFormat formatoFecha = new SimpleDateFormat("dd-MM-yyyy");

		Calendar fechaDesembolsoOPerario = Calendar.getInstance();
		fechaDesembolsoOPerario.add(Calendar.DATE, 5);

		Calendar fechaDesembolsoSupervisor = Calendar.getInstance();
		fechaDesembolsoSupervisor.add(Calendar.DATE, 3);

		mvc.perform(get("/payroll/apply-salary")
				.contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$", hasSize(3)))
				//Validación gerente
				.andExpect(jsonPath("$[0].id", is(1)))
				.andExpect(jsonPath("$[0].valor", is(6500000.0)))
				.andExpect(jsonPath("$[0].bonificacion", is(1500000.0)))
				.andExpect(jsonPath("$[0].fechaDesembolso", is(formatoFecha.format(fechaDesembolsoGerente.getTime()))))
				.andExpect(jsonPath("$[0].idEmpleado", is(1)))
				//Validación operario
				.andExpect(jsonPath("$[1].id", is(2)))
				.andExpect(jsonPath("$[1].valor", is(1017662.8)))
				.andExpect(jsonPath("$[1].bonificacion", is(92514.8)))
				.andExpect(jsonPath("$[1].fechaDesembolso", is(formatoFecha.format(fechaDesembolsoOPerario.getTime()))))
				.andExpect(jsonPath("$[1].idEmpleado", is(2)))
				//Validación supervisor
				.andExpect(jsonPath("$[2].id", is(3)))
				.andExpect(jsonPath("$[2].valor", is(1821855.2)))
				.andExpect(jsonPath("$[2].bonificacion", is(165623.2)))
				.andExpect(jsonPath("$[2].fechaDesembolso", is(formatoFecha.format(fechaDesembolsoSupervisor.getTime()))))
				.andExpect(jsonPath("$[2].idEmpleado", is(3)))
		;
	}
}