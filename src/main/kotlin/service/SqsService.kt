package com.report.utils.service

import software.amazon.awssdk.services.sqs.model.Message
import software.amazon.awssdk.services.sqs.model.SendMessageResponse

interface SqsService {

    fun <T> sendMessage(queueUrl: String, message: T): SendMessageResponse

    fun receivedMessage(queueUrl: String, waitTime: Int, maxNumberMessages: Int): List<Message>

    fun deleteMessage(queueUrl: String, message: Message)
}