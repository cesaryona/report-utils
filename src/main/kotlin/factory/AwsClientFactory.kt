package com.report.utils.factory

import software.amazon.awssdk.auth.credentials.AwsBasicCredentials
import software.amazon.awssdk.auth.credentials.StaticCredentialsProvider
import software.amazon.awssdk.regions.Region
import software.amazon.awssdk.services.s3.S3Client
import software.amazon.awssdk.services.s3.S3Configuration
import software.amazon.awssdk.services.sqs.SqsClient
import java.net.URI

class AwsClientFactory(
    accessKey: String,
    secretKey: String,
    region: String,
    endpointUrl: String
) {

    private val credentialsProvider = StaticCredentialsProvider.create(
        AwsBasicCredentials.create(accessKey, secretKey)
    )

    private val uri = URI.create(endpointUrl)
    private val regionObj = Region.of(region)

    fun createSqsClient(): SqsClient {
        return SqsClient.builder()
            .endpointOverride(uri)
            .credentialsProvider(credentialsProvider)
            .region(regionObj)
            .build()
    }

    fun createS3Client(): S3Client {
        return S3Client.builder()
            .endpointOverride(uri)
            .credentialsProvider(credentialsProvider)
            .region(regionObj)
            .serviceConfiguration(S3Configuration.builder().pathStyleAccessEnabled(true).build())
            .build()
    }
}
