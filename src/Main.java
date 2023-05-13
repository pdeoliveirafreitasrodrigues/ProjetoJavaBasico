import builders.StudentsBuilder;
import entities.Student;

import java.text.DecimalFormat;
import java.util.*;
import java.util.function.Predicate;
import java.util.stream.Collectors;


public class Main {

    private static double calculateAvg(Student student){
        return (student.getTestOne() + student.getTestTwo() + student.getTestThree()) /3;
    }

    public static void main(String[] args) {
        var allStudents = StudentsBuilder.getAllStudents();
        DecimalFormat df = new DecimalFormat("##0.0");
        Scanner teclado = new Scanner(System.in);

        // Agora vamos as atividades
        /*

        1. Recupere da lista os alunos que passaram de ano (nota minima 7.0).
            - Exiba os dados nesse formato: <código> - <nome> : Média = <nota>
        2. Recupere da lista os alunos que não passaram de ano.
            - Exiba os dados nesse formato: <código> - <nome> : Média = <media> (Faltou = <nota_faltante>)
        3. Traga os alunos que tiraram a nota máxima (nota 10).
            - Exiba os dados nesse formato: <código> - <nome>
        4. Traga o aluno que tirou a menor nota, em caso de notas iguais, traga ambos os alunos.
            - Exiba os dados nesse formato: <código> - <nome> : Nota = <nota>
        5. Faça uma lista com top 3 notas de alunos. Em caso de notas iguais coloque todos na mesma posição.
            - Ex:
                1º - Fulano : Nota = 10.0;
                   - Beltrano : Nota = 10.0;
                2º - Joãozinho : Nota = 9.0;
                3º - Mariazinha : Nota = 8.9;
            - Exiba os dados nesse formato: <posicao> - <nome> : Nota = <nota>
        6. Faça uma lista com as 3 menores notas de alunos. Em caso de notas iguais coloque todos na mesma posição. Exemplo igual a anterior
            - Exiba os dados nesse formato: <posicao> - <nome> : Nota = <nota>
        7. Monte a média de todos os alunos e exiba em tela ordenando da maior para a menor nota.
            - Exiba os dados nesse formato: <posicao> - <código> - <nome> : Média = <nota>


            */

        var resposta = 1;
        do {
            var escolhaSwich = 0;

            System.out.println("Selecione a opção desejada: " +
                    "\n[1] - Lista de Alunos acima da média." +
                    "\n[2] - Lista de Alunos abaixo da média." +
                    "\n[3] - Lista de Alunos que tiraram a nota máxima." +
                    "\n[4] - Aluno com menor nota." +
                    "\n[5] - Top 03 maiores notas." +
                    "\n[6] - Top 03 menores notas." +
                    "\n[7] - Lista de Alunos ordenada pela sua média.");
            escolhaSwich = teclado.nextInt();

            switch (escolhaSwich){

                case 1:
                    // ATIVIDADE 01
                    System.out.println("---------------------------------------------");
                    System.out.println("ALUNOS ACIMA DA MÉDIA");
                    System.out.println("---------------------------------------------");
                    for (Student student : allStudents){
                        double avg = calculateAvg(student);
                        if (avg >= 7){
                            System.out.println(student.getCode() + " - " + student.getName() + ": Média = " + df.format(avg));
                        }
                    }
                    break;
                case 2:
                    // ATIVIDADE 02
                    System.out.println("---------------------------------------------");
                    System.out.println("ALUNOS ABAIXO DA MÉDIA");
                    System.out.println("---------------------------------------------");
                    for (Student student : allStudents){
                        double avg = calculateAvg(student);
                        if (avg < 7){
                            double missNote = 7 - avg;
                            System.out.println(student.getCode() + " - " + student.getName() + ": Média = " + df.format(avg) +
                                    " (Faltou = " + df.format(missNote) + ")");
                        }
                    }
                    break;
                case 3:
                    // ATIVIDADE 03
                    System.out.println("---------------------------------------------");
                    System.out.println("ALUNOS QUE TIRARAM NOTA MÁXIMA");
                    System.out.println("---------------------------------------------");
                    Boolean validator = Boolean.FALSE;
                    for (Student student : allStudents){
                        if (student.getTestOne() == 10 || student.getTestTwo() == 10 || student.getTestThree() == 10){
                            validator = Boolean.TRUE;
                            System.out.println(student.getCode() + " - " + student.getName());
                        }else {
                            validator = Boolean.FALSE;
                        }
                    }
                    if (!(validator)){
                        System.out.println("Não existem alunos com média igual a 10");
                    }
                    break;
                case 4:
                    // ATIVIDADE 04
                    System.out.println("---------------------------------------------");
                    System.out.println("ALUNO COM MENOR NOTA");
                    System.out.println("---------------------------------------------");
                    float lowestNote = Float.MAX_VALUE;
                    var littleStudents = new ArrayList<Student>();
                    var controlNotesEX04 = new ArrayList<Float>();

                    for (Student student : allStudents){
                        if (lowestNote > student.getTestOne() || lowestNote > student.getTestTwo()
                                || lowestNote > student.getTestThree()){
                            controlNotesEX04.add(student.getTestOne());
                            controlNotesEX04.add(student.getTestTwo());
                            controlNotesEX04.add(student.getTestThree());
                            lowestNote = Collections.min(controlNotesEX04);
                            littleStudents.clear();
                            littleStudents.add(student);
                        } else if (lowestNote == student.getTestOne() || lowestNote == student.getTestTwo()
                                || lowestNote == student.getTestThree()) {
                            littleStudents.add(student);
                        }
                    }
                    for (Student student : littleStudents){
                        System.out.println(student.getCode() + " - " + student.getName() + ": Nota = " + df.format(lowestNote));
                    }
                    break;
                case 5:
                    // ATIVIDADE 05
                    System.out.println("---------------------------------------------");
                    System.out.println("TOP 03 MAIORES NOTAS");
                    System.out.println("---------------------------------------------");
                    float topOne = Float.MIN_VALUE;
                    var controlNotesEx05 = new ArrayList<Float>();
                    var topOneStudents = new ArrayList<Student>();

                    for (Student student : allStudents){
                        if (topOne < student.getTestOne() || topOne < student.getTestTwo() || topOne < student.getTestThree()){
                            controlNotesEx05.add(student.getTestOne());
                            controlNotesEx05.add(student.getTestTwo());
                            controlNotesEx05.add(student.getTestThree());
                            topOne = Collections.max(controlNotesEx05);
                            topOneStudents.clear();
                            topOneStudents.add(student);
                        } else if (topOne == student.getTestOne() || topOne == student.getTestTwo()
                                || topOne == student.getTestThree()){
                            topOneStudents.add(student);
                        }
                    }

                    float topTwo = Float.MIN_VALUE;
                    controlNotesEx05.clear();
                    var topTwoStudents = new ArrayList<Student>();

                    for (Student student : allStudents){
                        controlNotesEx05.add(student.getTestOne());
                        controlNotesEx05.add(student.getTestTwo());
                        controlNotesEx05.add(student.getTestThree());
                        if (controlNotesEx05.contains(topOne)){
                            controlNotesEx05.remove(topOne);
                        }
                        if(Collections.max(controlNotesEx05) < topOne && Collections.max(controlNotesEx05) > topTwo){
                            topTwo = Collections.max(controlNotesEx05);
                            topTwoStudents.clear();
                            topTwoStudents.add(student);
                        }else if (topTwo == Collections.max(controlNotesEx05) && Collections.max(controlNotesEx05) < topOne){
                            topTwoStudents.add(student);
                        }
                        controlNotesEx05.clear();
                    }

                    float topThree = Float.MIN_VALUE;
                    controlNotesEx05.clear();
                    var topThreeStudents = new ArrayList<Student>();

                    for (Student student : allStudents){
                        controlNotesEx05.add(student.getTestOne());
                        controlNotesEx05.add(student.getTestTwo());
                        controlNotesEx05.add(student.getTestThree());
                        if (controlNotesEx05.contains(topOne)){
                            controlNotesEx05.remove(topOne);
                        }
                        if (controlNotesEx05.contains(topTwo)){
                            controlNotesEx05.remove(topTwo);
                        }
                        if(topThree < Collections.max(controlNotesEx05) && Collections.max(controlNotesEx05) < topOne && Collections.max(controlNotesEx05) < topTwo){
                            topThree = Collections.max(controlNotesEx05);
                            topThreeStudents.clear();
                            topThreeStudents.add(student);
                        }else if (topThree == Collections.max(controlNotesEx05) && Collections.max(controlNotesEx05) < topOne && Collections.max(controlNotesEx05) < topTwo){
                            topThreeStudents.add(student);
                        }
                        controlNotesEx05.clear();
                    }

                    System.out.println("1° Lugar : ");
                    for (Student student : topOneStudents){
                        System.out.println(student.getName() + " - Nota = " + topOne);
                    }
                    System.out.println("2° Lugar : ");
                    for (Student student : topTwoStudents){
                        System.out.println(student.getName() + " - Nota = " + topTwo);
                    }
                    System.out.println("3° Lugar : ");
                    for (Student student : topThreeStudents){
                        System.out.println(student.getName() + " - Nota = " + topThree);
                    }
                    break;
                case 6:
                    //ATIVIDADE 06
                    System.out.println("---------------------------------------------");
                    System.out.println("TOP 03 MENORES NOTAS");
                    System.out.println("---------------------------------------------");
                    float badNoteOne = Float.MAX_VALUE;
                    var controlNotesEx06 = new ArrayList<Float>();
                    var topOneBadStudents = new ArrayList<Student>();

                    for (Student student : allStudents) {
                        controlNotesEx06.add(student.getTestOne());
                        controlNotesEx06.add(student.getTestTwo());
                        controlNotesEx06.add(student.getTestThree());
                        if (Collections.min(controlNotesEx06) < badNoteOne){
                            badNoteOne = Collections.min(controlNotesEx06);
                            topOneBadStudents.clear();
                            topOneBadStudents.add(student);
                        }else if (Collections.min(controlNotesEx06) == badNoteOne){
                            topOneBadStudents.add(student);
                        }
                        controlNotesEx06.clear();
                    }

                    controlNotesEx06.clear();
                    float badNoteTwo = Float.MAX_VALUE;
                    var topTwoBadStudents = new ArrayList<Student>();

                    for (Student student : allStudents){
                        controlNotesEx06.add(student.getTestOne());
                        controlNotesEx06.add(student.getTestTwo());
                        controlNotesEx06.add(student.getTestThree());
                        if (controlNotesEx06.contains(badNoteOne)){
                            controlNotesEx06.remove(badNoteOne);
                        }
                        if (Collections.min(controlNotesEx06) < badNoteTwo && Collections.min(controlNotesEx06) > badNoteOne){
                            badNoteTwo = Collections.min(controlNotesEx06);
                            topTwoBadStudents.clear();
                            topTwoBadStudents.add(student);
                        } else if (Collections.min(controlNotesEx06) == badNoteTwo && Collections.min(controlNotesEx06) > badNoteOne) {
                            topTwoBadStudents.add(student);
                        }
                        controlNotesEx06.clear();
                    }

                    controlNotesEx06.clear();
                    float badNoteThree = Float.MAX_VALUE;
                    var topThreeBadStudents = new ArrayList<Student>();

                    for (Student student : allStudents){
                        controlNotesEx06.add(student.getTestOne());
                        controlNotesEx06.add(student.getTestTwo());
                        controlNotesEx06.add(student.getTestThree());
                        if (controlNotesEx06.contains(badNoteOne)){
                            controlNotesEx06.remove(badNoteOne);
                        }
                        if (controlNotesEx06.contains(badNoteTwo)){
                            controlNotesEx06.remove(badNoteTwo);
                        }
                        if ((Collections.min(controlNotesEx06) > badNoteTwo) && (Collections.min(controlNotesEx06) > badNoteOne)
                                && (Collections.min(controlNotesEx06) < badNoteThree)){
                            badNoteThree = Collections.min(controlNotesEx06);
                            topThreeBadStudents.clear();
                            topThreeBadStudents.add(student);
                        } else if ((Collections.min(controlNotesEx06) == badNoteThree) &&
                                (Collections.min(controlNotesEx06) > badNoteOne) && (Collections.min(controlNotesEx06) > badNoteTwo)) {
                            topThreeBadStudents.add(student);
                        }
                        controlNotesEx06.clear();
                    }


                    System.out.println("1° Lugar pior Nota : ");
                    for (Student student : topOneBadStudents){
                        System.out.println(student.getName() + " - Nota = " + badNoteOne);
                    }
                    System.out.println("2° Lugar pior Nota : ");
                    for (Student student : topTwoBadStudents){
                        System.out.println(student.getName() + " - Nota = " + badNoteTwo);
                    }
                    System.out.println("3° Lugar : ");
                    for (Student student : topThreeBadStudents){
                        System.out.println(student.getName() + " - Nota = " + badNoteThree);
                    }
                    break;
                case 7:
                    //ATIVIDADE 07
                    System.out.println("---------------------------------------------");
                    System.out.println("LISTA DE ALUNOS ORDENADA PELAS MÉDIAS");
                    System.out.println("---------------------------------------------");
                    var mutableStudents = new ArrayList<>(allStudents);
                    Comparator<Student> comparator = Comparator.comparingDouble(Main::calculateAvg);
                    comparator = comparator.reversed();
                    mutableStudents.sort(comparator);
                    for (int i = 0; i < mutableStudents.size(); i++) {
                        Student student = mutableStudents.get(i);
                        int position = i + 1;
                        System.out.println(position + "° - " + student.getCode() + " - " + student.getName() +
                                " : Média = " + df.format(calculateAvg(student)));
                    }
                    break;
                default:
                    System.out.println("Opção inválida.");
            }
            System.out.println("*************");
            System.out.println("Deseja verificar outra opção? " +
                    "\n[1] - Sim" +
                    "\n[2] - Não");
            resposta = teclado.nextInt();
            System.out.println("=====================================================================");
        }while (resposta != 2);
    }
}
