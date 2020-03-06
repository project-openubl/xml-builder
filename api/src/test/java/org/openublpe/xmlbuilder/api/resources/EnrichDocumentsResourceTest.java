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
package org.openublpe.xmlbuilder.api.resources;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.quarkus.test.junit.QuarkusTest;
import io.restassured.response.Response;
import io.restassured.response.ResponseBody;
import org.junit.jupiter.api.Test;
import org.openublpe.xmlbuilder.apicore.resources.ApiApplication;
import org.openublpe.xmlbuilder.core.models.input.standard.despatchadvice.DespatchAdviceInputModel;
import org.openublpe.xmlbuilder.core.models.input.standard.invoice.InvoiceInputModel;
import org.openublpe.xmlbuilder.core.models.input.standard.note.creditNote.CreditNoteInputModel;
import org.openublpe.xmlbuilder.core.models.input.standard.note.debitNote.DebitNoteInputModel;
import org.openublpe.xmlbuilder.core.models.input.sunat.PerceptionInputModel;
import org.openublpe.xmlbuilder.core.models.input.sunat.RetentionInputModel;
import org.openublpe.xmlbuilder.core.models.input.sunat.SummaryDocumentInputModel;
import org.openublpe.xmlbuilder.core.models.input.sunat.VoidedDocumentInputModel;
import org.openublpe.xmlbuilder.core.models.output.standard.despatchadvice.DespatchAdviceOutputModel;
import org.openublpe.xmlbuilder.core.models.output.standard.invoice.InvoiceOutputModel;
import org.openublpe.xmlbuilder.core.models.output.standard.note.creditNote.CreditNoteOutputModel;
import org.openublpe.xmlbuilder.core.models.output.standard.note.debitNote.DebitNoteOutputModel;
import org.openublpe.xmlbuilder.core.models.output.sunat.PerceptionOutputModel;
import org.openublpe.xmlbuilder.core.models.output.sunat.RetentionOutputModel;
import org.openublpe.xmlbuilder.core.models.output.sunat.SummaryDocumentOutputModel;
import org.openublpe.xmlbuilder.core.models.output.sunat.VoidedDocumentOutputModel;
import org.openublpe.xmlbuilder.inputdata.AbstractInputDataTest;
import org.openublpe.xmlbuilder.inputdata.generator.InputGenerator;

import javax.inject.Inject;
import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import java.util.Set;
import java.util.stream.Collectors;

import static io.restassured.RestAssured.given;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

@QuarkusTest
public class EnrichDocumentsResourceTest extends AbstractInputDataTest {

    @Inject
    Validator validator;

    @Test
    void testEnrichInvoice() throws Exception {
        assertEquals(InputGenerator.NUMBER_TEST_INVOICES, INVOICES.size(), "The number of test cases is not the expected one");

        for (InvoiceInputModel input : INVOICES) {
            // GIVEN
            String body = new ObjectMapper().writeValueAsString(input);

            // WHEN
            Response response = given()
                    .body(body)
                    .header("Content-Type", "application/json")
                    .when()
                    .post(ApiApplication.API_BASE + "/documents/invoice/enrich")
                    .thenReturn();

            // THEN
            assertEquals(200, response.getStatusCode(), messageInputDataError(input, response.getBody().asString()));
            ResponseBody responseBody = response.getBody();

            InvoiceOutputModel output = new ObjectMapper().readValue(responseBody.asInputStream(), InvoiceOutputModel.class);
            assertNotNull(output, messageInputDataError(input, "Invalid output"));

            Set<ConstraintViolation<InvoiceOutputModel>> violations = validator.validate(output);
            assertTrue(
                    violations.isEmpty(),
                    messageInputDataError(
                            input,
                            violations.stream()
                                    .map(f -> f.getPropertyPath() + ": " + f.getMessage())
                                    .collect(Collectors.joining(", "))
                    )
            );
        }
    }

    @Test
    void testEnrichCreditNote() throws Exception {
        assertEquals(InputGenerator.NUMBER_TEST_CREDIT_NOTES, CREDIT_NOTES.size(), "The number of test cases is not the expected one");

        for (CreditNoteInputModel input : CREDIT_NOTES) {
            // GIVEN
            String body = new ObjectMapper().writeValueAsString(input);

            // WHEN
            Response response = given()
                    .body(body)
                    .header("Content-Type", "application/json")
                    .when()
                    .post(ApiApplication.API_BASE + "/documents/credit-note/enrich")
                    .thenReturn();

            // THEN
            assertEquals(200, response.getStatusCode(), messageInputDataError(input, response.getBody().asString()));
            ResponseBody responseBody = response.getBody();

            CreditNoteOutputModel output = new ObjectMapper().readValue(responseBody.asInputStream(), CreditNoteOutputModel.class);
            assertNotNull(output, messageInputDataError(input, "Invalid output"));

            Set<ConstraintViolation<CreditNoteOutputModel>> violations = validator.validate(output);
            assertTrue(
                    violations.isEmpty(),
                    messageInputDataError(
                            input,
                            violations.stream()
                                    .map(f -> f.getPropertyPath() + ": " + f.getMessage())
                                    .collect(Collectors.joining(", "))
                    )
            );
        }
    }

    @Test
    void testEnrichDebitNote() throws Exception {
        assertEquals(InputGenerator.NUMBER_TEST_DEBIT_NOTES, DEBIT_NOTES.size(), "The number of test cases is not the expected one");

        for (DebitNoteInputModel input : DEBIT_NOTES) {
            // GIVEN
            String body = new ObjectMapper().writeValueAsString(input);

            // WHEN
            Response response = given()
                    .body(body)
                    .header("Content-Type", "application/json")
                    .when()
                    .post(ApiApplication.API_BASE + "/documents/debit-note/enrich")
                    .thenReturn();

            // THEN
            assertEquals(200, response.getStatusCode(), messageInputDataError(input, response.getBody().asString()));
            ResponseBody responseBody = response.getBody();

            DebitNoteOutputModel output = new ObjectMapper().readValue(responseBody.asInputStream(), DebitNoteOutputModel.class);
            assertNotNull(output, messageInputDataError(input, "Invalid output"));

            Set<ConstraintViolation<DebitNoteOutputModel>> violations = validator.validate(output);
            assertTrue(
                    violations.isEmpty(),
                    messageInputDataError(
                            input,
                            violations.stream()
                                    .map(f -> f.getPropertyPath() + ": " + f.getMessage())
                                    .collect(Collectors.joining(", "))
                    )
            );
        }
    }

    @Test
    void testEnrichVoidedDocument() throws Exception {
        assertEquals(InputGenerator.NUMBER_TEST_VOIDED_DOCUMENTS, VOIDED_DOCUMENTS.size(), "The number of test cases is not the expected one");

        for (VoidedDocumentInputModel input : VOIDED_DOCUMENTS) {
            // GIVEN
            String body = new ObjectMapper().writeValueAsString(input);

            // WHEN
            Response response = given()
                    .body(body)
                    .header("Content-Type", "application/json")
                    .when()
                    .post(ApiApplication.API_BASE + "/documents/voided-document/enrich")
                    .thenReturn();

            // THEN
            assertEquals(200, response.getStatusCode(), messageInputDataError(input, response.getBody().asString()));
            ResponseBody responseBody = response.getBody();

            VoidedDocumentOutputModel output = new ObjectMapper().readValue(responseBody.asInputStream(), VoidedDocumentOutputModel.class);
            assertNotNull(output, messageInputDataError(input, "Invalid output"));

            Set<ConstraintViolation<VoidedDocumentOutputModel>> violations = validator.validate(output);
            assertTrue(
                    violations.isEmpty(),
                    messageInputDataError(
                            input,
                            violations.stream()
                                    .map(f -> f.getPropertyPath() + ": " + f.getMessage())
                                    .collect(Collectors.joining(", "))
                    )
            );
        }
    }

    @Test
    void testEnrichSummaryDocument() throws Exception {
        assertEquals(InputGenerator.NUMBER_TEST_SUMMARY_DOCUMENTS, SUMMARY_DOCUMENTS.size(), "The number of test cases is not the expected one");

        for (SummaryDocumentInputModel input : SUMMARY_DOCUMENTS) {
            // GIVEN
            String body = new ObjectMapper().writeValueAsString(input);

            // WHEN
            Response response = given()
                    .body(body)
                    .header("Content-Type", "application/json")
                    .when()
                    .post(ApiApplication.API_BASE + "/documents/summary-document/enrich")
                    .thenReturn();

            // THEN
            assertEquals(200, response.getStatusCode(), messageInputDataError(input, response.getBody().asString()));
            ResponseBody responseBody = response.getBody();

            SummaryDocumentOutputModel output = new ObjectMapper().readValue(responseBody.asInputStream(), SummaryDocumentOutputModel.class);
            assertNotNull(output, messageInputDataError(input, "Invalid output"));

            Set<ConstraintViolation<SummaryDocumentOutputModel>> violations = validator.validate(output);
            assertTrue(
                    violations.isEmpty(),
                    messageInputDataError(
                            input,
                            violations.stream()
                                    .map(f -> f.getPropertyPath() + ": " + f.getMessage())
                                    .collect(Collectors.joining(", "))
                    )
            );
        }
    }

    @Test
    void testEnrichPerception() throws Exception {
        assertEquals(InputGenerator.NUMBER_TEST_PERCEPTIONS, PERCEPTION_DOCUMENTS.size(), "The number of test cases is not the expected one");

        for (PerceptionInputModel input : PERCEPTION_DOCUMENTS) {
            // GIVEN
            String body = new ObjectMapper().writeValueAsString(input);

            // WHEN
            Response response = given()
                    .body(body)
                    .header("Content-Type", "application/json")
                    .when()
                    .post(ApiApplication.API_BASE + "/documents/perception/enrich")
                    .thenReturn();

            // THEN
            assertEquals(200, response.getStatusCode(), messageInputDataError(input, response.getBody().asString()));
            ResponseBody responseBody = response.getBody();

            PerceptionOutputModel output = new ObjectMapper().readValue(responseBody.asInputStream(), PerceptionOutputModel.class);
            assertNotNull(output, messageInputDataError(input, "Invalid output"));

            Set<ConstraintViolation<PerceptionOutputModel>> violations = validator.validate(output);
            assertTrue(
                    violations.isEmpty(),
                    messageInputDataError(
                            input,
                            violations.stream()
                                    .map(f -> f.getPropertyPath() + ": " + f.getMessage())
                                    .collect(Collectors.joining(", "))
                    )
            );
        }
    }

    @Test
    void testEnrichRetention() throws Exception {
        assertEquals(InputGenerator.NUMBER_TEST_RETENTIONS, RETENTION_DOCUMENTS.size(), "The number of test cases is not the expected one");

        for (RetentionInputModel input : RETENTION_DOCUMENTS) {
            // GIVEN
            String body = new ObjectMapper().writeValueAsString(input);

            // WHEN
            Response response = given()
                    .body(body)
                    .header("Content-Type", "application/json")
                    .when()
                    .post(ApiApplication.API_BASE + "/documents/retention/enrich")
                    .thenReturn();

            // THEN
            assertEquals(200, response.getStatusCode(), messageInputDataError(input, response.getBody().asString()));
            ResponseBody responseBody = response.getBody();

            RetentionOutputModel output = new ObjectMapper().readValue(responseBody.asInputStream(), RetentionOutputModel.class);
            assertNotNull(output, messageInputDataError(input, "Invalid output"));

            Set<ConstraintViolation<RetentionOutputModel>> violations = validator.validate(output);
            assertTrue(
                    violations.isEmpty(),
                    messageInputDataError(
                            input,
                            violations.stream()
                                    .map(f -> f.getPropertyPath() + ": " + f.getMessage())
                                    .collect(Collectors.joining(", "))
                    )
            );
        }
    }

    @Test
    void testEnrichDespatchAdvice() throws Exception {
        assertEquals(InputGenerator.NUMBER_TEST_DESPATCH_ADVICES, DESPATCH_ADVICE_DOCUMENTS.size(), "The number of test cases is not the expected one");

        for (DespatchAdviceInputModel input : DESPATCH_ADVICE_DOCUMENTS) {
            // GIVEN
            String body = new ObjectMapper().writeValueAsString(input);

            // WHEN
            Response response = given()
                    .body(body)
                    .header("Content-Type", "application/json")
                    .when()
                    .post(ApiApplication.API_BASE + "/documents/despatch-advice/enrich")
                    .thenReturn();

            // THEN
            assertEquals(200, response.getStatusCode(), messageInputDataError(input, response.getBody().asString()));
            ResponseBody responseBody = response.getBody();

            DespatchAdviceOutputModel output = new ObjectMapper().readValue(responseBody.asInputStream(), DespatchAdviceOutputModel.class);
            assertNotNull(output, messageInputDataError(input, "Invalid output"));

            Set<ConstraintViolation<DespatchAdviceOutputModel>> violations = validator.validate(output);
            assertTrue(
                    violations.isEmpty(),
                    messageInputDataError(
                            input,
                            violations.stream()
                                    .map(f -> f.getPropertyPath() + ": " + f.getMessage())
                                    .collect(Collectors.joining(", "))
                    )
            );
        }
    }

}
