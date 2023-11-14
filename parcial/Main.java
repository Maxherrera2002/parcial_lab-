//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {
    public Main() {
    }

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        String[] dna = new String[6];

        while(true) {
            while(true) {
                System.out.println("Quiere comprobar si es mutante o no(si/no)");
                String opcion = input.nextLine();
                if (!opcion.equals("si") && !opcion.equals("SI")) {
                    if (opcion.equals("no") || opcion.equals("NO")) {
                        System.out.println("Esta saliendo");
                        return;
                    }

                    System.out.println("Ingresar una opcion correcta");
                } else {
                    for(int i = 0; i < dna.length; ++i) {
                        String cadenaADN;
                        do {
                            System.out.println("Ingresa una cadena de ADN de 6 caracteres (A, T, C, G): ");
                            cadenaADN = input.nextLine();
                        } while(!validar(cadenaADN));

                        dna[i] = cadenaADN;
                    }

                    if (is_miutant(dna)) {
                        System.out.println("El ADN ingresado es de un MUTANTE");
                    } else {
                        System.out.println("El ADN ingresado no es de MUTANTE");
                    }
                }
            }
        }
    }

    public static boolean validar(String cadenaADN) {
        if (cadenaADN.length() != 6) {
            System.out.println("La cadena de ADN debe tener exactamente 6 caracteres.");
            return false;
        } else {
            for(int i = 0; i < cadenaADN.length(); ++i) {
                char nucleotido = cadenaADN.charAt(i);
                if (nucleotido != 'A' && nucleotido != 'T' && nucleotido != 'C' && nucleotido != 'G' && nucleotido != 'a' && nucleotido != 't' && nucleotido != 'c' && nucleotido != 'g') {
                    System.out.println("La cadena de ADN solo puede contener las letras A, T, C, G.");
                    return false;
                }
            }

            System.out.println("Se ingreso cadena");
            return true;
        }
    }

    public static boolean is_miutant(String[] dna) {
        int mutante = 0;
        mutante += horizontal(dna);
        mutante += vertical(dna);
        mutante += oblicuo(dna);
        String cadena = "";

        for(int i = 0; i < dna.length; ++i) {
            for(int j = 0; j < dna[i].length(); ++j) {
                cadena = cadena + dna[i].charAt(j);
            }

            dna[i] = cadena;
        }

        mutante += oblicuo(dna);
        if (mutante > 1) {
            return true;
        } else {
            return false;
        }
    }

    public static int oblicuo(String[] dna) {
        int mutacion = 0;
        int pase = 1;
        int i = 0;
        int j = 0;
        int repetir = 0;

        while(true) {
            if (pase == 1) {
                repetir = 4;
                i = 2;
                j = 0;
            } else if (pase == 2) {
                repetir = 4;
                i = 0;
                j = 2;
            } else if (pase == 3) {
                repetir = 5;
                i = 1;
                j = 0;
            } else if (pase == 4) {
                repetir = 5;
                i = 0;
                j = 1;
            } else if (pase == 5) {
                repetir = 6;
                i = 0;
                j = 0;
            }

            String cadena = "";

            for(int k = 0; k < repetir; ++k) {
                if (cadena.equals("")) {
                    cadena = cadena + dna[i].charAt(j);
                    ++i;
                    ++j;
                } else {
                    cadena = cadena + dna[i].charAt(j);
                    ++i;
                    ++j;
                }
            }

            mutacion += serrepite(cadena);
            if (pase == 5) {
                return mutacion;
            }

            ++pase;
        }
    }

    public static int serrepite(String cadena) {
        String regex = "(\\w)\\1{3}";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(cadena);
        return matcher.find() ? 1 : 0;
    }

    public static int horizontal(String[] dna) {
        int mutacion = 0;

        for(int i = 0; i < dna.length; ++i) {
            mutacion += serrepite(dna[i]);
        }

        return mutacion;
    }

    public static int vertical(String[] dna) {
        int mutacion = 0;

        for(int i = 0; i < dna.length; ++i) {
            String cadena = "";

            for(int j = 0; j < dna.length; ++j) {
                cadena = cadena + dna[j].charAt(i);
            }

            mutacion += serrepite(cadena);
        }

        return mutacion;
    }
}
