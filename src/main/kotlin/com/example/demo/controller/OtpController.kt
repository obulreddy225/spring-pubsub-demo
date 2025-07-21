package com.example.demo.controller

import com.example.demo.dto.OtpRequest
import com.example.demo.service.OtpPublisher
import com.example.demo.service.OtpSubscriber
import org.slf4j.LoggerFactory
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/otp")
class OtpController(

    private val otpPublisher: OtpPublisher
) {
    private val logger = LoggerFactory.getLogger(OtpController::class.java)

    @PostMapping("/publish")
    fun publishOtp(@RequestBody request: OtpRequest): ResponseEntity<String> {
        logger.info("request coming from postman is: {}",request)
        otpPublisher.publishOtp(request.otp, request.mailId)
        return ResponseEntity.ok("OTP published to Pub/Sub!")
    }
}