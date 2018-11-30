package ohtu.intjoukkosovellus;

public class IntJoukko {

    public final static int KAPASITEETTI = 5, // aloitustalukon koko
            OLETUSKASVATUS = 5;  // luotava uusi taulukko on 
    // näin paljon isompi kuin vanha
    private int kasvatuskoko;     // Uusi taulukko on tämän verran vanhaa suurempi.
    private int[] taulukko;      // Joukon luvut säilytetään taulukon alkupäässä. 
    private int alkioidenLkm;    // Tyhjässä joukossa alkioiden_määrä on nolla. 

    public IntJoukko() {
        taulukko = new int[KAPASITEETTI];
        alustaTaulukkoNolliksi();
        alkioidenLkm = 0;
        this.kasvatuskoko = OLETUSKASVATUS;
    }

    private void alustaTaulukkoNolliksi() {
        for (int i = 0; i < taulukko.length; i++) {
            taulukko[i] = 0;
        }
    }

    public IntJoukko(int kapasiteetti) {
        if (kapasiteetti < 0) {
            return;
        }
        taulukko = new int[kapasiteetti];
        alustaTaulukkoNolliksi();
        alkioidenLkm = 0;
        this.kasvatuskoko = OLETUSKASVATUS;

    }

    public IntJoukko(int kapasiteetti, int kasvatuskoko) {
        if (kapasiteetti < 0) {
            throw new IndexOutOfBoundsException("Kapasiteetin tulee olla positiivinen");//heitin vaan jotain :D
        }
        if (kasvatuskoko < 0) {
            throw new IndexOutOfBoundsException("Kasvatuskoon tulee olla positiivinen");//heitin vaan jotain :D
        }
        taulukko = new int[kapasiteetti];
        alustaTaulukkoNolliksi();
        alkioidenLkm = 0;
        this.kasvatuskoko = kasvatuskoko;

    }

    public boolean lisaa(int luku) {
        if (alkioidenLkm == 0) {
            lisaaLukuIndeksiin0(luku);
            return true;
        }
        if (!loytyykoTaulukostaLuku(luku)) {
            taulukko[alkioidenLkm] = luku;
            alkioidenLkm++;
            if (alkioidenLkm % taulukko.length == 0) {
                lisaaTaulukonKokoa();
            }
            return true;
        }
        return false;
    }

    private void lisaaLukuIndeksiin0(int luku) {
        taulukko[0] = luku;
        alkioidenLkm++;
    }

    private void lisaaTaulukonKokoa() {
        int[] kopio = new int[taulukko.length];
        kopio = taulukko;
        kopioiTaulukko(taulukko, kopio);
        taulukko = new int[alkioidenLkm + kasvatuskoko];
        kopioiTaulukko(kopio, taulukko);
    }

    public boolean loytyykoTaulukostaLuku(int luku) {
        int lukujaLöytyy = 0;
        for (int i = 0; i < alkioidenLkm; i++) {
            if (luku == taulukko[i]) {
                lukujaLöytyy++;
            }
        }
        if (lukujaLöytyy > 0) {
            return true;
        } else {
            return false;
        }
    }

    public boolean poista(int luku) {
        int lukuLöytyyIndeksistä = poistaLukuJosLoytyyTaulukostaJaPalautaIndeksi(luku);
        if (lukuLöytyyIndeksistä != -1) {
            poistaIndeksiTaulukosta(lukuLöytyyIndeksistä);
            return true;
        }
        return false;
    }

    private int poistaLukuJosLoytyyTaulukostaJaPalautaIndeksi(int luku) {
        int lukuLöytyyIndeksistä = -1;
        for (int i = 0; i < alkioidenLkm; i++) {
            if (luku == taulukko[i]) {
                lukuLöytyyIndeksistä = i;
                taulukko[lukuLöytyyIndeksistä] = 0;
                break;
            }
        }
        return lukuLöytyyIndeksistä;
    }

    private void poistaIndeksiTaulukosta(int indeksi) {
        for (int j = indeksi; j < alkioidenLkm - 1; j++) {
            int temp = taulukko[j];
            taulukko[j] = taulukko[j + 1];
            taulukko[j + 1] = temp;
        }
        alkioidenLkm--;
    }

    private void kopioiTaulukko(int[] vanha, int[] uusi) {
        for (int i = 0; i < vanha.length; i++) {
            uusi[i] = vanha[i];
        }

    }

    public int mahtavuus() {
        return alkioidenLkm;
    }

    @Override
    public String toString() {
        switch (alkioidenLkm) {
            case 0:
                return "{}";
            case 1:
                return "{" + taulukko[0] + "}";
            default:
                return taulukkoStringiksi();
        }
    }

    private String taulukkoStringiksi() {
        String tuotos = "{";
        for (int i = 0; i < alkioidenLkm - 1; i++) {
            tuotos += taulukko[i];
            tuotos += ", ";
        }
        tuotos += taulukko[alkioidenLkm - 1];
        tuotos += "}";
        return tuotos;
    }

    public int[] toIntArray() {
        int[] taulu = new int[alkioidenLkm];
        for (int i = 0; i < taulu.length; i++) {
            taulu[i] = taulukko[i];
        }
        return taulu;
    }

    public static IntJoukko yhdiste(IntJoukko a, IntJoukko b) {
        IntJoukko x = new IntJoukko();
        int[] aTaulu = a.toIntArray();
        int[] bTaulu = b.toIntArray();
        for (int i = 0; i < aTaulu.length; i++) {
            x.lisaa(aTaulu[i]);
        }
        for (int i = 0; i < bTaulu.length; i++) {
            x.lisaa(bTaulu[i]);
        }
        return x;
    }

    public static IntJoukko leikkaus(IntJoukko a, IntJoukko b) {
        IntJoukko y = new IntJoukko();
        int[] aTaulu = a.toIntArray();
        int[] bTaulu = b.toIntArray();
        for (int i = 0; i < aTaulu.length; i++) {
            for (int j = 0; j < bTaulu.length; j++) {
                if (aTaulu[i] == bTaulu[j]) {
                    y.lisaa(bTaulu[j]);
                }
            }
        }
        return y;

    }

    public static IntJoukko erotus(IntJoukko a, IntJoukko b) {
        IntJoukko z = new IntJoukko();
        int[] aTaulu = a.toIntArray();
        int[] bTaulu = b.toIntArray();
        for (int i = 0; i < aTaulu.length; i++) {
            z.lisaa(aTaulu[i]);
        }
        for (int i = 0; i < bTaulu.length; i++) {
            z.poista(i);
        }

        return z;
    }

}
