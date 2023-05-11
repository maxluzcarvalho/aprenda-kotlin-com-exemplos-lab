enum class Nivel { BASICO, INTERMEDIARIO, AVANCADO }

class Usuario(val nome: String)

data class ConteudoEducacional(val nome: String, val duracao: Int = 60)

interface Matriculavel {
    fun matricular(usuario: Usuario)
}

data class Formacao(val nome: String, var conteudos: MutableList<ConteudoEducacional>) : Matriculavel {

    val inscritos = mutableListOf<Usuario>()

    override fun matricular(usuario: Usuario) {
        inscritos.add(usuario)
        println("Usuário ${usuario.nome} matriculado na formação $nome.")
    }
}

open class Curso(val nome: String, val nivel: Nivel, val duracao: Int) : Matriculavel {

    val inscritos = mutableListOf<Usuario>()

    override fun matricular(usuario: Usuario) {
        inscritos.add(usuario)
        println("Usuário ${usuario.nome} matriculado no curso $nome.")
    }
}

class CursoPresencial(nome: String, nivel: Nivel, duracao: Int, val local: String) : Curso(nome, nivel, duracao) {
    fun iniciar() {
        println("Iniciando curso presencial $nome no local $local.")
    }
}

class CursoOnline(nome: String, nivel: Nivel, duracao: Int, val url: String) : Curso(nome, nivel, duracao) {
    fun reproduzir() {
        println("Reproduzindo curso online $nome na URL $url.")
    }
}

fun main() {
    val usuario1 = Usuario("Max")
    val usuario2 = Usuario("Vanessa")

    val conteudo1 = ConteudoEducacional("Kotlin Básico")
    val conteudo2 = ConteudoEducacional("Kotlin Intermediário")
    val conteudo3 = ConteudoEducacional("Kotlin Avançado")

    val formacao = Formacao("Formação Kotlin", mutableListOf(conteudo1, conteudo2, conteudo3))

    formacao.matricular(usuario1)
    formacao.matricular(usuario2)

    val cursoPresencial = CursoPresencial("Kotlin para Android", Nivel.INTERMEDIARIO, 40, "São Paulo")
    cursoPresencial.matricular(usuario1)
    cursoPresencial.iniciar()

    val cursoOnline = CursoOnline("Kotlin para Web", Nivel.AVANCADO, 60, "https://www.dio.me/en")
    cursoOnline.matricular(usuario2)
    cursoOnline.reproduzir()
}