import java.util.Random;
import java.util.stream.Stream;

public class Main {

    public static void main(String[] args) {

        int i, k, jt, j;
        float[][] dist = new float[10][10];

        Random random = Random();

        P[] pontos = Stream.generate(P::new).limit(10).toArray(P[]::new);

        for (i = 0; i < 10; i++) {
            pontos[i].id = i;
            pontos[i].px = getRandomNumber(random, 361);
            pontos[i].py = getRandomNumber(random,361);

            System.out.println("\nIndividuo " + pontos[i].id);
            System.out.println("PX = " + pontos[i].px);
            System.out.println("PY = " + pontos[i].py);
        }

        for (i = 0; i < 10; i++){
            for (j = 0; j < 10; j++){
                dist[i][j] = (float) Math.sqrt(Math.pow(pontos[i].px - pontos[j].px, 2) + Math.pow(pontos[i].py - pontos[j].py, 2));
                System.out.printf("%6.2f  ", dist[i][j]);
            }
            System.out.println();
        }

        Populacao[] pop = Stream.generate(Populacao::new).limit(100).toArray(Populacao[]::new);

        for (k = 0; k < 100; k++){
            pop[k].valor= (float) 0;

            for(i=0;i<10;i++){
                pop[k].individuo[i]=getRandomNumber(random, 10);
                for(jt=0;jt<i;jt++){
                    if(pop[k].individuo[i]==pop[k].individuo[jt]){
                        pop[k].individuo[i]=getRandomNumber(random, 10);
                        jt=0;
                    }
                }
                System.out.print(pop[k].individuo[i] + "  ");

                if (i>0){
                    pop[k].valor= pop[k].valor + (dist[pop[k].individuo[i]][pop[k].individuo[i-1]]);
                }

                System.out.printf("%7.2f\n", pop[k].valor);
                System.out.println();
            }
        }
    }

    private static Random Random() {

        Random random = new Random();

        random.setSeed(System.currentTimeMillis());

        return random;
    }

    private static int getRandomNumber(Random random, int porcentagem) {

        return ((random.nextInt() & Integer.MAX_VALUE) % porcentagem);
    }
}
