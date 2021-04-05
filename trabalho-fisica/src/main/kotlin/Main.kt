import kotlin.math.*

fun main() {
    do {
        val menu = """----------Cenário 7----------  
|                           |
|      1-Calculos           |
|      2-Grafico            |
|      0-sair               |
|                           |
-----------------------------
opcao: """
        print(menu)
        val erro = "\nResposta inválida"
        val opcao = readLine()?.toIntOrNull() ?: 3

        if (opcao == 0)
            return
        if (opcao in 1..2) {
            println()
            val larguraGrafico = 80
            val alturaGrafico = 25
            val grafico = Chart(larguraGrafico , alturaGrafico)
            var alvoy: Double
            var alvox : Double

            do {
                println("Distância do alvo:")//comprimento final
                alvox = readLine()?.toDoubleOrNull() ?: 0.0
                if (alvox <= 0) {
                    println(erro)
                }
            } while (alvox <= 0)
            println()

            do {
                println("Altura do alvo:")//Altura final
                alvoy = readLine()?.toDoubleOrNull() ?: 0.0
                if (alvoy <= 0) {
                    println(erro)
                }
            } while (alvoy <= 0)
            println()

            val teta = atan(2 * alvoy / alvox)//teta original
            var teta1 : Double //Variar este teta muda o y0

            do {
                println("Escolher um angulo entre 0 e ${(teta * 180) / PI} (Enter para obter o valor maximo):")//teta->0+ o grafico aproxima-se de uma reta horizontal
                teta1 = readLine()?.toDoubleOrNull() ?: teta * 180 / PI
                if ((teta1 !in 0.0..teta * 180 / PI)) {
                    println(erro)
                }
            } while (teta1 !in 0.0..teta * 180 / PI)

            teta1 *= PI / 180//converter para radianos
            
            var x = 0.0//x inicial
            val gravidade = 9.80665
            val v0 = sqrt((gravidade * alvox) / (sin(teta1) * cos(teta1)))//velocidade inicial
            val y0 = alvoy - (alvox * tan(teta1) / 2)//y inicial
            var y : Double

            when (opcao) {
                1 -> println("Angulo: ${teta1 * 180 / PI} \nAltura inicial: $y0 \nVelocidade inicial: $v0")//valor dos calculos
                2 -> {
                    do {
                        y = y0+ tan(teta1) * x - ((gravidade / 2) * (x.pow(2)) / (v0 * cos(teta1)).pow(2))
                        x += 0.001
                        grafico.ponto(x , y)//pontos do grafico
                    } while ( alvoy >= y && alvox>=x)
                    grafico.draw()//grafico
                }
            }
            println()
        }else{
            println(erro)
        }

    } while (opcao != 0)
}
