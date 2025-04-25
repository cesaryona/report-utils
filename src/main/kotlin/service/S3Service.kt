package com.report.utils.service

interface S3Service {

    fun listFilesInBucket(bucket: String): List<String>

    fun uploadFile(bucket: String, key: String, content: ByteArray, contentType: String)

    fun downloadFile(bucket: String, key: String): ByteArray

    fun deleteFile(bucket: String, key: String)
}