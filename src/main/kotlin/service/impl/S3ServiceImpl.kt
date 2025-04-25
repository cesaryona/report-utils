package com.report.utils.service.impl

import com.report.utils.service.S3Service
import software.amazon.awssdk.core.sync.RequestBody
import software.amazon.awssdk.services.s3.S3Client
import software.amazon.awssdk.services.s3.model.ListObjectsV2Request
import software.amazon.awssdk.services.s3.model.PutObjectRequest
import software.amazon.awssdk.services.s3.model.S3Exception

class S3ServiceImpl(
    private val s3Client: S3Client
) : S3Service {

    override fun listFilesInBucket(bucket: String): List<String> {
        val listObjectsRequest = ListObjectsV2Request.builder()
            .bucket(bucket)
            .build()

        val listObjectsResponse = s3Client.listObjectsV2(listObjectsRequest)
        return listObjectsResponse.contents().map { it.key() }
    }

    override fun uploadFile(bucket: String, key: String, content: ByteArray, contentType: String) {
        createBucketIfNotExists(bucket)

        val request = PutObjectRequest.builder()
            .bucket(bucket)
            .key(key)
            .contentType(contentType)
            .build()

        s3Client.putObject(request, RequestBody.fromBytes(content))
    }

    override fun downloadFile(bucket: String, key: String): ByteArray {
        return s3Client.getObject { it.bucket(bucket).key(key) }.readAllBytes()
    }

    override fun deleteFile(bucket: String, key: String) {
        s3Client.deleteObject { it.bucket(bucket).key(key) }
    }

    private fun createBucketIfNotExists(bucket: String) {
        if (!bucketExists(bucket)) {
            s3Client.createBucket { it.bucket(bucket) }
        }
    }

    private fun bucketExists(bucket: String): Boolean {
        return try {
            s3Client.headBucket { it.bucket(bucket) }
            true
        } catch (e: S3Exception) {
            e.statusCode() != 404
        }
    }

}