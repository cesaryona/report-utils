package com.report.utils.config.properties

data class AwsCredentials(
    val accessKey: String,
    val secretKey: String
)

data class AwsEndpoint(
    val url: String
)

data class AwsProperties(
    val credentials: AwsCredentials,
    val region: String,
    val endpoint: AwsEndpoint
)