package br.com.zup.edu.shared.grpc

import io.micronaut.aop.Around

@Retention(AnnotationRetention.RUNTIME)
@Target(AnnotationTarget.CLASS)
@Around
annotation class ErrorHandler()
