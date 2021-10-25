package br.com.zup.edu.pix.registra

import br.com.zup.edu.KeyManagerRegistraServiceGrpc
import br.com.zup.edu.RegistraChavePixRequest
import br.com.zup.edu.RegistraChavePixResponse
import br.com.zup.edu.shared.grpc.ErrorHandler
import io.grpc.stub.StreamObserver
import jakarta.inject.Inject
import jakarta.inject.Singleton

@Singleton
@ErrorHandler
class RegistraChaveEndpoint(@Inject private val service: NovaChavePixService) :
    KeyManagerRegistraServiceGrpc.KeyManagerRegistraServiceImplBase() {

    override fun registra(
        request: RegistraChavePixRequest,
        responseObserver: StreamObserver<RegistraChavePixResponse>
    ) {

        val novaChave = request.toModel()
        val chaveCriada = service.registra(novaChave)

        responseObserver.onNext(
            RegistraChavePixResponse.newBuilder()
                .setClienteId(chaveCriada.clienteId.toString())
                .setPixId(chaveCriada.id.toString())
                .build()
        )

        responseObserver.onCompleted()
    }

}