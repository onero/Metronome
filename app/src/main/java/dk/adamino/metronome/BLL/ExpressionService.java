package dk.adamino.metronome.BLL;

import static dk.adamino.metronome.BE.EMusicalExpression.Adagio;
import static dk.adamino.metronome.BE.EMusicalExpression.Allegretto;
import static dk.adamino.metronome.BE.EMusicalExpression.Allegro;
import static dk.adamino.metronome.BE.EMusicalExpression.Andante;
import static dk.adamino.metronome.BE.EMusicalExpression.Grave;
import static dk.adamino.metronome.BE.EMusicalExpression.Larghetto;
import static dk.adamino.metronome.BE.EMusicalExpression.Larghissimo;
import static dk.adamino.metronome.BE.EMusicalExpression.Largo;
import static dk.adamino.metronome.BE.EMusicalExpression.Lento;
import static dk.adamino.metronome.BE.EMusicalExpression.Moderato;
import static dk.adamino.metronome.BE.EMusicalExpression.Prestissimo;
import static dk.adamino.metronome.BE.EMusicalExpression.Presto;
import static dk.adamino.metronome.BE.EMusicalExpression.Vivace;

public class ExpressionService implements IExpressionService {

    public static final String LARGHISSIMO = "Larghissimo";
    public static final String GRAVE = "Grave";
    public static final String LARGO = "Largo";
    public static final String LENTO = "Lento";
    public static final String LARGHETTO = "Larghetto";
    public static final String ADAGIO = "Adagio";
    public static final String ANDANTE = "Andante";
    public static final String MODERATO = "Moderato";
    public static final String ALLEGRETTO = "Allegretto";
    public static final String ALLEGRO_POP = "Allegro (POP)";
    public static final String VIVACE = "Vivace";
    public static final String PRESTO = "Presto";
    public static final String PRESTISSIMO = "Prestissimo";

    @Override
    public String getExpression(int bpm) {
        String expression = "";
        if (bpm >= Larghissimo.getStartTempo()) {
            if (bpm < Grave.getStartTempo()) {
                expression = LARGHISSIMO;
            }else if (bpm < Largo.getStartTempo()) {
                expression = GRAVE;
            }else if (bpm < Lento.getStartTempo()) {
                expression = LARGO;
            }else if (bpm < Larghetto.getStartTempo()) {
                expression = LENTO;
            }else if (bpm < Adagio.getStartTempo()) {
                expression = LARGHETTO;
            }else if (bpm < Andante.getStartTempo()) {
                expression = ADAGIO;
            }else if (bpm < Moderato.getStartTempo()) {
                expression = ANDANTE;
            }else if (bpm < Allegretto.getStartTempo()) {
                expression = MODERATO;
            }else if (bpm < Allegro.getStartTempo()) {
                expression = ALLEGRETTO;
            }else if (bpm < Vivace.getStartTempo()) {
                expression = ALLEGRO_POP;
            }else if (bpm < Presto.getStartTempo()) {
                expression = VIVACE;
            }else if (bpm < Prestissimo.getStartTempo()) {
                expression = PRESTO;
            } else {
                expression = PRESTISSIMO;
            }
        }
        return expression;
    }
}
