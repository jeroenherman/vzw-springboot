package be.voedsaam.vzw.business.repository.impl;

import be.voedsaam.vzw.business.Destination;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

/**
 * Integration test uses test data brought in via bootstrap package
 */
@RunWith(SpringRunner.class)
@Transactional
@SpringBootTest
public class DestinationServiceRepoImplTest {

    @Autowired
    private DestinationServiceRepoImpl classUnderTest;
    private Destination destination;

    @Before
    public void setup(){
        destination = new Destination();
        destination.setDestinationName("nieuwe Bestemming");
        destination.setContactInfo("contact");
    }

    @Test
    public void listAll() throws Exception {
        assertEquals(3,classUnderTest.listAll().size());
    }

    @Test
    public void getById() throws Exception {
        destination = classUnderTest.saveOrUpdate(destination);
        assertEquals( destination , classUnderTest.getById(destination.getId()));
    }


    @Test
    public void delete() throws Exception {
        destination = classUnderTest.saveOrUpdate(destination);
        Long id = destination.getId();
        classUnderTest.delete(null);
        classUnderTest.delete(destination.getId());
        assertNull( classUnderTest.getById(destination.getId()));
        assertNull( classUnderTest.getById(id));
    }

    @Test
    public void testLargeAgreement(){
       List<String> agreements =new ArrayList<>();
       agreements.add("Wat is Lorem Ipsum?\n"+
                "Lorem Ipsum is slechts een proeftekst uit het drukkerij- en zetterijwezen. Lorem Ipsum is de standaard proeftekst in deze bedrijfstak sinds de 16e eeuw, toen een onbekende drukker een zethaak met letters nam en ze door elkaar husselde om een font-catalogus te maken. Het heeft niet alleen vijf eeuwen overleefd maar is ook, vrijwel onveranderd, overgenomen in elektronische letterzetting. Het is in de jaren '60 populair geworden met de introductie van Letraset vellen met Lorem Ipsum passages en meer recentelijk door desktop publishing software zoals Aldus PageMaker die versies van Lorem Ipsum bevatten.\n" +
                "\n" +
                "Waarom gebruiken we het?\n" +
                "Het is al geruime tijd een bekend gegeven dat een lezer, tijdens het bekijken van de layout van een pagina, afgeleid wordt door de tekstuele inhoud. Het belangrijke punt van het gebruik van Lorem Ipsum is dat het uit een min of meer normale verdeling van letters bestaat, in tegenstelling tot \"Hier uw tekst, hier uw tekst\" wat het tot min of meer leesbaar nederlands maakt. Veel desktop publishing pakketten en web pagina editors gebruiken tegenwoordig Lorem Ipsum als hun standaard model tekst, en een zoekopdracht naar \"lorem ipsum\" ontsluit veel websites die nog in aanbouw zijn. Verscheidene versies hebben zich ontwikkeld in de loop van de jaren, soms per ongeluk soms expres (ingevoegde humor en dergelijke).\n" +
                "\n" +
                "\n" +
                "Waar komt het vandaan?\n" +
                "In tegenstelling tot wat algemeen aangenomen wordt is Lorem Ipsum niet zomaar willekeurige tekst. het heeft zijn wortels in een stuk klassieke latijnse literatuur uit 45 v.Chr. en is dus meer dan 2000 jaar oud. Richard McClintock, een professor latijn aan de Hampden-Sydney College in Virginia, heeft één van de meer obscure latijnse woorden, consectetur, uit een Lorem Ipsum passage opgezocht, en heeft tijdens het zoeken naar het woord in de klassieke literatuur de onverdachte bron ontdekt. Lorem Ipsum komt uit de secties 1.10.32 en 1.10.33 van \"de Finibus Bonorum et Malorum\" (De uitersten van goed en kwaad) door Cicero, geschreven in 45 v.Chr. Dit boek is een verhandeling over de theorie der ethiek, erg populair tijdens de renaissance. De eerste regel van Lorem Ipsum, \"Lorem ipsum dolor sit amet..\", komt uit een zin in sectie 1.10.32.\n" +
                "\n" +
                "Het standaard stuk van Lorum Ipsum wat sinds de 16e eeuw wordt gebruikt is hieronder, voor wie er interesse in heeft, weergegeven. Secties 1.10.32 en 1.10.33 van \"de Finibus Bonorum et Malorum\" door Cicero zijn ook weergegeven in hun exacte originele vorm, vergezeld van engelse versies van de 1914 vertaling door H. Rackham.\n" +
                "\n" +
                "Waar kan ik het vinden?\n" +
                "Er zijn vele variaties van passages van Lorem Ipsum beschikbaar maar het merendeel heeft te lijden gehad van wijzigingen in een of andere vorm, door ingevoegde humor of willekeurig gekozen woorden die nog niet half geloofwaardig ogen. Als u een passage uit Lorum Ipsum gaat gebruiken dient u zich ervan te verzekeren dat er niets beschamends midden in de tekst verborgen zit. Alle Lorum Ipsum generators op Internet hebben de eigenschap voorgedefinieerde stukken te herhalen waar nodig zodat dit de eerste echte generator is op internet. Het gebruikt een woordenlijst van 200 latijnse woorden gecombineerd met een handvol zinsstructuur modellen om een Lorum Ipsum te genereren die redelijk overkomt. De gegenereerde Lorum Ipsum is daardoor altijd vrij van herhaling, ingevoegde humor of ongebruikelijke woorden etc.\n" +
                "\n" +
                "5\n" +
                "\tparagrafen\n" +
                "\twoorden\n" +
                "\tbytes\n" +
                "\tlijsten\n" +
                "\tStart met \"Lorem\n" +
                "ipsum dolor sit amet...\"\n");
        destination.setAgreements(agreements);
        destination = classUnderTest.saveOrUpdate(destination);
        assertEquals(destination.getAgreements().size(), classUnderTest.getById(destination.getId()).getAgreements().size());
    }

}