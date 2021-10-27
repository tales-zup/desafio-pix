package br.com.zup.edu.pix

import javax.persistence.Column
import javax.persistence.Embeddable
import javax.validation.constraints.NotBlank

@Embeddable
class ContaAssociada(
    @field:NotBlank
    @Column(nullable = false)
    var instituicao: String,

    @field:NotBlank
    @Column(nullable = false)
    var nomeDoTitular: String,

    @field:NotBlank
    @Column(nullable = false)
    var cpfDoTitular: String,

    @field:NotBlank
    @Column(nullable = false)
    var agencia: String,

    @field:NotBlank
    @Column(nullable = false)
    var numeroDaConta: String,

) {

    companion object {
        val ITAU_UNIBANCO_ISPB: String = "60701190"
    }

}
