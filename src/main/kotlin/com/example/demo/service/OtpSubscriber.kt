package com.example.demo.service

import org.slf4j.LoggerFactory
import org.springframework.integration.annotation.ServiceActivator
import org.springframework.stereotype.Component
import java.nio.charset.StandardCharsets

@Component
class OtpSubscriber {

    private val logger = LoggerFactory.getLogger(OtpSubscriber::class.java)

    @ServiceActivator(inputChannel = "inputMessageChannel")
    fun messageReceiver(message: org.springframework.messaging.Message<*>) {
        val payload = when (val data = message.payload) {
            is ByteArray -> String(data, StandardCharsets.UTF_8)
            is String -> data
            else -> throw IllegalArgumentException("Unsupported payload type: ${data::class}")
        }

        val (otp, mailId) = payload.split(":")
        logger.info("✅ Received OTP: $otp for email: $mailId")
    }

}