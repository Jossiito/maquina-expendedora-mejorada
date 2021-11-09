public class MaquinaExpendedoraMejorada {
    
    // El precio del billete
    private int precioBillete;
    // La cantidad de dinero que lleva metida el cliente actual
    private int balanceClienteActual;
    // El total de dinero almacenado en la maquina desde su ultimo vaciado
    private int totalDineroAcumulado;
    // El origen del billete
    private String estacionOrigen;
    // El destino del billete
    private String estacionDestino;
    // Numero de billetes vendidos
    private int numeroBilletesVendidos;
    // Eleccion para maquina con premios o no (true o false)
    private boolean maquinaPremios;
    // Numero maximo de billetes que puede imprimir la maquina 
    private int billetesMaximosImprimir;
    // Cada tres billetes imprime el premio
    private int imprimirCadaTresBilletes;
    
    /**
     * Crea una maquina expendedora de billetes de tren con el 
     * precio del billete y el origen y destino dados. Se asume que el precio
     * del billete que se recibe es mayor que 0.
     */
    public MaquinaExpendedoraMejorada(int precioDelBillete, String origen, String destino, boolean maquinaConPremio, int billetesMaximosQuePuedeImprimir) {
        precioBillete = precioDelBillete;
        balanceClienteActual = 0;
        totalDineroAcumulado = 0;
        estacionOrigen = origen;
        estacionDestino = destino;
        numeroBilletesVendidos = 0;
        maquinaPremios = maquinaConPremio;
        billetesMaximosImprimir = billetesMaximosQuePuedeImprimir;
        imprimirCadaTresBilletes = 0;
    }

    /**
     * Devuelve el precio del billete
     */
    public int getPrecioBillete() {
        return precioBillete;
    }

    /**
     * Devuelve la cantidad de dinero que el cliente actual lleva introducida
     */
    public int getBalanceClienteActual() {
        return balanceClienteActual;
    }

    /**
     * Simula la introduccion de dinero por parte del cliente actual
     */
    public void introducirDinero(int cantidadIntroducida) {
        if (billetesMaximosImprimir == numeroBilletesVendidos) {
            System.out.println(" La maquina ha alcanzado el numero máximo de billetes impresos ");
        }
        else{
            if (cantidadIntroducida > 0) {
                balanceClienteActual = balanceClienteActual + cantidadIntroducida;
            }
            else {
                System.out.println(cantidadIntroducida + " no es una cantidad de dinero valida.");
            }
        }
    }

    /**
     * Imprime un billete para el cliente actual
     */
    public void imprimirBillete() {
        if (billetesMaximosImprimir == numeroBilletesVendidos) {
            System.out.println(" La maquina ha alcanzado el numero máximo de billetes impresos ");
        }
        else {
            int cantidadDineroQueFalta = precioBillete - balanceClienteActual;
            if (cantidadDineroQueFalta <= 0) {        
                // Simula la impresion de un billete
                System.out.println("##################");
                System.out.println("# Billete de tren:");
                System.out.println("# De " + estacionOrigen + " a " + estacionDestino);
                System.out.println("# " + precioBillete + " euros.");
                System.out.println("##################");
                System.out.println();         
    
                // Actualiza el total de dinero acumulado en la maquina
                totalDineroAcumulado = totalDineroAcumulado + precioBillete;
                // Reduce el balance del cliente actual dejandole seguir utilizando la maquina
                balanceClienteActual = balanceClienteActual - precioBillete;
            
                numeroBilletesVendidos = numeroBilletesVendidos + 1;
            
                if (maquinaPremios == true) {
                    imprimirCadaTresBilletes = imprimirCadaTresBilletes + 1;
                    if (imprimirCadaTresBilletes == 3) {   
                        float descuentoBillete = (precioBillete*10)/100; 
                        System.out.println("Con esta compra tiene un descuento de " + descuentoBillete + " euros ");
                        imprimirCadaTresBilletes = imprimirCadaTresBilletes - 3;
                    }
                }
            } 
            else {
                System.out.println(" Dinero insuficiente, faltan " + cantidadDineroQueFalta + " euros ");
            }
        }
    }
    
    /**
     * Cancela la operacion de compra del cliente actual y le
     * devuelve al cliente el dinero que ha introducido hasta el momento
     */
    public int cancelarOperacionYDevolverDinero() {
        int cantidadDeDineroADevolver;
        cantidadDeDineroADevolver = balanceClienteActual;
        balanceClienteActual = 0;
        return cantidadDeDineroADevolver;
    } 
    
    public int vaciarDineroDeLaMaquina() {
        int cantidadDeDineroADevolverTotal;
        if (balanceClienteActual <= 0) {
            cantidadDeDineroADevolverTotal = balanceClienteActual + totalDineroAcumulado;
            balanceClienteActual = 0;
            totalDineroAcumulado = 0;
        }
        else {
            System.out.println("Hay una operación en curso");
            cantidadDeDineroADevolverTotal = -1;
        }
        return cantidadDeDineroADevolverTotal;
    }
    
    public int getNumeroBilletesVendidos() {
        return numeroBilletesVendidos;        
    }
    
    public void imprimeNumeroBilletesVendidos () {
        System.out.println(" El numero de billetes impresos es: " + numeroBilletesVendidos);
    }
}
