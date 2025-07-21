package com.example.demo.service


import com.google.cloud.spring.pubsub.core.PubSubTemplate
import com.google.cloud.spring.pubsub.integration.inbound.PubSubInboundChannelAdapter
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.integration.channel.DirectChannel
import org.springframework.messaging.MessageChannel


@Configuration
class PubSubSubscriberConfig(
    private val pubSubTemplate: PubSubTemplate
) {
    @Bean
    fun inputMessageChannel(): MessageChannel = DirectChannel()

    @Bean
    fun messageChannelAdapter(
        inputMessageChannel: MessageChannel,
        @Value("\${pubsub.subscription.name}") subscriptionName: String
    ): PubSubInboundChannelAdapter {
        val adapter = PubSubInboundChannelAdapter(pubSubTemplate, subscriptionName)
        adapter.outputChannel = inputMessageChannel
        return adapter
    }
}
