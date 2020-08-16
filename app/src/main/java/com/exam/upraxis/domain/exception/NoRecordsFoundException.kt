package com.exam.upraxis.domain.exception

/**
 * throws this error for database query without result i.e. get user
 */
class NoRecordsFoundException(message: String = "default error message") : RuntimeException(message)