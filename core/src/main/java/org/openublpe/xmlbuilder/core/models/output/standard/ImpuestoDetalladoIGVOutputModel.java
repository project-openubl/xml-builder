/**
 * Copyright 2019 Project OpenUBL, Inc. and/or its affiliates
 * and other contributors as indicated by the @author tags.
 *
 * Licensed under the Eclipse Public License - v 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.openublpe.xmlbuilder.core.models.output.standard;

import org.openublpe.xmlbuilder.core.models.catalogs.Catalog5;
import org.openublpe.xmlbuilder.core.models.catalogs.Catalog7;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

public class ImpuestoDetalladoIGVOutputModel extends ImpuestoDetalladoOutputModel {

    @NotNull
    private Catalog7 tipo;

    public Catalog7 getTipo() {
        return tipo;
    }

    public void setTipo(Catalog7 tipo) {
        this.tipo = tipo;
    }

    public static final class Builder {
        private Catalog7 tipo;
        private BigDecimal baseImponible;
        private BigDecimal porcentaje;
        private BigDecimal importe;
        private Catalog5 categoria;

        private Builder() {
        }

        public static Builder anImpuestoDetalladoIGVOutputModel() {
            return new Builder();
        }

        public Builder withTipo(Catalog7 tipo) {
            this.tipo = tipo;
            return this;
        }

        public Builder withBaseImponible(BigDecimal baseImponible) {
            this.baseImponible = baseImponible;
            return this;
        }

        public Builder withPorcentaje(BigDecimal porcentaje) {
            this.porcentaje = porcentaje;
            return this;
        }

        public Builder withImporte(BigDecimal importe) {
            this.importe = importe;
            return this;
        }

        public Builder withCategoria(Catalog5 categoria) {
            this.categoria = categoria;
            return this;
        }

        public ImpuestoDetalladoIGVOutputModel build() {
            ImpuestoDetalladoIGVOutputModel impuestoDetalladoIGVOutputModel = new ImpuestoDetalladoIGVOutputModel();
            impuestoDetalladoIGVOutputModel.setTipo(tipo);
            impuestoDetalladoIGVOutputModel.setBaseImponible(baseImponible);
            impuestoDetalladoIGVOutputModel.setPorcentaje(porcentaje);
            impuestoDetalladoIGVOutputModel.setImporte(importe);
            impuestoDetalladoIGVOutputModel.setCategoria(categoria);
            return impuestoDetalladoIGVOutputModel;
        }
    }
}
