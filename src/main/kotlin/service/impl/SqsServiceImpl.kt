package com.report.utils.service.impl

import com.fasterxml.jackson.databind.ObjectMapper
import com.report.utils.service.SqsService
import software.amazon.awssdk.services.sqs.SqsClient
import software.amazon.awssdk.services.sqs.model.Message
import software.amazon.awssdk.services.sqs.model.SendMessageResponse

class SqsServiceImpl(
    private val objectMapper: ObjectMapper,
    private val sqsClient: SqsClient
) : SqsService {

    override fun <T> sendMessage(queueUrl: String, message: T): SendMessageResponse {
        return sqsClient.sendMessage { builder ->
            builder
                .queueUrl(queueUrl)
                .messageBody(objectMapper.writeValueAsString(message))
        }
    }

    override fun receivedMessage(queueUrl: String, waitTime: Int, maxNumberMessages: Int): List<Message> {
        val response = sqsClient.receiveMessage { builder ->
            builder
                .queueUrl(queueUrl)
                .waitTimeSeconds(waitTime)
                .maxNumberOfMessages(maxNumberMessages)
        }

        return response.messages()
    }

    override fun deleteMessage(queueUrl: String, message: Message) {
        try {
            sqsClient.deleteMessage {
                it.queueUrl(queueUrl).receiptHandle(message.receiptHandle())
            }
        } catch (e: Exception) {
            println("Error while deleting the message: ${e.message}")
        }
    }

}