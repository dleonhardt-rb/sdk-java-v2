package com.decidir.sdk.services;

import java.io.IOException;

import com.decidir.sdk.converters.ErrorConverter;
import com.decidir.sdk.converters.PaymentConverter;
import com.decidir.sdk.dto.*;
import com.decidir.sdk.exceptions.DecidirException;
import com.decidir.sdk.payments.GDSPaymentRequestPCI;
import com.decidir.sdk.payments.GDSPaymentResponse;
import com.decidir.sdk.resources.PaymentApi;

import retrofit2.Response;

/**
 * Created by biandra on 06/07/16.
 */
public class PaymentsService {

    public static final int HTTP_500 = 500;
    private PaymentApi paymentApi;
    private PaymentConverter paymentConverter;
    private ErrorConverter errorConverter;

    private PaymentsService(PaymentApi paymentApi, PaymentConverter paymentConverter, ErrorConverter errorConverter){
        this.paymentApi = paymentApi;
        this.paymentConverter = paymentConverter;
        this.errorConverter = errorConverter;
    }

    public static PaymentsService getInstance(PaymentApi paymentApi) {
        return new PaymentsService(paymentApi, new PaymentConverter(), new ErrorConverter());
    }

    public DecidirResponse<Page> getPayments(Integer offset, Integer pageSize, String siteOperationId, String siteId) {
        try {
            Response<Page> response = this.paymentApi.getPayments(offset, pageSize, siteOperationId, siteId).execute();
            if (response.isSuccessful()) {
                return paymentConverter.convert(response, response.body());
            }

            DecidirResponse<DecidirError> error = errorConverter.convert(response);
            throw DecidirException.wrap(error.getStatus(), error.getMessage(), error.getResult());

        } catch(IOException ioe) {
            throw new DecidirException(HTTP_500, ioe.getMessage());
        }
    }

    public DecidirResponse<PaymentResponse> paymentNoPci(PaymentRequest payment) {
        try {
            Response<PaymentResponse> response = this.paymentApi.payNoPci(payment).execute();
            return paymentConverter.convertOrThrowError(response);
        } catch(IOException ioe) {
            throw new DecidirException(HTTP_500, ioe.getMessage());
        }
    }
    public DecidirResponse<PaymentResponse> paymentPciCard(PaymentPciRequest payment) {
        try {
            Response<PaymentResponse> response = this.paymentApi.payPciCard(payment).execute();
            return paymentConverter.convertOrThrowError(response);
        } catch(IOException ioe) {
            throw new DecidirException(HTTP_500, ioe.getMessage());
        }
    }
    public DecidirResponse<PaymentResponse> paymentPciToken(PaymentPciTokenRequest payment) {
        try {
            Response<PaymentResponse> response = this.paymentApi.payPciToken(payment).execute();
            return paymentConverter.convertOrThrowError(response);
        } catch(IOException ioe) {
            throw new DecidirException(HTTP_500, ioe.getMessage());
        }
    }

    public DecidirResponse<PaymentResponse> getPayment(Long paymentId) {
        try {
            Response<PaymentResponse> response = this.paymentApi.getPayment(paymentId).execute();
            return paymentConverter.convertOrThrowError(response);
        } catch(IOException ioe) {
            throw new DecidirException(HTTP_500, ioe.getMessage());
        }
    }

    public DecidirResponse<OfflinePaymentResponse> offlinePayment(OfflinePaymentRequest offlinePayment) {
        try {
            Response<OfflinePaymentResponse> response = this.paymentApi.payOffline(offlinePayment).execute();
            return paymentConverter.convertOrThrowError(response);
        } catch(IOException ioe) {
            throw new DecidirException(HTTP_500, ioe.getMessage());
        }
    }

    public DecidirResponse<GDSPaymentResponse> gdsPaymentNoPci(GDSPaymentRequestNoPCI gdsPayment) {
        try {
            Response<GDSPaymentResponse> response = this.paymentApi.payGdsNoPci(gdsPayment).execute();
            return paymentConverter.convertOrThrowError(response);
        } catch(IOException ioe) {
            throw new DecidirException(HTTP_500, ioe.getMessage());
        }
    }

    public DecidirResponse<GDSPaymentResponse> gdsPaymentPciCard(GDSPaymentRequestPCI gdsPayment) {
        try {
            Response<GDSPaymentResponse> response = this.paymentApi.payGdsPci(gdsPayment).execute();
            return paymentConverter.convertOrThrowError(response);
        } catch(IOException ioe) {
            throw new DecidirException(HTTP_500, ioe.getMessage());
        }
    }

    public DecidirResponse<BSAPaymentResponse> bsaPaymentRequestPCI(BSAPaymentRequestPCI bsaPaymentRequestPCI) {
        try {
            Response<BSAPaymentResponse> response = this.paymentApi.payBsaPci(bsaPaymentRequestPCI).execute();
            return paymentConverter.convertOrThrowError(response);
        } catch(IOException ioe) {
            throw new DecidirException(HTTP_500, ioe.getMessage());
        }
    }


    public DecidirResponse<BSAPaymentResponse> bsaPaymentRequestNoPCI(BSAPaymentRequestNoPCI bsaPaymentRequestNoPCI) {
        try {
            Response<BSAPaymentResponse> response = this.paymentApi.payBsaNoPci(bsaPaymentRequestNoPCI).execute();
            return paymentConverter.convertOrThrowError(response);
        } catch(IOException ioe) {
            throw new DecidirException(HTTP_500, ioe.getMessage());
        }
    }

    public DecidirResponse<AgroPaymentResponse> agroPaymentRequestPCI(AgroPaymentRequestPCI agroPaymentRequestPCI) {
        try {
            Response<AgroPaymentResponse> response = this.paymentApi.payAgroPci(agroPaymentRequestPCI).execute();
            return paymentConverter.convertOrThrowError(response);
        } catch(IOException ioe) {
            throw new DecidirException(HTTP_500, ioe.getMessage());
        }
    }

    public DecidirResponse<AgroPaymentResponse> agroPaymentRequestNoPCI(AgroPaymentRequestNoPCI agroPaymentRequestNoPCI) {
        try {
            Response<AgroPaymentResponse> response = this.paymentApi.payAgroNoPci(agroPaymentRequestNoPCI).execute();
            return paymentConverter.convertOrThrowError(response);
        } catch(IOException ioe) {
            throw new DecidirException(HTTP_500, ioe.getMessage());
        }
    }
}
