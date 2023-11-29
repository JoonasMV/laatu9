import org.example.*;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class HinnoittelijaTest {

    TilaustenKasittely kasittely = new TilaustenKasittely();
    Asiakas asiakas = new Asiakas(100);
    Tuote tuote = new Tuote("tuoli", 50);
    Tilaus tilaus = new Tilaus(asiakas, tuote);

    IHinnoittelija hinnoittelija;

    @Test
    public void testHinnoittelu() {
        hinnoittelija = Mockito.mock(IHinnoittelija.class);
        Mockito.when(hinnoittelija.getAlennusProsentti(asiakas, tuote)).thenReturn(10F);

        kasittely.setHinnoittelija(hinnoittelija);
        kasittely.kasittele(tilaus);
        assertEquals(515, asiakas.getSaldo());
    }
}
