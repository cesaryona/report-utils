package com.report.utils.config

import com.report.utils.config.properties.AwsProperties
import software.amazon.awssdk.auth.credentials.AwsBasicCredentials
import software.amazon.awssdk.auth.credentials.StaticCredentialsProvider
import software.amazon.awssdk.regions.Region
import software.amazon.awssdk.services.s3.S3Client
import software.amazon.awssdk.services.s3.S3Configuration
import software.amazon.awssdk.services.sqs.SqsClient
import java.net.URI

class AwsClientFactory(private val properties: AwsProperties) {

    private val credentialsProvider = StaticCredentialsProvider.create(
        AwsBasicCredentials.create(
            properties.credentials.accessKey,
            properties.credentials.secretKey
        )
    )

    private fun getURI() : URI = URI.create(properties.endpoint.url)

    fun createSqsClient(): SqsClient {
        return SqsClient.builder()
            .endpointOverride(getURI())
            .credentialsProvider(credentialsProvider)
            .region(Region.of(properties.region))
            .build()
    }

    fun createS3Client(): S3Client {
        return S3Client.builder()
            .endpointOverride(getURI())
            .credentialsProvider(credentialsProvider)
            .region(Region.of(properties.region))
            .serviceConfiguration(S3Configuration.builder().pathStyleAccessEnabled(true).build())
            .build()
    }
}
