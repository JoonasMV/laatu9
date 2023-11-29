import org.example.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class TilaustenKasittelijaTest {

    @Mock
    IHinnoittelija hinnoittelijaMock;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testKasittelijaMockAlle100() {
        float alkuSaldo = 100.0f;
        float listaHinta = 100.0f;
        float alennus = 20.0f;
        float loppuSaldo = alkuSaldo - (listaHinta * (1 - (alennus) / 100));

        Asiakas asiakas = new Asiakas(alkuSaldo);
        Tuote tuote = new Tuote("test", listaHinta);

        when(hinnoittelijaMock.getAlennusProsentti(asiakas, tuote))
                .thenReturn(alennus);


        TilaustenKasittely kasittelija = new TilaustenKasittely();


        kasittelija.setHinnoittelija(hinnoittelijaMock);
        kasittelija.kasittele(new Tilaus(asiakas, tuote));

        assertEquals(loppuSaldo, asiakas.getSaldo());
        verify(hinnoittelijaMock, times(2)).getAlennusProsentti(asiakas, tuote);
    }


    @Test
    public void testKasittelijaMockTasan100() {
        float alkuSaldo = 100.0f;
        float listaHinta = 100.0f;
        float alennus = 20.0f;
        float lisaAlennus = 5F;
        float loppuSaldo = alkuSaldo - (listaHinta * (1 - (alennus + lisaAlennus) / 100));

        Asiakas asiakas = new Asiakas(alkuSaldo);
        Tuote tuote = new Tuote("test", listaHinta);

        when(hinnoittelijaMock.getAlennusProsentti(asiakas, tuote))
                .thenReturn(alennus + lisaAlennus);


        TilaustenKasittely kasittelija = new TilaustenKasittely();


        kasittelija.setHinnoittelija(hinnoittelijaMock);
        kasittelija.kasittele(new Tilaus(asiakas, tuote));

        assertEquals(loppuSaldo, asiakas.getSaldo());
        verify(hinnoittelijaMock, times(2)).getAlennusProsentti(asiakas, tuote);
    }
}
