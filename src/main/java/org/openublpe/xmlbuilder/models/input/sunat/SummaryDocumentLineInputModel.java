package org.openublpe.xmlbuilder.models.input.sunat;

import org.openublpe.xmlbuilder.models.input.common.ClienteInputModel;
import org.openublpe.xmlbuilder.models.input.common.FirmanteInputModel;
import org.openublpe.xmlbuilder.models.output.sunat.ImpuestoTotalResumenDiarioOutputModel;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.List;

public class SummaryDocumentLineInputModel {

    @NotBlank
    private String serieNumero;

    @NotBlank
    private String tipoOperacion;

    @NotNull
    private String tipoComprobante;

    @Valid
    @NotNull
    private ClienteInputModel cliente;

    @Valid
    private FirmanteInputModel firmante;

    @NotNull
    @Min(0)
    private BigDecimal importeTotal;

    @Min(0)
    private BigDecimal totalOtrosCargos;

    @Min(0)
    private BigDecimal totalOperacionesGravadas;

    @Min(0)
    private BigDecimal totalOperacionesExoneradas;

    @Min(0)
    private BigDecimal totalOperacionesInafectas;

    @Min(0)
    private BigDecimal totalOperacionesGratuitas;

    @NotNull
    @Min(0)
    private BigDecimal igv;

    @Min(0)
    private BigDecimal icb;

    public String getSerieNumero() {
        return serieNumero;
    }

    public void setSerieNumero(String serieNumero) {
        this.serieNumero = serieNumero;
    }

    public String getTipoOperacion() {
        return tipoOperacion;
    }

    public void setTipoOperacion(String tipoOperacion) {
        this.tipoOperacion = tipoOperacion;
    }

    public String getTipoComprobante() {
        return tipoComprobante;
    }

    public void setTipoComprobante(String tipoComprobante) {
        this.tipoComprobante = tipoComprobante;
    }

    public ClienteInputModel getCliente() {
        return cliente;
    }

    public void setCliente(ClienteInputModel cliente) {
        this.cliente = cliente;
    }

    public FirmanteInputModel getFirmante() {
        return firmante;
    }

    public void setFirmante(FirmanteInputModel firmante) {
        this.firmante = firmante;
    }

    public BigDecimal getImporteTotal() {
        return importeTotal;
    }

    public void setImporteTotal(BigDecimal importeTotal) {
        this.importeTotal = importeTotal;
    }

    public BigDecimal getTotalOtrosCargos() {
        return totalOtrosCargos;
    }

    public void setTotalOtrosCargos(BigDecimal totalOtrosCargos) {
        this.totalOtrosCargos = totalOtrosCargos;
    }

    public BigDecimal getTotalOperacionesGravadas() {
        return totalOperacionesGravadas;
    }

    public void setTotalOperacionesGravadas(BigDecimal totalOperacionesGravadas) {
        this.totalOperacionesGravadas = totalOperacionesGravadas;
    }

    public BigDecimal getTotalOperacionesExoneradas() {
        return totalOperacionesExoneradas;
    }

    public void setTotalOperacionesExoneradas(BigDecimal totalOperacionesExoneradas) {
        this.totalOperacionesExoneradas = totalOperacionesExoneradas;
    }

    public BigDecimal getTotalOperacionesInafectas() {
        return totalOperacionesInafectas;
    }

    public void setTotalOperacionesInafectas(BigDecimal totalOperacionesInafectas) {
        this.totalOperacionesInafectas = totalOperacionesInafectas;
    }

    public BigDecimal getTotalOperacionesGratuitas() {
        return totalOperacionesGratuitas;
    }

    public void setTotalOperacionesGratuitas(BigDecimal totalOperacionesGratuitas) {
        this.totalOperacionesGratuitas = totalOperacionesGratuitas;
    }

    public BigDecimal getIgv() {
        return igv;
    }

    public void setIgv(BigDecimal igv) {
        this.igv = igv;
    }

    public BigDecimal getIcb() {
        return icb;
    }

    public void setIcb(BigDecimal icb) {
        this.icb = icb;
    }

}
