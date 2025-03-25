/**
 * @author Sławek Klasa implementująca kalkulator ONP
 */
class ONP {
    private TabStack stack = new TabStack();

    /**
     * Metoda sprawdza czy równanie kończy się znakiem '='
     *
     * @param rownanie równanie do sprawdzenia
     * @return true jeśli równanie jest poprawne, false jeśli niepoprawne
     */
    boolean czyPoprawneRownanie(String rownanie) {
        if (rownanie.endsWith("="))
            return true;
        else
            return false;
    }

    /**
     * Metoda oblicza wartość wyrażenia zapisanego w postaci ONP
     *
     * @param rownanie równanie zapisane w postaci ONP
     * @return wartość obliczonego wyrażenia
     */
    public String obliczOnp(String rownanie) {
        if (czyPoprawneRownanie(rownanie)) {
            stack.setSize(0);
            String wynik = "";
            Double a = 0.0;
            Double b = 0.0;
            long silnia; // silnia jest zawsze z liczby naturalnej
            for (int i = 0; i < rownanie.length(); i++) {
                if (rownanie.charAt(i) >= '0' && rownanie.charAt(i) <= '9') {
                    wynik += rownanie.charAt(i);
                    if (!(rownanie.charAt(i + 1) >= '0' && rownanie.charAt(i + 1) <= '9')) {
                        stack.push(wynik);
                        wynik = "";
                    }

                } else if (rownanie.charAt(i) == '=') {
                    return stack.pop();
                }
                // Dodane liczenie silni
                else if(rownanie.charAt(i) == '!'){
                    a = Double.parseDouble(stack.pop());

                    silnia = 1;
                    for(int s = 2; s <= a; s++){
                        silnia *= s;
                    }

                    stack.push(silnia + "");
                }
                else if (rownanie.charAt(i) != ' ') {
                    b = Double.parseDouble(stack.pop());
                    a = Double.parseDouble(stack.pop());
                    switch (rownanie.charAt(i)) {
                        case ('+'): {
                            stack.push((a + b) + "");
                            break;
                        }
                        case ('-'): {
                            stack.push((a - b) + "");
                            break;
                        }
                        case ('x'):
                            ;
                        case ('*'): {
                            stack.push((a * b) + "");
                            break;
                        }
                        case ('/'): {
                            stack.push((a / b) + "");
                            break;
                        }
                        case ('^'): {
                            stack.push(Math.pow(a, b) + "");
                            break;
                        }

                        // Dodane liczenie modulo
                        case ('%'): {
                            stack.push((a % b) + "");
                            break;
                        }

                        case ('v'):{ // obsługa pierwiastków
                            stack.push(Math.pow(b, 1.0/a)+"");
                            break;
                        }

                        // Dodany wyjątek gdy nieznany operator
                        default: {
                            throw new IllegalArgumentException("Nieobslugiwany operator: " + rownanie.charAt(i));
                        }
                    }
                }
            }
            return "0.0";
        } else
            return "Brak '='";
    }

    /**
     * Metoda zamienia równanie na postać ONP
     *
     * @param rownanie równanie do zamiany na postać ONP
     * @return równanie w postaci ONP
     */
    public String przeksztalcNaOnp(String rownanie) {
        if (czyPoprawneRownanie(rownanie)) {
            String wynik = "";
            for (int i = 0; i < rownanie.length(); i++) {
                if (rownanie.charAt(i) >= '0' && rownanie.charAt(i) <= '9') {
                    wynik += rownanie.charAt(i);
                    if (!(rownanie.charAt(i + 1) >= '0' && rownanie.charAt(i + 1) <= '9'))
                        wynik += " ";
                } else
                    switch (rownanie.charAt(i)) {
                        case ('+'):
                            ;
                        case ('-'): {
                            while (stack.getSize() > 0 && !stack.showValue(stack.getSize() - 1).equals("(")) {
                                wynik = wynik + stack.pop() + " ";
                            }
                            String str = "" + rownanie.charAt(i);
                            stack.push(str);
                            break;
                        }
                        case ('x'):
                            ;
                        case ('*'):
                            ;
                        case ('%'): // Dodana obsługa modulo
                            ;
                        case ('/'):
                        {
                            while (stack.getSize() > 0 && !stack.showValue(stack.getSize() - 1).equals("(")
                                    && !stack.showValue(stack.getSize() - 1).equals("+")
                                    && !stack.showValue(stack.getSize() - 1).equals("-")) {
                                wynik = wynik + stack.pop() + " ";
                            }
                            String str = "" + rownanie.charAt(i);
                            stack.push(str);
                            break;
                        }
                        case ('^'): {
                            while (stack.getSize() > 0 && stack.showValue(stack.getSize() - 1).equals("^")) {
                                wynik = wynik + stack.pop() + " ";
                            }
                            String str = "" + rownanie.charAt(i);
                            stack.push(str);
                            break;
                        }

                        case ('v'): { // obsługa pierwiastków
                            while (stack.getSize() > 0 && stack.showValue(stack.getSize() - 1).equals("v")) {
                                wynik = wynik + stack.pop() + " ";
                            }
                            String str = "" + rownanie.charAt(i);
                            stack.push(str);
                            break;
                        }

                        case ('('): {
                            String str = "" + rownanie.charAt(i);
                            stack.push(str);
                            break;
                        }
                        case (')'): {
                            while (stack.getSize() > 0 && !stack.showValue(stack.getSize() - 1).equals("(")) {
                                wynik = wynik + stack.pop() + " ";
                            }
                            // zdjęcie ze stosu znaku (
                            stack.pop();
                            break;
                        }
                        case ('!'): {
                            wynik += rownanie.charAt(i) + " ";
                            break;
                        }

                        case ('='): {
                            while (stack.getSize() > 0) {
                                wynik = wynik + stack.pop() + " ";
                            }
                            wynik += "=";
                        }
                    }
            }
            return wynik;
        } else
            return "null";
    }

    public static void main(String[] args) {

        String tmp = "";
        if (args.length == 0) {
            tmp = "(2+3)*(6-2)^2=";
        } else {
            for (int i = 0; i < args.length; i++) {
                tmp += args[i];
            }
        }
        ONP onp = new ONP();
        System.out.print(tmp + " ");
        try {
            String rownanieOnp = onp.przeksztalcNaOnp(tmp);
            System.out.print(rownanieOnp);
            String wynik = onp.obliczOnp(rownanieOnp);
            System.out.println(" " + wynik);
        } catch (Exception e) {
            System.out.println("error: " + e.getMessage());
        }
    }
}