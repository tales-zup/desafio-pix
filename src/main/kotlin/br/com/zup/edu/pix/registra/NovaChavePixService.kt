package br.com.zup.edu.pix.registra

import br.com.zup.edu.integration.itau.ContasDeClientesNoItauClient
import br.com.zup.edu.pix.ChavePix
import br.com.zup.edu.pix.ChavePixRepository
import io.micronaut.validation.Validated
import jakarta.inject.Inject
import jakarta.inject.Singleton
import javax.transaction.Transactional
import javax.validation.Valid

@Validated
@Singleton
class NovaChavePixService(
    @Inject val chavePixRepository: ChavePixRepository,
    @Inject val itauClient: ContasDeClientesNoItauClient
) {

    @Transactional
    fun registra(@Valid novaChave: NovaChavePix): ChavePix {

        if (chavePixRepository.existsByChave(novaChave.chave)) {
            throw ChavePixExistenteException("Chave Pix '${novaChave.chave}' já existe.")
        }

        val response = itauClient.buscaContaPorTipo(novaChave.clienteId!!, novaChave.tipoDeConta!!.name)
        val conta = response.toModel()

        val chave = novaChave.toModel(conta)
        chavePixRepository.save(chave)

        return chave
    }

}