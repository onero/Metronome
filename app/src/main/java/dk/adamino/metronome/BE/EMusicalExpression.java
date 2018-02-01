package dk.adamino.metronome.BE;

public enum EMusicalExpression {
    Larghissimo (1),
    Grave (24),
    Largo (40),
    Lento (45),
    Larghetto (60),
    Adagio (66),
    Andante (76),
    Moderato (108),
    Allegretto (112),
    Allegro (120),
    Vivace (156),
    Presto (168),
    Prestissimo (200);

    private final int mTempo;

    private EMusicalExpression(int tempo) {
        mTempo = tempo;
    }

    public int getStartTempo() {
        return mTempo;
    }
}
