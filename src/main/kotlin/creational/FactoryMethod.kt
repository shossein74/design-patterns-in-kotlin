package com.hossein.dev.creational

import java.util.*

interface PaymentGateway {
    fun process(amount: Double)
}

class PaypalGateway: PaymentGateway {
    override fun process(amount: Double) {
        println("processing ${Utils.formattedPrice(amount)} with paypal")
    }
}

class GooglePayGateway: PaymentGateway {
    override fun process(amount: Double) {
        println("processing ${Utils.formattedPrice(amount)} with google pay")
    }
}

class ApplePayGateway: PaymentGateway {
    override fun process(amount: Double) {
        println("processing ${Utils.formattedPrice(amount)} with apple")
    }
}

abstract class PaymentFactory {
    abstract fun createPaymentGateway(): PaymentGateway
}

class PaypalFactory: PaymentFactory() {
    override fun createPaymentGateway(): PaymentGateway {
        return PaypalGateway()
    }
}

class GooglePayFactory: PaymentFactory() {
    override fun createPaymentGateway(): PaymentGateway {
        return GooglePayGateway()
    }
}

class ApplePayFactory: PaymentFactory() {
    override fun createPaymentGateway(): PaymentGateway {
        return ApplePayGateway()
    }
}

//region CUSTOM_TOOLS
object Utils {
    fun formattedPrice(amount: Double): String {
        return String.format(Locale.US, "$%.2f", amount)
    }
}

fun getSelectedPaymentMethodByUser(): String {
    return "GooglePay"
}

object PaymentFactoryProvider {
    fun getPaymentFactory(paymentMethodName: String): PaymentFactory {
        return when (paymentMethodName) {
            "Paypal" -> PaypalFactory()
            "GooglePay" -> GooglePayFactory()
            "ApplePay" -> ApplePayFactory()
            else -> throw IllegalArgumentException("Unsupported payment method: $paymentMethodName")
        }
    }
}
//endregion

fun main() {
    val selectedMethod = getSelectedPaymentMethodByUser()
    val paymentFactory = PaymentFactoryProvider.getPaymentFactory(selectedMethod)

    val paymentGateway = paymentFactory.createPaymentGateway()
    paymentGateway.process(12000000.78)
}