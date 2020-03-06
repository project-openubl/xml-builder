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
package org.openublpe.xmlbuilder.core.models.input.standard.note;

import org.openublpe.xmlbuilder.core.models.input.standard.DocumentInputModel;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

public abstract class NoteInputModel extends DocumentInputModel {

    @NotBlank
    @Pattern(regexp = "^[F|B].*$")
    private String serieNumeroComprobanteAfectado;

    @NotNull
    @NotBlank
    private String descripcionSustento;

    public String getSerieNumeroComprobanteAfectado() {
        return serieNumeroComprobanteAfectado;
    }

    public void setSerieNumeroComprobanteAfectado(String serieNumeroComprobanteAfectado) {
        this.serieNumeroComprobanteAfectado = serieNumeroComprobanteAfectado;
    }

    public String getDescripcionSustento() {
        return descripcionSustento;
    }

    public void setDescripcionSustento(String descripcionSustento) {
        this.descripcionSustento = descripcionSustento;
    }

}
