package br.com.zup.edu.shared.grpc.interceptors

import br.com.zup.edu.pix.ChavePixExistenteException
import br.com.zup.edu.pix.registra.RegistraChaveEndpoint
import br.com.zup.edu.shared.grpc.ErrorHandler
import io.grpc.Status
import io.grpc.stub.StreamObserver
import io.micronaut.aop.InterceptorBean
import io.micronaut.aop.MethodInterceptor
import io.micronaut.aop.MethodInvocationContext
import jakarta.inject.Singleton
import javax.validation.ConstraintViolationException

@Singleton
@InterceptorBean(ErrorHandler::class)
class ChavePixExistenteExceptionInterceptor : MethodInterceptor<RegistraChaveEndpoint, Any?> {

    override fun intercept(context: MethodInvocationContext<RegistraChaveEndpoint, Any?>): Any? {

        try {
            return context.proceed()
        } catch (e: Exception) {

            val responseObserver = context.parameterValues[1] as StreamObserver<*>
            var status = Status.INTERNAL

            if(e is ChavePixExistenteException) {
                status = Status.ALREADY_EXISTS
            } else if(e is ConstraintViolationException){
                status = Status.INVALID_ARGUMENT
            }

            return responseObserver.onError(status.withCause(e).withDescription(e.message).asRuntimeException())
        }
    }
}