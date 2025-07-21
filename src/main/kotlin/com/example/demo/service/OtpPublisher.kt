package com.example.demo.service


import com.google.cloud.spring.pubsub.core.PubSubTemplate
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Service

@Service
class OtpPublisher(
    private val pubSubTemplate: PubSubTemplate,
    @Value("\${pubsub.topic.name}") private val topicName: String
) {
    private val logger = LoggerFactory.getLogger(OtpPublisher::class.java)
    fun publishOtp(otp: String, mailId: String) {
        logger.info("otp is: {}",otp)
        logger.info("mailId is: {}",mailId)
        val message = "$otp:$mailId"
        pubSubTemplate.publish(topicName, message)
    }
}