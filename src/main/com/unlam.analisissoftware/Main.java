package com.unlam.analisissoftware;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

    public static void main(String[] args) throws IOException {

      Long read = 1L, side1, side2, side3;
      Boolean toContinue = true;

      BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
      while (toContinue) {
        side1 = readSide(br, "Ingrese el primer lado:");
        side2 = readSide(br, "Ingrese el segundo lado:");
        side3 = readSide(br, "Ingrese el tercer lado:");

	    if (esTriangulo(side1, side2, side3)){
	      	TriangType type = getType(side1, side2, side3);	
	       	System.out.println("El triangulo es: "+type.toString());
	    }
	    else
	    {
	       	System.out.println("ERROR. No es triangulo");
	    }

        System.out.print("ENTER para continuar, 0 para finalizar:");
        try {
          read = Long.parseLong(br.readLine().trim());
          toContinue = !read.equals(0L);
        } catch (NumberFormatException e) {
          toContinue = true;
        }
      }
    }

    private static Long readSide(BufferedReader br, String msg) throws IOException {

      Long raw, side = null;

      while (side == null) {
          try {
              System.out.print(msg);
              raw = Long.parseLong(br.readLine().trim());

              if (raw <= 0) {
                throw new NumberFormatException();
              }

              side = raw;

          } catch(NumberFormatException nfe) {
              System.out.println("Error. Sólo se permiten enteros positivos.");
          }
      }

      return side;
    }

	private static boolean esTriangulo(Long side1, Long side2, Long side3){
		return ((side1 + side2) > side3)&& ((side1 + side3) > side2)
				&& ((side2 + side3) > side1);
	}    
    
    private static TriangType getType(Long side1, Long side2, Long side3) {
        if (!side1.equals(side2) && !side2.equals(side3) && !side3.equals(side1)) {
            // todos los lados son distintos
            return TriangType.ESCALENO;
        }

        if (side1.equals(side2) && side2.equals(side3)) {
            // todos los lados son iguales.
            return TriangType.EQUILATERO;
        }

        // no todos son distintos, y no todos son iguales, entones, hay 2 que son iguales.
        return TriangType.ISOSCELES;
    }
}
