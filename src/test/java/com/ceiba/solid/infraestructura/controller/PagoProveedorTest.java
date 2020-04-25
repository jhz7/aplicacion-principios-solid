package com.ceiba.solid.infraestructura.controller;

import com.ceiba.solid.infraestructura.entidad.PagoEntity;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.web.servlet.MockMvc;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
class PagoProveedorTest {

    @Autowired
    private MockMvc mvc;

    @PersistenceContext
    private EntityManager entityManager;

    @Test
    void generarPagosAProveedoresTest() throws Exception {
        mvc.perform(post("/provider-payment")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"idProveedor\":1,\"valor\":500000}"))
                .andExpect(status().isOk());

        PagoEntity pago = entityManager.createQuery("SELECT p FROM Pago p", PagoEntity.class).getResultList().get(0);

        SimpleDateFormat formatoFecha = new SimpleDateFormat("dd-MM-yyyy");

        Assert.assertThat(pago.getIdProveedor(), is(1l));
        Assert.assertThat(pago.getValor(), is(500000.0));
        Assert.assertThat(
                formatoFecha.format(pago.getFechaDesembolso()),
                is((formatoFecha.format(Date.from(obtenerFechaDesembolsoProveedores().atStartOfDay(ZoneId.systemDefault()).toInstant())))));
    }

    private LocalDate obtenerFechaDesembolsoProveedores() {
        LocalDate localDate = LocalDate.now();
        localDate = localDate.withDayOfMonth(1).withMonth(localDate.getMonthValue() + 1);
        return localDate;
    }
}
