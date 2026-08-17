package Aula_26_04_13;

public class Hora {
    private int hora;
    private int minuto;
    private int segundo;

    public Hora() {
        this.hora = 0;
        this.minuto = 0;
        this.segundo = 0;
    }

    public Hora(int hora, int minuto, int segundo) {
        setHora(hora);
        setMinuto(minuto);
        setSegundo(segundo);
    }

    public int getHora() {
        return hora;
    }

    public void setHora(int hora) {
        this.hora = hora >= 0 && hora <= 23 ? hora : 0;
    }

    public int getMinuto() {
        return minuto;
    }

    public void setMinuto(int minuto) {
        this.minuto = minuto >= 0 && minuto <= 59 ? this.minuto : 0;
    }

    public int getSegundo() {
        return segundo;
    }

    public void setSegundo(int segundo) {
        this.segundo = segundo >= 0 && segundo <= 59 ? this.segundo : 0;
    }

    public String porExtenso() {
        return hora + ":" + minuto + ":" + segundo;
    }

    public Hora adicionarSegundos(int segundos) {
        if (segundos >= 0 && segundos <= 59) {
            if (this.segundo + segundos > 59) {
                this.minuto++;
                this.segundo += segundos % 59;
                if (this.minuto > 59) {
                    this.hora++;
                    this.minuto %= this.minuto;
                }
            }
        }
        return this;
    }
}
